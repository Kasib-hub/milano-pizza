package com.ted.milanopizza.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "zipcode")
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

