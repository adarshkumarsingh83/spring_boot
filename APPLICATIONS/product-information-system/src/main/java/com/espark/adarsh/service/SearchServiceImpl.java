package com.espark.adarsh.service;

import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.entity.Dimension;
import com.espark.adarsh.entity.DimensionValue;
import com.espark.adarsh.entity.Product;
import com.espark.adarsh.entity.TreeNode;
import com.espark.adarsh.exception.ApplicationException;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.repostiory.solr.SearchCategoryRepository;
import com.espark.adarsh.repostiory.solr.SearchDimensionRepository;
import com.espark.adarsh.repostiory.solr.SearchDimensionValueRepository;
import com.espark.adarsh.repostiory.solr.SearchProductRepository;
import com.espark.adarsh.util.TreeClimberUtil;
import com.espark.adarsh.util.TreeClimberUtil.TreeClimber;
import com.espark.adarsh.util.TreeUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.espark.adarsh.util.ApplicationUtil.*;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchCategoryRepository searchCategoryRepository;

    @Autowired
    private SearchProductRepository searchProductRepository;

    @Autowired
    private SearchDimensionRepository searchDimensionRepository;

    @Autowired
    private SearchDimensionValueRepository searchDimensionValueRepository;

    @Autowired
    private TreeClimberUtil treeClimberUtil;

    @Autowired
    private TreeUtilService treeUtilService;

    @Autowired
    private DimensionService dimensionService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public List<AbstractEntity> getListSearchResult(String searchTerm, String type, int startIndex, int maxSize) {
        List<AbstractEntity> responseList = new LinkedList<>();
        Page page1 = null;
        Page page2 = null;

        switch (type) {
            case CATEGORY:
                page1 = this.searchCategory(searchTerm, startIndex, maxSize);
                break;

            case PRODUCT:
                page1 = this.searchProduct(searchTerm, startIndex, maxSize);
                break;

            case PRODUCT_CATEGORY:
                page1 = this.searchCategory(searchTerm, startIndex, maxSize);
                page2 = this.searchProduct(searchTerm, startIndex, maxSize);
                break;

            case DIMENSION:
                page1 = this.searchDimension(searchTerm, startIndex, maxSize);
                break;
        }

        if ((page1 != null && page1.getTotalElements() > 0) && (page2 == null || page2.getTotalElements() == 0)) {
            responseList = ((List<AbstractEntity>) page1.getContent()).stream()
                    .collect(Collectors.toList());
        }

        if ((page1 != null && page1.getTotalElements() > 0) && (page2 != null && page2.getTotalElements() > 0)) {
            responseList = Stream.concat(((List<AbstractEntity>) page1.getContent()).stream()
                    , ((List<AbstractEntity>) page2.getContent()).stream())
                    .collect(Collectors.toList());
        }

        return responseList;
    }

    @Override
    public TreeNodeBean getTreeSearchResult(String searchTerm, String type) throws ResourceNotFound {
        List<TreeNodeBean> response = this.getSearchResult(searchTerm, type, 0, Integer.MAX_VALUE);
        TreeClimber treeClimber = treeClimberUtil.getTreeClimber();
        AbstractEntity parent = null;
        for (TreeNodeBean treeNodeBean : response) {
            parent = treeClimber.withNode((AbstractEntity) treeNodeBean.getTreeNode(), true)
                    .withPredicate((abstractEntity) -> ((AbstractEntity) abstractEntity).getParent() == 0)
                    .withSelfClimb(true).climb();
        }
        if (parent != null) {
            response.clear();
            List<AbstractEntity> list = treeClimberUtil.getAllData(treeClimber);
            response = list.stream()
                    .map((AbstractEntity entity) -> new TreeNodeBean((TreeNode) entity))
                    .collect(Collectors.toList());
            TreeNodeBean treeNode = this.treeUtilService.getTree(new TreeNodeBean((TreeNode) parent), response);
            return treeNode;
        } else {
            throw new ResourceNotFound(DATA_NOT_FOUND, new Object[]{searchTerm});
        }
    }


    public List<TreeNodeBean> getSearchResult(String searchTerm, String type, int startIndex, int maxSize) {
        List<TreeNodeBean> responseList = new LinkedList<>();
        switch (type) {
            case CATEGORY:
                this.searchCategory(searchTerm, startIndex, maxSize, responseList);
                break;

            case PRODUCT:
                this.searchProduct(searchTerm, startIndex, maxSize, responseList);
                break;

            case PRODUCT_CATEGORY:
                this.searchCategory(searchTerm, startIndex, maxSize, responseList);
                this.searchProduct(searchTerm, startIndex, maxSize, responseList);
                break;

            case DIMENSION:
                this.searchDimension(searchTerm, startIndex, maxSize, responseList);
                break;
        }
        return responseList;
    }


    private void searchProduct(String searchTerm, int startIndex, int maxSize, List<TreeNodeBean> responseList) {
        Page<Product> productPagesList = this.searchProduct(searchTerm, startIndex, maxSize);
        if (productPagesList != null && productPagesList.getTotalElements() > 0) {
            productPagesList.stream().forEach((Product product) -> responseList.add(new TreeNodeBean(product)));
        }
    }

    private void searchCategory(String searchTerm, int startIndex, int maxSize, List<TreeNodeBean> responseList) {
        Page<Category> categoriesPagesList = this.searchCategory(searchTerm, startIndex, maxSize);
        if (categoriesPagesList != null && categoriesPagesList.getTotalElements() > 0) {
            categoriesPagesList.getContent().stream().forEach((Category category) -> responseList.add(new TreeNodeBean(category)));
        }
    }

    private void searchDimension(String searchTerm, int startIndex, int maxSize, List<TreeNodeBean> responseList) {
        Page<Dimension> dimensionPage = searchDimension(searchTerm, startIndex, maxSize);
        if (dimensionPage != null && dimensionPage.getTotalElements() > 0) {
            dimensionPage.getContent().stream().forEach((Dimension dimension) -> {
                responseList.add(new TreeNodeBean(dimension));
                responseList.addAll(this.getDimensionValues(dimension.getDimId(), 0, 10000));
            });
        }
    }

    private Page<Product> searchProduct(String searchTerm, int startIndex, int maxSize) {
        Page<Product> productPagesList =
                this.searchProductRepository.findByProductNameContaining(searchTerm
                        , new PageRequest(startIndex, maxSize));
        return productPagesList;
    }

    private Page<Category> searchCategory(String searchTerm, int startIndex, int maxSize) {
        Page<Category> categoriesPagesList =
                this.searchCategoryRepository.findByCatNameContaining(searchTerm
                        , new PageRequest(startIndex, maxSize));
        return categoriesPagesList;
    }

    private Page<Dimension> searchDimension(String searchTerm, int startIndex, int maxSize) {
        Page<Dimension> dimensionPageList = this.searchDimensionRepository.findByDimNameContaining(searchTerm
                , new PageRequest(startIndex, maxSize));
        return dimensionPageList;
    }

    private List<TreeNodeBean> getDimensionValues(Long dimId, int startIndex, int maxSize) {
        List<TreeNodeBean> dimValues = new LinkedList<>();
        Page<DimensionValue> dimensionValuePage =
                this.searchDimensionValueRepository.findByDimId(dimId, new PageRequest(startIndex, maxSize));
        if (dimensionValuePage != null && dimensionValuePage.getTotalElements() > 0) {
            dimValues = dimensionValuePage.getContent()
                    .stream()
                    .map((DimensionValue dimensionValue) -> new TreeNodeBean<>(dimensionValue))
                    .collect(Collectors.toList());
        }
        return dimValues;
    }

    @Override
    public List<ProductBean> searchProduct(String keywords) throws ResourceNotFound {
        List<ProductBean> products = new LinkedList<>();
        /*
        * STEP 0 BREAK ALL THE WORD AND CREATE A FUTURE AND SEND EVERY WORD TO SEARCH CATEGORY
        * - MATCH EVERY RESPONSE WITH PROVIDED SEARCH WORLD AND BASED ON
        * THE CRETAIN LEVEL OF MATCH PASS IT FOR DIMENSON SEARCH
        * - REMOVE THOSE MATCH WORLD WITH CATEGORY FROM REST OF THE SEARCH STRING
        * - PASS ALL THOSE TO FIND THE DIMENSION WITH FOUNDED CATEGORY THEN DO THE SAME OPERAITON
        * - MATCH EVERY RESPONSE WITH PORVIDED SEARCH WORLD AND BASED ON
        * THE CRETAIN LEVEL OF MATCH PASS IT FOR DIMENSON SEARCH
        * THEN FIND THE PRODUCT BASED ON THE FOUNDED CATEGORY AND DIMENSION AND RETURN ALL THEM TO THE CALLER
        * EVERY THIGNS WILL BE DONE IN COMPLETABLE FUTURE OBJECT
        *
        * STEP 1 GET THE CATEGORY
        * STEP 2 GET THE DIMENSION BASED ON CATEGORY
        * STEP 3 GET THE PRODUCT BASED ON THE CATEGORY AND DIMENSIONS
        * STEP 4 WARP EVERY THIGNS IN THE PRODUCT BEAN AND RETURN
        * STEP 5 IF NO RECORD FOUND THEN THROW EXCEPTION RESOURCE NOT FOUND
        * */

        ForkJoinPool pool = new ForkJoinPool();
        /* FETCHING THE MATCHING CATEGORY */
        SearchTask<List<Category>, CategoryService> searchTaskCategory = new SearchTask(keywords, categoryService);
        List<Category> categoryList = pool.invoke(searchTaskCategory);

        /* FETCHING THE MATCHING DIMENSIONS VALUES  */
        SearchTask<List<DimensionValue>, DimensionService> searchTaskDimension = new SearchTask(keywords, dimensionService);
        List<DimensionValue> dimensionValueList = pool.invoke(searchTaskDimension);

        /* FETCHING THE MATCHING PRODUCTS VALUES BASED ON THE CATEGORY AND DIMENSIONS */

            /* BASED ON THE COMBINATION OF THE CATEGORY AND DIMENSIONS PRODUCT NEED TO FETCH FROM SOLR */
        List<Product> productList = new LinkedList<>();
        if (!categoryList.isEmpty() && !dimensionValueList.isEmpty()) {
            for (Category category : categoryList) {
                for (DimensionValue dimensionValue : dimensionValueList) {
                    if (category != null && dimensionValue != null) {
                        Page<Product> page = this.searchProductRepository.findByCatIdOrDimension(
                                category.getCatId()
                                , dimensionValue.getDimValId()
                                , new PageRequest(0, 10000));
                        productList.addAll(page.getContent());
                    }
                }
            }

        } else if (!categoryList.isEmpty() && dimensionValueList.isEmpty()) {
            if (!categoryList.isEmpty() && !dimensionValueList.isEmpty()) {
                for (Category category : categoryList) {
                    if (category != null) {
                        Page<Product> page = this.searchProductRepository.findByCatIdOrDimension(category.getCatId()
                                , new PageRequest(0, 10000));
                        productList.addAll(page.getContent());
                    }
                }
            }

        } else if (categoryList.isEmpty() && !dimensionValueList.isEmpty()) {
            for (DimensionValue dimensionValue : dimensionValueList) {
                if (dimensionValue != null) {
                    Page<Product> page = this.searchProductRepository.findByCatIdOrDimension(
                            dimensionValue.getDimValId()
                            , new PageRequest(0, 10000));
                    productList.addAll(page.getContent());
                }
            }
        }

        if (!productList.isEmpty()) {
            products = productList.stream()
                    .filter((Product product) -> product != null)
                    .map((Product product) -> new ProductBean(product))
                    .collect(Collectors.toList());
        }

        return products;
    }

    /**
     * @param <R> return type value which can be List<Category> or List<DimensionValue>
     * @param <S> services to invoke the task internally CategoryService or DimensionService
     *            Example:
     *            SearchTask<List<Category>,CategoryService> searchTaskCategory =new SearchTask("xxx",categoryService);
     *            SearchTask<List<DimensionValue>,DimensionService> searchTaskDimension =new SearchTask("xxx",dimensionService);
     *            <p>
     *            Example for Caller
     *            ForkJoinPool pool = new ForkJoinPool();
     *            <p>
     *            SearchTask<List<Category>,CategoryService> searchTaskCategory = new SearchTask("xxSEARCH TERMxx",categoryService);
     *            List<Category> list = pool.invoke(searchTaskCategory);
     *            OR
     *            SearchTask<List<DimensionValue>,DimensionService> searchTaskDimension =new SearchTask("xxSEARCH TERMxx",dimensionService);
     *            List<DimensionValue> list = pool.invoke(searchTaskDimension);
     */

    private static class SearchTask<R extends List, S extends AbstractService> extends RecursiveTask<List> {


        private String data;
        private S service;

        public SearchTask(String data, S service) {
            if (service != null) {
                this.service = service;
            } else {
                throw new ApplicationException("service can't be null");
            }
            if (!StringUtils.isEmpty(data)) {
                this.data = data.trim();
            } else {
                throw new ApplicationException("data can't be empty");
            }
        }

        @Override
        protected R compute() {
            R response = null;
            if (this.data != null && !this.data.isEmpty()) {
                if (this.data.length() > 0 && this.data.matches("\\s+")) {
                    String[] words = this.data.split("\\s+");
                    if (!ObjectUtils.isEmpty(words)) {
                        List<SearchTask<R, S>> subTasksList
                                = Arrays.asList(words)
                                .stream()
                                .map((String word) -> new SearchTask<R, S>(word, this.service))
                                .collect(Collectors.toList());
                        Collection<SearchTask<R, S>> searchTaskCollection = ForkJoinTask.invokeAll(subTasksList);
                        for (SearchTask<R, S> searchTask : searchTaskCollection) {
                            try {
                                response.addAll(searchTask.get());
                            } catch (InterruptedException | ExecutionException e) {
                                log.error("label=search exception={}", e);
                            }
                        }
                    }
                } else {
                    response = (R) this.processSearch(this.data);
                }
            }
            return response;
        }

        private R processSearch(String keyWord) {
            R data = null;
            try {
                data = (R) this.service.searchByKeyWord(keyWord);
            } catch (ResourceNotFound resourceNotFound) {
                log.error("label=search exception={}", resourceNotFound);
            }
            return data;
        }
    }
}
