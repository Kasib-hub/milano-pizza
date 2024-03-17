package com.ted.milanopizza.api;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MilanoPizzaApplicationTests {

	@ParameterizedTest
	@ValueSource(strings = {"hello", "world"})
	void contextLoads(String input) {
		assertThat(input).isEqualTo("hello");
	}

}
