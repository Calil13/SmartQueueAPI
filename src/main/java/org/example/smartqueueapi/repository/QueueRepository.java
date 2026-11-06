package org.example.smartqueueapi.repository;

import org.example.smartqueueapi.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueueRepository extends JpaRepository<Queue, Long> {
}
