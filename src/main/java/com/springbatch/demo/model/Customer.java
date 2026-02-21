package com.springbatch.demo.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "customers")
public class Customer {

    @Id
    private Long id;
    private String name;
    private String email;
    private Integer age;
}
