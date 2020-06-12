package com.espark.adarsh.repository;

import com.espark.adarsh.bean.SearchBean;
import com.espark.adarsh.entity.DbEntity;

import java.text.ParseException;
import java.util.List;

public interface GenericRepository {
    List<DbEntity> search(SearchBean searchBean) throws Exception;
}
