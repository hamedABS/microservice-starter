package com.enjoybycode.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/customers")
public record CustomerController(CustomerService customerService) {
    public void register(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new Customer registration {}" + customerRegistrationRequest);
        customerService.registerCustomer(customerRegistrationRequest);
    }
}