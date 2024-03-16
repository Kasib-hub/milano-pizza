package com.ted.milanopizza.api;

import com.ted.milanopizza.model.Customer;
import com.ted.milanopizza.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class MilanoPizzaApplicationTests {


	@Autowired
	CustomerRepository customerRepository;

	@Test
	@DisplayName("Verify that you can stream customers")
	void testCustomerLambda() {
		// map to
		List<String> customerList = customerRepository.findAll().stream()
				.filter(customer -> customer.getTelephoneID() > 5000)
				.map(Customer::getStreetAddress)
				.toList();
	}

}
