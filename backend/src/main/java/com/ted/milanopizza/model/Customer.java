package com.ted.milanopizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    private String telephone;
    @Column(nullable = false)
    private String streetAddress;
    @ManyToOne
    @JoinColumn(name="zipcode_id", nullable = false)
    private Zipcode zipcode; // <-- needs to be a foreign key look that up!
    @OneToMany(mappedBy = "customer")
    private List<CustomerOrder> customerOrderList;
}



