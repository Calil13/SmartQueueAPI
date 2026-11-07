package org.example.smartqueueapi.service;

import lombok.RequiredArgsConstructor;
import org.example.smartqueueapi.dto.CustomerRequestDto;
import org.example.smartqueueapi.entity.Customer;
import org.example.smartqueueapi.entity.Queue;
import org.example.smartqueueapi.enums.CustomerStatus;
import org.example.smartqueueapi.repository.CustomerRepository;
import org.example.smartqueueapi.repository.QueueRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {

    ArrayDeque<Customer> queue = new ArrayDeque<>();

    private final CustomerRepository customerRepository;
    private final QueueRepository queueRepository;

    public String addCustomer(CustomerRequestDto customerRequestDto) {

        var customerExist = customerRepository.findByNameAndFinCode(customerRequestDto.getName(), customerRequestDto.getFinCode());

        if (customerRepository.existsByFinCode(customerRequestDto.getFinCode())) {
            if (!customerExist.getCustomerStatus().equals(CustomerStatus.COMPLETED)) {
                throw new IllegalStateException("Customer already has an active queue request");
            }

            if (queue.size() == 1) {
                customerExist.setCustomerStatus(CustomerStatus.SERVING);
            } else {
                customerExist.setCustomerStatus(CustomerStatus.WAITING);
            }

            return "Customer added to queue again.";
        }

        Customer customer = Customer.builder()
                .name(customerRequestDto.getName())
                .finCode(customerRequestDto.getFinCode())
                .customerStatus(CustomerStatus.WAITING)
                .createdAt(LocalDateTime.now())
                .build();
        customerRepository.save(customer);
        queue.addLast(customer);

        Queue queue = Queue.builder()
                .customer(customer)
                .build();
        queueRepository.save(queue);

        return "New Customer added to queue.";
    }

    public List<Customer> getQueueCustomers(){
        return customerRepository.findAllByCustomerStatusIs(CustomerStatus.WAITING);
    }

    public String getCustomerQueue(String finCode){
        var customer = customerRepository.findByFinCode(finCode);

        if (customer == null) {
            throw new RuntimeException("CUSTOMER_NOT_FOUND");
        }

        int row = 1;

        System.out.println("FIN Code: " + finCode);

        for (Customer customerArr : queue) {
            if (customerArr.getFinCode().equals(finCode)) {
                return customer.getName() + "'s position is " + row;
            }
            row++;
        }

        return "Customer is not in the queue";
    }

    public String nextCustomer() {
        var customer = queue.peek();

        if (customer != null) {
            customer.setCustomerStatus(CustomerStatus.COMPLETED);
        }

        queue.poll();

        var nextCustomer = queue.peek();

        if (nextCustomer == null) {
            return "Customer completed. No customers waiting.";
        }

        return "Next customer: " + nextCustomer.getName();
    }

    public String deleteCustomer(String finCode) {
        var customer = customerRepository.findByFinCode(finCode);

        if (customer == null) {
            return "CUSTOMER_NOT_FOUND";
        }

        customer.setCustomerStatus(CustomerStatus.CANCELLED);

        queue.removeIf(customerRemove -> customerRemove.getFinCode().equals(finCode));

        return "Customer out of queue: " + customer.getName();
    }
}
