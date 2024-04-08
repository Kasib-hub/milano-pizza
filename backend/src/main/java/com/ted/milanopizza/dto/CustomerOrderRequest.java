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
    private Integer id;
    private String telephone;
    private Integer employeeId;
    private LocalDateTime customerOrderDateTime;
}
