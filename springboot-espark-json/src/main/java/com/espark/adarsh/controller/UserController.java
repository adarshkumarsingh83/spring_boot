package com.espark.adarsh.controller;

import com.espark.adarsh.bean.AbstractBean;
import com.espark.adarsh.bean.EsparkRequestBean;
import com.espark.adarsh.bean.EsparkResponseBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.POST,value = "/save")
    public @ResponseBody EsparkResponseBean save(@RequestBody EsparkRequestBean esparkRequestBean){
        AbstractBean data=esparkRequestBean.getData();
        EsparkResponseBean esparkResponseBean= new EsparkResponseBean();
        esparkResponseBean.setData(data);
        esparkResponseBean.setMessage("Data Saved");
        return esparkResponseBean;
    }

}
