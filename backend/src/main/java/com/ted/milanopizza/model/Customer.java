package com.ted.milanopizza.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Customer {
    @Id
    @JsonProperty("telephone")
    private String telephone;
    private String streetAddress;
    // customer can also have many customerOrders, but not implemented, just made a route to show them

    // we're saying that customer is the many and that zipcode is a fk
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="zipcode", nullable = false)
    @JsonBackReference
    private Zipcode zipcode; // <-- needs to be a foreign key look that up!

}



