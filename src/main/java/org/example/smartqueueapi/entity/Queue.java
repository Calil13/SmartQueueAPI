package org.example.smartqueueapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "queue")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "customer")
    public Customer customer;
}
