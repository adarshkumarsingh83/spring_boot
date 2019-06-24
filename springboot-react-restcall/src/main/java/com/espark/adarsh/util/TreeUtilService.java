package com.espark.adarsh.util;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TreeUtilService {

    public CategoryBean getTree(Category rootCategory, List<Category> childCategory){
        return this.getTreeInternal(rootCategory,childCategory);
    }

    private CategoryBean getTreeInternal(Category rootCategory, List<Category> childCategory){
        CategoryBean categoryBean= new CategoryBean();
        if(childCategory!=null && !childCategory.isEmpty()){
            Map<Long,List<Category>> groupedChild =
                    childCategory.stream()
                    .filter(category -> category!=null)
                    .collect(Collectors.groupingBy(category -> category.getParentCatId()));
            List<Category> child = groupedChild.get(rootCategory.getParentCatId());
            groupedChild.remove(rootCategory.getParentCatId());
            categoryBean= getTreeHierarchy(null,child,groupedChild);
        }
        return categoryBean;
    }

    private CategoryBean getTreeHierarchy(CategoryBean categoryBean,List<Category> directChild
            ,Map<Long,List<Category>> groupedTotalChild ){
        if(directChild!=null && !directChild.isEmpty()){
            for(Category childCategory :directChild){
                CategoryBean childCategoryBean = new CategoryBean(childCategory);
                if(categoryBean!=null) {
                    categoryBean.setChildCategoryBean(childCategoryBean);
                }else{
                    categoryBean=childCategoryBean;
                }
                List<Category> child = groupedTotalChild.get(childCategory.getCatId());
                groupedTotalChild.remove(childCategory.getCatId());
                this.getTreeHierarchy(childCategoryBean,child,groupedTotalChild);
            }
        }
        return categoryBean;
    }
}
