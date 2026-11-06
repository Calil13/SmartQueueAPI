package org.example.smartqueueapi.repository;

import org.example.smartqueueapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByFinCode(String finCode);

    Customer findByNameAndFinCode(String name, String finCode);
}
