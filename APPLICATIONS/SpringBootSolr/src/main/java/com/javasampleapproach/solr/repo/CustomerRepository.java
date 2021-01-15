package com.javasampleapproach.solr.repo;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.javasampleapproach.solr.model.Customer;

public interface CustomerRepository extends SolrCrudRepository<Customer, String> {
	List<Customer> findByNameEndsWith(String name);
}
