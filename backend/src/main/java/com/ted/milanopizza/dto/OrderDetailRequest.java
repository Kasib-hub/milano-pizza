package com.ted.milanopizza.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailRequest {
    private int id;
    private int customerOrderId;
    private int productId;
    private LocalDateTime orderDateTime;
    private int quantity;
    private int discount;
    private int subTotal;
}
