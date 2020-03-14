package com.espark.adarsh.service;

import com.espark.adarsh.entity.AbstractEntity;
import com.espark.adarsh.exception.ResourceNotFound;

import java.util.List;

public interface AbstractService {

    public AbstractEntity getRoot();

    public List<AbstractEntity> getNextLevelChildren(Long id);

    public List<AbstractEntity> getAllLevelChildren(Long id) throws ResourceNotFound;

    public AbstractEntity getById(Long id) throws ResourceNotFound;

    public List searchByKeyWord(String keyWord) throws ResourceNotFound;

}
