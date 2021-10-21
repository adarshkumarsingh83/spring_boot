package com.espark.adarsh;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class SpringbbootLogMessageAssertApplicationTests {

	@Test
	void contextLoads(CapturedOutput output) {
		Assertions.assertThat(output).contains("No active profile set, falling back to default profiles: default");
	}

}
