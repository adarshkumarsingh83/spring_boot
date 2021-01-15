package com.espark.adarsh.content;

import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.bean.DimensionBean;
import com.espark.adarsh.bean.DimensionBean.DimensionValues;
import com.espark.adarsh.bean.ProductBean;
import com.espark.adarsh.entity.AbstractEntity.Type;
import com.espark.adarsh.entity.Category;
import com.espark.adarsh.entity.DimensionValue;
import com.espark.adarsh.entity.Product;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.CategoryService;
import com.espark.adarsh.service.DimensionService;
import com.espark.adarsh.service.ProductService;
import com.espark.adarsh.util.ApplicationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ContentInitializer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DimensionService dimensionService;

    private static Map<String, CategoryBean> categoryMap = new HashMap<>();
    private static Map<String, DimensionBean> dimensionMap = new HashMap<>();
    private static Map<String, DimensionValue> dimensionValueMap = new HashMap<>();
    private static Map<String, ProductBean> productMap = new HashMap<>();

    @PostConstruct
    public void init() {
        this.initCategory();
        this.initDimensionAndValues();
        this.initProduct();
    }

    private void initCategory() {

        /*
            -- Category --

            Department
              |--Home
              |    |--Furniture
              |    |    |-Living Room
              |    |    |   |-Sofa
              |    |    |   |-Almira
              |    |    |
              |    |    |-Bed Room
              |    |    |   |-Bed
              |    |    |   |-Matres
              |    |    |
              |    |    |-Kitchen
              |    |    |   |-Containers
              |    |    |   |-Crockery
              |    |    |
              |    |-Electronic
              |         |-TV
              |         |-Fridge
              |         |-Washing Machine
              |
              |-- Mens
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
              |     |
              |-- Womens
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
              |
              |-- Kid
              |     |-Shirt
              |     |   |-FullShirt
              |     |   |-HalfShirt
              |     |
              |     |-Pant
              |     |   |-FullPant
              |     |   |-HalfPant
              |     |
              |     |-Watch
              |     |   |-AnalogWatch
              |     |   |-DigitalWatch
        */

        Category department = new Category(ApplicationUtil.HOME, null);
        department.setCatId(1L);
        department = this.saveOrUpdateCategory(department);

        Category home = new Category("home", department);
        home = this.saveOrUpdateCategory(home);

        Category furniture = new Category("furniture", home);
        furniture = this.saveOrUpdateCategory(furniture);

        Category livingRoom = new Category("living-room", furniture);
        livingRoom = this.saveOrUpdateCategory(livingRoom);

        Category sofa = new Category("sofa", livingRoom);
        sofa = this.saveOrUpdateCategory(sofa);

        Category almira = new Category("almira", livingRoom);
        almira = this.saveOrUpdateCategory(almira);

        Category bedRoom = new Category("bed-room", furniture);
        bedRoom = this.saveOrUpdateCategory(bedRoom);

        Category bedFrame = new Category("bed-frame", bedRoom);
        bedFrame = this.saveOrUpdateCategory(bedFrame);

        Category matress = new Category("matress", bedRoom);
        matress = this.saveOrUpdateCategory(matress);

        Category kitchen = new Category("kitchen", furniture);
        kitchen = this.saveOrUpdateCategory(kitchen);


        Category electronic = new Category("electronic", home);
        electronic = this.saveOrUpdateCategory(electronic);


        Category men = new Category("men", department);
        men = this.saveOrUpdateCategory(men);

        Category menShirt = new Category("mens-shirt", men);
        menShirt = this.saveOrUpdateCategory(menShirt);
        Category menFullShirt = new Category("mens-full-shirt", menShirt);
        menFullShirt = this.saveOrUpdateCategory(menFullShirt);
        Category menHalfShirt = new Category("mens-half-shirt", menShirt);
        menHalfShirt = this.saveOrUpdateCategory(menHalfShirt);

        Category menPant = new Category("mens-pant", men);
        menPant = this.saveOrUpdateCategory(menPant);
        Category menFullPant = new Category("mens-full-pant", menPant);
        menFullPant = this.saveOrUpdateCategory(menFullPant);
        Category menHalfPant = new Category("mens-half-pant", menPant);
        menHalfPant = this.saveOrUpdateCategory(menHalfPant);

        Category menWatch = new Category("mens-watch", men);
        menWatch = this.saveOrUpdateCategory(menWatch);
        Category menDigitalWatch = new Category("mens-digital-watch", menWatch);
        menDigitalWatch = this.saveOrUpdateCategory(menDigitalWatch);
        Category menAnalogWatch = new Category("mens-analog-watch", menWatch);
        menAnalogWatch = this.saveOrUpdateCategory(menAnalogWatch);

        Category women = new Category("women", department);
        women = this.saveOrUpdateCategory(women);

        Category womenShirt = new Category("womens-shirt", women);
        womenShirt = this.saveOrUpdateCategory(womenShirt);
        Category womenFullShirt = new Category("womens-full-shirt", womenShirt);
        womenFullShirt = this.saveOrUpdateCategory(womenFullShirt);
        Category womenHalfShirt = new Category("womens-half-shirt", womenShirt);
        womenHalfShirt = this.saveOrUpdateCategory(womenHalfShirt);

        Category womenPant = new Category("womens-pant", women);
        womenPant = this.saveOrUpdateCategory(womenPant);
        Category womenFullPant = new Category("womens-full-pant", womenPant);
        womenFullPant = this.saveOrUpdateCategory(womenFullPant);
        Category womenHalfPant = new Category("womens-half-pant", womenPant);
        womenHalfPant = this.saveOrUpdateCategory(womenHalfPant);

        Category womenWatch = new Category("womens-watch", women);
        womenWatch = this.saveOrUpdateCategory(womenWatch);
        Category womenDigitalWatch = new Category("womens-digital-watch", womenWatch);
        womenDigitalWatch = this.saveOrUpdateCategory(womenDigitalWatch);
        Category womenAnalogWatch = new Category("womens-analog-watch", womenWatch);
        womenAnalogWatch = this.saveOrUpdateCategory(womenAnalogWatch);

        Category kid = new Category("kid", department);
        kid = this.saveOrUpdateCategory(kid);

        Category kidShirt = new Category("kid-shirt", kid);
        kidShirt = this.saveOrUpdateCategory(kidShirt);
        Category kidFullShirt = new Category("kid-full-shirt", kidShirt);
        kidFullShirt = this.saveOrUpdateCategory(kidFullShirt);
        Category kidHalfShirt = new Category("kid-half-shirt", kidShirt);
        kidHalfShirt = this.saveOrUpdateCategory(kidHalfShirt);

        Category kidPant = new Category("kid-pant", kid);
        kidPant = this.saveOrUpdateCategory(kidPant);
        Category kidFullPant = new Category("kid-full-pant", kidPant);
        kidFullPant = this.saveOrUpdateCategory(kidFullPant);
        Category kidHalfPant = new Category("kid-half-pant", kidPant);
        kidHalfPant = this.saveOrUpdateCategory(kidHalfPant);

        Category kidWatch = new Category("kid-watch", kid);
        kidWatch = this.saveOrUpdateCategory(kidWatch);
        Category kidDigitalWatch = new Category("kid-digital-watch", kidWatch);
        kidDigitalWatch = this.saveOrUpdateCategory(kidDigitalWatch);
        Category kidAnalogWatch = new Category("kid-analog-watch", kidWatch);
        kidAnalogWatch = this.saveOrUpdateCategory(kidAnalogWatch);


        Category office = new Category("office", department);
        office = this.saveOrUpdateCategory(office);

        Category stationary = new Category("stationary", office);
        stationary = this.saveOrUpdateCategory(stationary);

        Category books = new Category("books", stationary);
        books = this.saveOrUpdateCategory(books);

        Category pen = new Category("pen", stationary);
        pen = this.saveOrUpdateCategory(pen);

        Category pencil = new Category("pencil", stationary);
        pencil = this.saveOrUpdateCategory(pencil);
    }

    private Category saveOrUpdateCategory(Category category) {
        Category currentCategory = null;
        try {
            currentCategory = this.categoryService.findByCatName(category.getCatName());
            category.setCatId(currentCategory.getCatId());
            CategoryBean currentCategoryBean =
                    this.categoryService.updateCategory(currentCategory.getCatId(), new CategoryBean(category));
            categoryMap.put(currentCategoryBean.getCatName(), currentCategoryBean);
            return currentCategory;
        } catch (ResourceNotFound resourceNotFound) {
            logger.error("Exception Occurred ex={}", resourceNotFound);
        }
        CategoryBean bean = new CategoryBean(category);
        bean = this.categoryService.saveCategory(bean);
        categoryMap.put(bean.getCatName(), bean);
        return bean.getCategory();
    }


    public void initDimensionAndValues() {
        DimensionBean dimensionBean = new DimensionBean();
        dimensionBean.setDimName(ApplicationUtil.DIMENSION);
        dimensionBean.setHierarchical(true);
        dimensionBean.setParentDimId(0L);
        dimensionBean.setDimPath("");
        dimensionBean.setLevel(0);
        DimensionBean baseDimension = this.saveOrUpdateDimension(dimensionBean);

        final DimensionBean colorDimensionBean = new DimensionBean();
        colorDimensionBean.setDimName("color");
        colorDimensionBean.setHierarchical(false);
        colorDimensionBean.setLevel(dimensionBean.getLevel() + 1);
        colorDimensionBean.setParentDimId(baseDimension.getDimId());
        colorDimensionBean.setDimPath(baseDimension.getDimPath());
        List<DimensionValues> colorValues = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "red", colorDimensionBean.getDimPath()));
                add(new DimensionValues(null, "green", colorDimensionBean.getDimPath()));
                add(new DimensionValues(null, "yellow", colorDimensionBean.getDimPath()));
            }
        };
        colorDimensionBean.setDimensionValues(colorValues);
        this.saveOrUpdateDimension(colorDimensionBean);

        final DimensionBean brandDimensionBean = new DimensionBean();
        brandDimensionBean.setDimName("brand");
        brandDimensionBean.setHierarchical(false);
        brandDimensionBean.setParentDimId(baseDimension.getDimId());
        brandDimensionBean.setLevel(dimensionBean.getLevel() + 1);
        brandDimensionBean.setDimPath(baseDimension.getDimPath());
        List<DimensionValues> brandValues = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "adidas", brandDimensionBean.getDimPath()));
                add(new DimensionValues(null, "nike", brandDimensionBean.getDimPath()));
                add(new DimensionValues(null, "puma", brandDimensionBean.getDimPath()));
                add(new DimensionValues(null, "reebok", brandDimensionBean.getDimPath()));
            }
        };
        brandDimensionBean.setDimensionValues(brandValues);
        this.saveOrUpdateDimension(brandDimensionBean);


        final DimensionBean priceDimensionBean = new DimensionBean();
        priceDimensionBean.setDimName("price");
        priceDimensionBean.setHierarchical(false);
        priceDimensionBean.setParentDimId(baseDimension.getDimId());
        priceDimensionBean.setLevel(dimensionBean.getLevel() + 1);
        priceDimensionBean.setDimPath(baseDimension.getDimPath());
        List<DimensionValues> priceValues = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "1-10", priceDimensionBean.getDimPath()));
                add(new DimensionValues(null, "11-20", priceDimensionBean.getDimPath()));
                add(new DimensionValues(null, "21-30", priceDimensionBean.getDimPath()));
                add(new DimensionValues(null, "31-40", priceDimensionBean.getDimPath()));
                add(new DimensionValues(null, "41-50", priceDimensionBean.getDimPath()));
            }
        };
        priceDimensionBean.setDimensionValues(priceValues);
        this.saveOrUpdateDimension(priceDimensionBean);

        final DimensionBean hierarchicalDimension1 = new DimensionBean();
        hierarchicalDimension1.setDimName("level1-dim");
        hierarchicalDimension1.setHierarchical(true);
        hierarchicalDimension1.setParentDimId(baseDimension.getDimId());
        hierarchicalDimension1.setLevel(baseDimension.getLevel() + 1);
        hierarchicalDimension1.setDimPath(baseDimension.getDimPath());
        List<DimensionValues> hierarchicalDimensionValues1 = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "h1.1", hierarchicalDimension1.getDimPath()));
                add(new DimensionValues(null, "h1.2", hierarchicalDimension1.getDimPath()));
            }
        };
        hierarchicalDimension1.setDimensionValues(hierarchicalDimensionValues1);
        DimensionBean savedHierarchicalDimension1 = this.saveOrUpdateDimension(hierarchicalDimension1);


        final DimensionBean hierarchicalDimension2 = new DimensionBean();
        hierarchicalDimension2.setDimName("level2-dim");
        hierarchicalDimension2.setHierarchical(true);
        hierarchicalDimension2.setParentDimId(savedHierarchicalDimension1.getDimId());
        hierarchicalDimension2.setLevel(savedHierarchicalDimension1.getLevel() + 1);
        hierarchicalDimension2.setDimPath(savedHierarchicalDimension1.getDimPath());
        List<DimensionValues> hierarchicalDimensionValues2 = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "h2.1", hierarchicalDimension2.getDimPath()));
                add(new DimensionValues(null, "h2.2", hierarchicalDimension2.getDimPath()));
            }
        };
        hierarchicalDimension2.setDimensionValues(hierarchicalDimensionValues2);
        DimensionBean savedHierarchicalDimension2 = this.saveOrUpdateDimension(hierarchicalDimension2);


        final DimensionBean hierarchicalDimension3 = new DimensionBean();
        hierarchicalDimension3.setDimName("level3-dim");
        hierarchicalDimension3.setHierarchical(true);
        hierarchicalDimension3.setParentDimId(savedHierarchicalDimension2.getDimId());
        hierarchicalDimension3.setLevel(savedHierarchicalDimension2.getLevel() + 1);
        hierarchicalDimension3.setDimPath(savedHierarchicalDimension2.getDimPath());
        List<DimensionValues> hierarchicalDimensionValues3 = new LinkedList<DimensionValues>() {
            {
                add(new DimensionValues(null, "h3.1", hierarchicalDimension3.getDimPath()));
                add(new DimensionValues(null, "h3.2", hierarchicalDimension3.getDimPath()));
            }
        };
        hierarchicalDimension3.setDimensionValues(hierarchicalDimensionValues3);
        this.saveOrUpdateDimension(hierarchicalDimension3);
    }

    private DimensionBean saveOrUpdateDimension(DimensionBean dimensionBean) {
        DimensionBean currentDimension = null;
        try {
            currentDimension = this.dimensionService.findByDimName(dimensionBean.getDimName());
            dimensionBean.setDimId(currentDimension.getDimId());
            currentDimension = this.dimensionService.updateDimensionAndDimensionValue(currentDimension.getDimId(), dimensionBean);
            dimensionMap.put(currentDimension.getDimName(), currentDimension);
            return currentDimension;
        } catch (ResourceNotFound resourceNotFound) {
            logger.error("Exception Occurred ex={}", resourceNotFound);
        }
        dimensionBean = this.dimensionService.saveDimensionAndDimensionValue(dimensionBean);
        dimensionMap.put(dimensionBean.getDimName(), dimensionBean);
        return dimensionBean;
    }


    public void initProduct() {
        Product shirtNikeRedColor = new Product();
        shirtNikeRedColor.setProductName("nike-red-shirt");
        shirtNikeRedColor.setDimension(this.getDimensionValue("brand", "nike"));
        this.saveOrUpdateProduct(new ProductBean(shirtNikeRedColor));

        Product shirtAdidasRedColor = new Product();
        shirtAdidasRedColor.setProductName("adidad-red-shirt");
        shirtAdidasRedColor.setDimension(this.getDimensionValue("brand", "adidas"));
        this.saveOrUpdateProduct(new ProductBean(shirtAdidasRedColor));

        Product shirtPumaRedColor = new Product();
        shirtPumaRedColor.setProductName("puma-red-shirt");
        shirtPumaRedColor.setDimension(this.getDimensionValue("brand", "puma"));
        this.saveOrUpdateProduct(new ProductBean(shirtPumaRedColor));
    }

    private Long getDimensionValue(String dimensionName, String valueName) {
        DimensionBean dimension = dimensionMap.get(dimensionName);
        List<DimensionValue> dimensionValues = new LinkedList<>();
        if (dimension != null) {
            dimensionValues = (List<DimensionValue>) dimension.getDimensionValue()
                    .stream()
                    .filter((Object dimVal) -> ((DimensionValue) dimVal).getType().equals(Type.dimensionValue))
                    .filter((Object dimVal) -> ((DimensionValue) dimVal).getDimValName().equals(valueName))
                    .collect(Collectors.toList());
        }

        return (dimensionValues != null && dimensionValues.size() > 0) ? dimensionValues.get(0).getId() : 0l;
    }


    private ProductBean saveOrUpdateProduct(ProductBean productBean) {

        try {
            ProductBean currentProductBean = this.productService.getProduct(productBean.getProductName());
            productMap.put(currentProductBean.getProductName(), currentProductBean);
            productBean.setProdId(currentProductBean.getProdId());
            currentProductBean = this.productService.updateProduct(currentProductBean.getProdId(), productBean);
            return currentProductBean;
        } catch (ResourceNotFound resourceNotFound) {
            logger.error("Exception Occurred ex={}", resourceNotFound);
        }
        productBean = this.productService.saveProduct(productBean);
        productMap.put(productBean.getProductName(), productBean);
        return productBean;
    }
}
