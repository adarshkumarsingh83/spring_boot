package com.espark.adarsh.consumer;

import com.espark.adarsh.producer.consumer.ApplicationMain;
import com.espark.adarsh.producer.consumer.service.IntegrationService;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
public class ApplicationMainTests {

	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
			.downloadStub("com.espark.adarsh", "springboot-contract-client-validation", "0.0.1-SNAPSHOT", "stubs")
			.withPort(9090)
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);

	@Autowired
	IntegrationService consumerClient;

	@Test
	public void clientShouldRetrunPersonForGivenID_checkFirsttName() throws Exception {

		BDDAssertions.then(this.consumerClient.getEmployee(1).getFname()).isEqualTo("Adarsh");
	}

	@Test
	public void clientShouldRetrunPersonForGivenID_checkLastName() throws Exception {

		BDDAssertions.then(this.consumerClient.getEmployee(1).getLname()).isEqualTo("Kumar");
	}

}
