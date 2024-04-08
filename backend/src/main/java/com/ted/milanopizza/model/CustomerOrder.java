package com.ted.milanopizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "customer_order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="employee_id", nullable = false)
    private Employee employee;
    @Column(nullable = false)
    private LocalDateTime customerOrderDateTime;
    @OneToMany(mappedBy = "customerOrder")
    private List<OrderDetail> orderDetailList;
}

