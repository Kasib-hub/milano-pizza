package com.ted.milanopizza.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Zipcode")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Zipcode {

    // can't use zipcodeID as models have to be initialized with Id
    @Id
    @JsonProperty("zipcodeId")
    private Long zipcodeId;
    private String city;
    private String state;
    @OneToMany(mappedBy = "zipcode", cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<Customer> customers = new ArrayList<>();

}

