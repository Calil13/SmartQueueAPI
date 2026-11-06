package org.example.smartqueueapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartqueueapi.enums.CustomerStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(unique = true, name = "fin_code")
    private String finCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private CustomerStatus customerStatus;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
