package com.ted.milanopizza.repository;

import com.ted.milanopizza.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// No Update/Delete
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    // I want the customer to return zipcode Id
    // Define a projection interface
//    interface CustomerWithZipcodeId {
//        Long getTelephoneID();
//        String getStreetAddress();
//        Long getZipcodeId(); // Include the foreign key
//    }
//
//    // Use the projection in the query method
//    @Query("SELECT c.telephone as telephoneID, c.streetAddress as streetAddress, c.zipcode.zipcodeID as zipcodeId FROM customer c")
//    List<CustomerWithZipcodeId> findAllWithZipcodeId();
//
//    @Query("SELECT c.telephone as telephoneID, c.streetAddress as streetAddress, c.zipcode.zipcodeID as zipcodeId FROM customer c WHERE c.telephone = :telephoneId")
//    Optional<CustomerWithZipcodeId> findByIdWithZipcode(@Param("telephoneId") Long telephoneId);

}

