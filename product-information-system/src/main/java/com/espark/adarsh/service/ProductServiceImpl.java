package com.espark.adarsh.service;

import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.entity.Product;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.repostiory.db.ProductRepository;
import com.espark.adarsh.repostiory.solr.SearchProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.espark.adarsh.util.ApplicationUtil.*;

@Slf4j
@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private SearchService searchService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired(required = false)
    private SearchProductRepository searchProductRepository;

    @Override
    public ProductBean saveProduct(ProductBean productBean) {
        Product product = this.productRepository.save(productBean.getProduct());
        this.searchProductRepository.save(product);
        return new ProductBean(product);
    }

    @Override
    public ProductBean updateProduct(Long productId, ProductBean productBean) {
        Product product = this.productRepository.save(productBean.getProduct());
        this.searchProductRepository.save(product);
        return new ProductBean(product);
    }

    @Override
    public ProductBean getProduct(Long productId) throws ResourceNotFound {
        Product product = null;
        if (this.productRepository.existsById(productId)) {
            product = this.productRepository.getOne(productId);
        } else {
            throw new ResourceNotFound(PRODUCT_NOT_FOUND, new Object[]{productId});
        }
        return new ProductBean(product);
    }

    @Override
    public ProductBean getProduct(String productName) throws ResourceNotFound {
        Product product = this.productRepository.findByProductName(productName);
        if (product == null) {
            throw new ResourceNotFound(PRODUCT_NOT_FOUND, new Object[]{productName});
        }
        return new ProductBean(product);
    }

    @Override
    public ProductBean deleteProduct(Long productId) throws ResourceNotFound {
        Product product = null;
        if (this.productRepository.existsById(productId)) {
            product = this.productRepository.getOne(productId);
            this.productRepository.deleteById(productId);
            this.searchProductRepository.deleteById(productId);
        } else {
            throw new ResourceNotFound(PRODUCT_NOT_FOUND, new Object[]{productId});
        }
        return new ProductBean(product);
    }

    @Override
    public AbstractEntity getById(Long productId) throws ResourceNotFound {
        Product product = null;
        if (this.productRepository.existsById(productId)) {
            product = this.productRepository.getOne(productId);
        } else {
            throw new ResourceNotFound(PRODUCT_NOT_FOUND, new Object[]{productId});
        }
        return product;
    }

    @Override
    public List<ProductBean> searchProduct(String searchTerm, int start, int max) throws ResourceNotFound {
        List<AbstractEntity> list = this.searchService.getListSearchResult(searchTerm, PRODUCT, start, max);
        List<ProductBean> response = list.stream()
                .map((AbstractEntity bean) -> new ProductBean((Product) bean))
                .collect(Collectors.toList());
        return response;
    }

}

