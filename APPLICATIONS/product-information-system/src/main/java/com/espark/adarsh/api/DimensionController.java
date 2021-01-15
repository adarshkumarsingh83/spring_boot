package com.espark.adarsh.api;


import com.espark.adarsh.bean.CategoryBean;
import com.espark.adarsh.bean.DimensionBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.exception.ResourceNotFound;
import com.espark.adarsh.service.DimensionService;
import com.espark.adarsh.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.espark.adarsh.util.ApplicationUtil.*;

@RestController
public class DimensionController {

    @Autowired
    private MessageUtil messageUtil;

    @Autowired
    private DimensionService dimensionService;

    @GetMapping(value = "/dimension/{id}")
    public ResponseBean<DimensionBean, String> getDimension(@PathVariable("id") Long id)
            throws ResourceNotFound {
        DimensionBean categoryBeanResponse = this.dimensionService.getDimensionAndDimensionValue(id);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{DIMENSION, GET_DIMENSION}));
    }


    @GetMapping(value = "/dimension")
    public ResponseBean<List<DimensionBean>, String> getDimension() throws ResourceNotFound {
        List<DimensionBean> dimensionBeanList = this.dimensionService.getDimensionAndValues();
        return new ResponseBean(dimensionBeanList,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{DIMENSION, GET_DIMENSION}));
    }


    @PostMapping(value = "/dimension")
    public ResponseBean<DimensionBean, String> saveDimension(
            @RequestBody DimensionBean dimensionBean)
            throws ResourceNotFound {
        DimensionBean dimensionBeanResponse = this.dimensionService.saveDimension(dimensionBean);
        return new ResponseBean(dimensionBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{DIMENSION, POST_DIMENSION}));
    }


    @PutMapping(value = "/dimension/{id}")
    public ResponseBean<DimensionBean, String> updateDimension(
            @PathVariable("id") Long id,
            @RequestBody DimensionBean dimensionBean)
            throws ResourceNotFound {
        DimensionBean dimensionBeanResponse = this.dimensionService.updateDimensionAndDimensionValue(id, dimensionBean);
        return new ResponseBean(dimensionBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{DIMENSION, PUT_DIMENSION}));
    }

    @DeleteMapping(value = "/dimension/{id}")
    public ResponseBean<DimensionBean, String> deleteDimension(@PathVariable("id") Long id)
            throws ResourceNotFound {
        DimensionBean categoryBeanResponse = this.dimensionService.deleteDimension(id);
        return new ResponseBean(categoryBeanResponse,
                HttpStatus.OK, messageUtil.get(API_RESPONSE, new Object[]{DIMENSION, DELETE_DIMENSION}));
    }
}
