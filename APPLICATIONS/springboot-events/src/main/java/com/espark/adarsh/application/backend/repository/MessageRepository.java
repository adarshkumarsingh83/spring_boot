package com.espark.adarsh.application.backend.repository;

import com.espark.adarsh.application.backend.entities.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
