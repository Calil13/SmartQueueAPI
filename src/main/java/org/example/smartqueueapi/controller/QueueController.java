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

    @PostMapping("/add-customer-queue")
    public String addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return queueService.addCustomer(customerRequestDto);
    }

    @GetMapping("/get-queue-customers")
    public List<Customer> getQueueCustomers(){
        return queueService.getQueueCustomers();
    }

    @GetMapping("/get-customer-queue")
    public String getCustomerQueue(String finCode){
        return queueService.getCustomerQueue(finCode);
    }

    @PostMapping("/next-customer")
    public String nextCustomer() {
        return queueService.nextCustomer();
    }

    @DeleteMapping("/delete-customer")
    public String deleteCustomer(String finCode) {
        return queueService.deleteCustomer(finCode);
    }
}
