package com.springbatch.demo.batch;

import com.springbatch.demo.model.Customer;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

    public Customer process(Customer customer) throws Exception {
        // Validate email
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            return null; // skip invalid
        }
        // Validate age
        if (customer.getAge() < 18) {
            return null; // skip underage
        }
        // Transformation example: uppercase name
        customer.setName(customer.getName().toUpperCase());
        return customer;

    }
}
