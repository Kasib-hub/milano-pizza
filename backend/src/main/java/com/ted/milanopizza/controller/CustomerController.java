package com.ted.milanopizza.controller;

import com.ted.milanopizza.dto.CustomerRequest;
import com.ted.milanopizza.model.Customer;
import com.ted.milanopizza.model.Zipcode;
import com.ted.milanopizza.repository.CustomerRepository;
import com.ted.milanopizza.repository.ZipcodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:5173")
@Slf4j
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerRepository.CustomerWithZipcodeId>>getAllCustomers() {
        try {
            List<CustomerRepository.CustomerWithZipcodeId> customerList = customerRepository.findAllWithZipcodeId();

            if (customerList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("SUCCESS: CUSTOMERS RETURNED");
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("### SERVER ERROR: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerRepository.CustomerWithZipcodeId> getCustomerById(@PathVariable Long id) {

        Optional<CustomerRepository.CustomerWithZipcodeId> customerData = customerRepository.findByIdWithZipcode(id);

        return customerData.map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    

    // since customer is in the list of customers under a zipcode, we must retrieve the zipcode parent
    // we instantiate the customer and set its parent!
    @PostMapping("/customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerRequest customerRequest) {
        Optional<Zipcode> zipcodeOptional = zipcodeRepository.findById(customerRequest.getZipcode_id());
        if (zipcodeOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Zipcode zipcode = zipcodeOptional.get();
        Customer customer = new Customer();
        customer.setTelephoneID(customerRequest.getTelephoneID());
        customer.setStreetAddress(customerRequest.getStreetAddress());
        customer.setZipcode(zipcode);
        customerRepository.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    // update customer
    @PostMapping("/customer/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id, @RequestBody CustomerRequest newCustomer) {
        Optional<Customer> oldCustomerData = customerRepository.findById(id);
        Optional<Zipcode> oldZipcodeData = zipcodeRepository.findById(newCustomer.getZipcode_id());

        if (oldCustomerData.isPresent() && oldZipcodeData.isPresent()) {
            Customer existingCustomer = oldCustomerData.get();
            Zipcode existingZipcode = oldZipcodeData.get();

            // I should update the non primary fields here with existing customer then make updated later
            if (newCustomer.getStreetAddress() != null) {
                existingCustomer.setStreetAddress(newCustomer.getStreetAddress());
            }

            if (newCustomer.getZipcode_id() != null) {
                existingCustomer.setZipcode(existingZipcode);
            }

            // make new object with updated values and give option to change telephone ID
            Customer updatedCustomer = new Customer();
            // changed to new telephoneID
            updatedCustomer.setTelephoneID(newCustomer.getTelephoneID());
            updatedCustomer.setStreetAddress(existingCustomer.getStreetAddress());
            updatedCustomer.setZipcode(existingCustomer.getZipcode());

            Customer savedCustomer = customerRepository.save(existingCustomer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteZipcodeById(@PathVariable Long id) {
        customerRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
