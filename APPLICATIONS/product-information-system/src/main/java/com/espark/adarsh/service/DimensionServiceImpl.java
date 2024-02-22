package com.espark.adarsh.service;

import com.espark.adarsh.bean.DimensionBean;
import com.espark.adarsh.bean.DimensionBean.DimensionValues;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.entity.DimensionValue;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.repostiory.db.DimensionRepository;
import com.espark.adarsh.repostiory.db.DimensionValueRepository;
import com.espark.adarsh.repostiory.solr.SearchDimensionRepository;
import com.espark.adarsh.repostiory.solr.SearchDimensionValueRepository;
import com.espark.adarsh.util.ApplicationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.espark.adarsh.util.ApplicationUtil.*;

@Slf4j
@Service
public class DimensionServiceImpl implements DimensionService {

    @Autowired
    private DimensionRepository dimensionRepository;

    @Autowired
    private DimensionValueRepository dimensionValueRepository;

    @Autowired
    private SearchDimensionRepository searchDimensionRepository;

    @Autowired
    private SearchDimensionValueRepository searchDimensionValueRepository;


    @Override
    public DimensionBean findByDimName(String dimName) throws ResourceNotFound {
        Dimension dimension = this.dimensionRepository.findByDimName(dimName);
        if (dimension == null) {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{dimName});
        }
        DimensionBean dimensionBean = new DimensionBean(dimension);
        List<DimensionValue> dimensionValueList = this.dimensionValueRepository.findByDimId(dimension.getDimId());
        List<DimensionValues> dimensionValues =
                dimensionValueList
                        .stream()
                        .map((DimensionValue value) -> new DimensionValues(value))
                        .collect(Collectors.toList());
        dimensionBean.setDimensionValues(dimensionValues);
        return dimensionBean;
    }

    @Override
    public DimensionBean saveDimension(DimensionBean dimensionBean) {
        Dimension dimension = dimensionBean.getDimension();
        dimension = this.dimensionRepository.save(dimension);
        this.searchDimensionRepository.save(dimension);
        dimensionBean.setDimension(dimension);
        return dimensionBean;
    }

    @Override
    public DimensionBean saveDimensionValue(DimensionBean dimensionBean) throws ResourceNotFound {
        Long dimId = dimensionBean.getDimId();
        if (this.dimensionRepository.existsById(dimId)) {
            List<DimensionValues> dimensionValues = this.saveDimensionValues(dimensionBean);
            dimensionBean.setDimensionValues(dimensionValues);
            return dimensionBean;
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{dimId});
        }
    }

    @Override
    public DimensionBean saveDimensionAndDimensionValue(DimensionBean dimensionBean) {
        dimensionBean = this.saveDimension(dimensionBean);
        List<DimensionValues> dimensionValues = this.saveDimensionValues(dimensionBean);
        dimensionBean.setDimensionValues(dimensionValues);
        return dimensionBean;
    }

    @Override
    @Transactional
    public DimensionBean updateDimensionAndDimensionValue(Long dimensionId, DimensionBean dimensionBean) {

        if (this.dimensionRepository.existsById(dimensionId)) {
            Dimension dimension = dimensionBean.getDimension();
            dimension = this.dimensionRepository.save(dimension);
            this.searchDimensionRepository.save(dimension);
            dimensionBean.setDimension(dimension);
            List<DimensionValues> dimensionValueList = this.saveDimensionValues(dimensionBean);
            dimensionBean.setDimensionValues(dimensionValueList);
        }
        return dimensionBean;
    }


    @Override
    public DimensionBean deleteDimension(Long dimensionId) throws ResourceNotFound {
        if (this.dimensionRepository.existsById(dimensionId)) {
            Dimension dimension = (Dimension) this.getById(dimensionId);
            this.dimensionRepository.deleteById(dimensionId);
            this.searchDimensionRepository.deleteById(dimensionId);
            return new DimensionBean(dimension);
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{dimensionId});
        }
    }

    @Override
    public DimensionBean deleteDimensionValue(DimensionBean dimensionBean) {
        List<DimensionValue> list = dimensionBean.getDimensionValue();
        List<DimensionValues> dimensionValueList =
                list.stream()
                        .map((DimensionValue dimValue) -> {
                            try {
                                return (DimensionValue) this.getById(dimValue.getId());
                            } catch (ResourceNotFound resourceNotFound) {
                                resourceNotFound.printStackTrace();
                            }
                            return null;
                        }).map((DimensionValue dimValue) -> {
                    this.dimensionValueRepository.deleteById(dimValue.getId());
                    this.searchDimensionValueRepository.deleteById(dimValue.getId());
                    return new DimensionValues(dimValue);
                }).collect(Collectors.toList());
        dimensionBean.setDimensionValues(dimensionValueList);
        return dimensionBean;
    }

    @Override
    public DimensionBean getDimensionAndDimensionValue(Long dimId) throws ResourceNotFound {
        DimensionBean dimensionBean = null;
        if (this.dimensionRepository.existsById(dimId)) {
            dimensionBean = getDimensionAndValue(dimId);
            if (dimensionBean.getHierarchical()) {
                dimensionBean = this.getDimensionHierarchy(dimensionBean);
            }
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{dimId});
        }
        return dimensionBean;
    }

    @Override
    public List<DimensionBean> getDimensionAndValues() {
        List<DimensionBean> response = new LinkedList<>();
        Iterable<Dimension> dimensionIterable = this.dimensionRepository.findAll();
        dimensionIterable.forEach((Dimension dimension) -> {
            DimensionBean dimensionBean = new DimensionBean(dimension);
            List<DimensionValue> dimensionValueList =
                    this.dimensionValueRepository.findByDimId(dimension.getDimId());
            List<DimensionValues> dimensionValues =
                    dimensionValueList
                            .stream()
                            .map((DimensionValue value) -> new DimensionValues(value))
                            .collect(Collectors.toList());
            dimensionBean.setDimensionValues(dimensionValues);
            response.add(dimensionBean);
        });
        return response;
    }

    private List<DimensionValues> saveDimensionValues(DimensionBean dimensionBean) {
        List<DimensionValues> dimensionValues = dimensionBean.getDimensionValue().stream()
                .map((dimensionValue) -> this.dimensionValueRepository.save(dimensionValue))
                .map((dimensionValue) -> this.searchDimensionValueRepository.save(dimensionValue))
                .map((dimensionValue -> new DimensionValues(dimensionValue)))
                .collect(Collectors.toList());
        return dimensionValues;
    }

    private DimensionBean getDimensionAndValue(Long dimId) throws ResourceNotFound {
        DimensionBean dimensionBean = new DimensionBean();
        if (this.dimensionRepository.existsById(dimId)) {
            Optional<Dimension> dimensionOptional = this.dimensionRepository.findById(dimId);
            if (dimensionOptional.isPresent()) {
                Dimension dimension = dimensionOptional.get();
                dimensionBean.setDimension(dimension);
                List<DimensionValue> response = this.dimensionValueRepository.findByDimId(dimension.getDimId());
                List<DimensionValues> dimensionValues = response.stream()
                        .map((dimensionValue -> new DimensionValues(dimensionValue)))
                        .collect(Collectors.toList());
                dimensionBean.setDimensionValues(dimensionValues);
            }
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{dimId});
        }
        return dimensionBean;
    }


    @Override
    public AbstractEntity getRoot() {
        return this.dimensionRepository.findByDimName(ApplicationUtil.DIMENSION);
    }

    @Override
    public List<AbstractEntity> getNextLevelChildren(Long id) {
        List<Dimension> dimensionList = this.dimensionRepository.findByParentDimId(id);
        List<AbstractEntity> list = new LinkedList<>();
        List<AbstractEntity> listChild =
                dimensionList.stream()
                        .map((Dimension dimension) -> {
                            list.add(dimension);
                            return dimension;
                        })
                        .flatMap((Dimension dimension) ->
                                this.dimensionValueRepository.findByDimId(dimension.getDimId())
                                        .stream())
                        .collect(Collectors.toList());
        list.addAll(listChild);
        return list;
    }

    @Override
    public List<AbstractEntity> getAllLevelChildren(Long id)
            throws ResourceNotFound {
        Dimension dimensionObject = (Dimension) this.getById(id);
        List<Dimension> dimensionList = this.dimensionRepository.findByLevelGreaterThan(dimensionObject.getLevel());
        List<AbstractEntity> list = new LinkedList<>();
        List<AbstractEntity> listChild =
                dimensionList.stream()
                        .map((Dimension dimension) -> {
                            list.add(dimension);
                            return dimension;
                        })
                        .flatMap((Dimension dimension) ->
                                this.dimensionValueRepository.findByDimId(dimension.getDimId())
                                        .stream())
                        .collect(Collectors.toList());
        list.addAll(listChild);
        return list;
    }

    @Override
    public AbstractEntity getById(Long id) throws ResourceNotFound {
        Dimension dimension = null;
        if (this.dimensionRepository.existsById(id)) {
            Optional<Dimension> dimensionOptional = this.dimensionRepository.findById(id);
            if (dimensionOptional.isPresent()) {
                dimension = dimensionOptional.get();
                dimension.setChildren(this.dimensionValueRepository.findByDimId(dimension.getDimId()));
            } else {
                throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{id});
            }
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{id});
        }
        return dimension;
    }

    private DimensionBean getDimensionHierarchy(DimensionBean dimensionBean) throws ResourceNotFound {
        List<Dimension> dimensionList = this.dimensionRepository.findByParentDimId(dimensionBean.getDimId());
        List<DimensionBean> children = new LinkedList<>();
        for (Dimension dimensionElement : dimensionList) {
            DimensionBean childDimensionBean = this.getDimensionAndValue(dimensionElement.getDimId());
            children.add(childDimensionBean);
            this.getDimensionHierarchy(childDimensionBean);
        }
        dimensionBean.setChildDimensionBean(children);
        return dimensionBean;
    }

    @Override
    public List<DimensionValue> searchByKeyWord(String keyWord) throws ResourceNotFound {
        Page<DimensionValue> dimensionValuePage = this.searchDimensionValueRepository
                .findByDimValNameContaining(keyWord, new PageRequest(0, 1000));
        if (dimensionValuePage.getTotalElements() > 0) {
            return dimensionValuePage.getContent();
        } else {
            throw new ResourceNotFound(DIMENSION_NOT_FOUND, new Object[]{keyWord});
        }
    }
}
