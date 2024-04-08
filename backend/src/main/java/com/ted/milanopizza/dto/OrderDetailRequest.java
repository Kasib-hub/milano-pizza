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
    private Integer id;
    private Integer customerOrderId;
    private Integer productId;
    private LocalDateTime orderDateTime;
    private Integer quantity;
    private Integer discount;
    private Integer subTotal;
}
