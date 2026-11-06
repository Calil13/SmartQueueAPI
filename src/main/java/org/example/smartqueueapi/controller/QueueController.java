package org.example.smartqueueapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartqueueapi.dto.CustomerRequestDto;
import org.example.smartqueueapi.entity.Customer;
import org.example.smartqueueapi.service.QueueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/queue")
public class QueueController {

    private final QueueService queueService;

    @PostMapping("/add-customer")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return queueService.addCustomer(customerRequestDto);
    }

    @GetMapping
    public List<Customer> getQueueCustomers(){
        return queueService.getQueueCustomers();
    }
}
