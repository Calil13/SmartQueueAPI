package org.example.smartqueueapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.smartqueueapi.dto.CustomerRequestDto;
import org.example.smartqueueapi.dto.CustomerResponseDto;
import org.example.smartqueueapi.service.QueueService;
import org.springframework.web.bind.annotation.*;

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
    public CustomerResponseDto getQueueCustomers(){

    }
}
