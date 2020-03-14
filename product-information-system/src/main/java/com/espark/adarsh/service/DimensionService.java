package com.espark.adarsh.service;

import com.espark.adarsh.bean.DimensionBean;
import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.exception.ResourceNotFound;

import java.util.List;

public interface DimensionService extends AbstractService{

    DimensionBean findByDimName(String dimName) throws ResourceNotFound;

    DimensionBean saveDimension(DimensionBean dimensionBean);

    DimensionBean saveDimensionValue(DimensionBean dimensionBean) throws ResourceNotFound;

    DimensionBean saveDimensionAndDimensionValue(DimensionBean dimensionBean);

    DimensionBean updateDimensionAndDimensionValue(Long dimensionId,DimensionBean dimensionBean);

    DimensionBean getDimensionAndDimensionValue(Long dimeId) throws ResourceNotFound;

    DimensionBean deleteDimension(Long dimensionId) throws ResourceNotFound;

    DimensionBean deleteDimensionValue(DimensionBean dimensionBean);

    List<DimensionBean> getDimensionAndValues();

}
