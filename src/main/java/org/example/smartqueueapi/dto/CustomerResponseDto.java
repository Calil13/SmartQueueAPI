package org.example.smartqueueapi.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartqueueapi.enums.CustomerStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String finCode;
    private String customerStatus;
    private LocalDateTime createdAt;
}
