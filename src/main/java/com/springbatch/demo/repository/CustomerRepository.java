package com.springbatch.demo.repository;

import com.springbatch.demo.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<Customer, Long> {
}
