package com.ted.milanopizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "zipcode")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Zipcode {
    @Id
    private String zipcode;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @OneToMany(mappedBy = "zipcode")
    private List<Customer> customers;
}

