package com.ted.milanopizza.api;

import com.ted.milanopizza.model.Customer;
import com.ted.milanopizza.repository.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

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
				.filter(customer -> customer.getTelephone() > 5000)
				.map(Customer::getStreetAddress)
				.toList();
	}

	@ParameterizedTest
	@ValueSource(strings = {"hello", "world"})
	void contextLoads(String input) {
		assertThat(input).isEqualTo("hello");
	}
}
