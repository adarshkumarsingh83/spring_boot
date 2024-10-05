package com.espark.adarsh;

import com.espark.adarsh.bean.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest
class SpringbootValidationBasicApplicationTests {


	private Validator validator;

	@BeforeEach
	void setUp() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void test1(){
		User user = new User();
		user.setName("test-name");
		user.setWorking(true);
		user.setAboutMe("test-about-me");
		user.setAge(24);
		user.setEmail("adarsh@kumar.ut");

		Set<ConstraintViolation<User>> violations = validator.validate(user);
         display(violations);
		assertTrue(violations.isEmpty());
	}


	void display(Set<ConstraintViolation<User>> violations){
		for (ConstraintViolation<User> violation : violations) {
			log.error(violation.getMessage());
		}
	}
}
