package org.example.smartqueueapi.repository;

import org.example.smartqueueapi.entity.Customer;
import org.example.smartqueueapi.enums.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayDeque;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByFinCode(String finCode);

    Customer findByNameAndFinCode(String name, String finCode);

    List<Customer> findAllByCustomerStatusIs(CustomerStatus customerStatus);

    Customer findByFinCode(String finCode);
}
