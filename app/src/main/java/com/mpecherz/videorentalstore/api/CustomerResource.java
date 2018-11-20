package com.mpecherz.videorentalstore.api;

import com.mpecherz.videorentalstore.model.CustomerService;
import com.mpecherz.videorentalstore.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerService customerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> get(@PathVariable String id) {
        return ResponseEntity.of(customerService.get(id));
    }
}
