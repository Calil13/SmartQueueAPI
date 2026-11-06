package org.example.smartqueueapi.service;

import lombok.RequiredArgsConstructor;
import org.example.smartqueueapi.dto.CustomerRequestDto;
import org.example.smartqueueapi.entity.Customer;
import org.example.smartqueueapi.entity.Queue;
import org.example.smartqueueapi.enums.CustomerStatus;
import org.example.smartqueueapi.repository.CustomerRepository;
import org.example.smartqueueapi.repository.QueueRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueueService {

    private final CustomerRepository customerRepository;
    private final QueueRepository queueRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto) {

        var customerExist = customerRepository.findByNameAndFinCode(customerRequestDto.getName(), customerRequestDto.getFinCode());

        if (customerRepository.existsByFinCode(customerRequestDto.getFinCode())) {
            if (!customerExist.getCustomerStatus().equals(CustomerStatus.COMPLETED)) {
                throw new IllegalStateException("Customer already has an active queue request");
            }

            customerExist.setCustomerStatus(CustomerStatus.WAITING);
        }

        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .finCode(customerRequestDto.getFinCode())
                .customerStatus(CustomerStatus.WAITING)
                .build();
        customerRepository.save(customer);

        Queue queue = Queue.builder()
                .customer(customer)
                .build();
        queueRepository.save(queue);

        return "New Customer added to queue";
    }
}
