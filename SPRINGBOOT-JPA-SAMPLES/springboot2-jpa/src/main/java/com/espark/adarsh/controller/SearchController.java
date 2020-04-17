package com.espark.adarsh.controller;

import com.espark.adarsh.bean.RangeCriteria;
import com.espark.adarsh.bean.SearchBean;
import com.espark.adarsh.bean.SearchCriteria;
import com.espark.adarsh.entity.DbEntity;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private GenericRepository genericRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    @ResponseBody
    public List<DbEntity> search(@RequestBody SearchBean search) throws Exception {
        return genericRepository.search(search);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<DbEntity> search(@RequestParam(value = "searchAnd", required = false) String searchAnd
            ,@RequestParam(value = "searchOr", required = false) String searchOr
            ,@RequestParam(value = "searchBt", required = false) String searchBt) throws Exception {
        Pattern pattern = Pattern.compile("(\\w+?)(::|<|>|>=|=<|:eq:|:neq:|:lk:|:bt:)(\\w+?),");
        List<SearchCriteria> searchAndCriteria = new ArrayList<SearchCriteria>();
        if (searchAnd != null) {
            Matcher matcher = pattern.matcher(searchAnd + ",");
            while (matcher.find()) {
                searchAndCriteria.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        List<SearchCriteria> searchOrCriteria = new ArrayList<SearchCriteria>();
        if (searchOr != null) {
            Matcher matcher = pattern.matcher(searchOr + ",");
            while (matcher.find()) {
                searchOrCriteria.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }

        List<RangeCriteria> rangeCriteria=new ArrayList<>();
        if(searchBt!=null){
            Pattern patternBetween = Pattern.compile("(\\w+?)(:bt:)(\\d{4}-[01]\\d-[0-3]\\d\\s\\d{2}:\\d{2}:\\d{2})(::)(\\d{4}-[01]\\d-[0-3]\\d\\s\\d{2}:\\d{2}:\\d{2})(::)(\\w+?)(::)(\\w+?),");
            Matcher matcher = patternBetween.matcher(searchBt + ",");
            while (matcher.find()) {
                rangeCriteria.add(new RangeCriteria(matcher.group(1), matcher.group(3), matcher.group(5)
                        ,Boolean.valueOf(matcher.group(7)),Boolean.valueOf(matcher.group(9))));
            }
        }
        SearchBean searchBean =new SearchBean();
        searchBean.setClassType(Employee.class);
        searchBean.setAndSearchCriteria(searchAndCriteria);
        searchBean.setOrSearchCriteria(searchOrCriteria);
        searchBean.setRangeCriteria(rangeCriteria);
        return genericRepository.search(searchBean);
    }
}
