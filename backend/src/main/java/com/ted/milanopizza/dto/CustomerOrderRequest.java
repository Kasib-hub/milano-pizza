package com.ted.milanopizza.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderRequest {
    private int id;
    private String telephone;
    private int employeeId;
    private LocalDateTime customerOrderDateTime;
}
