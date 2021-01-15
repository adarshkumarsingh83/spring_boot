/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.espark.adarsh;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.junit.Assert.assertTrue;

public class SampleProfileApplicationTests {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	private String profiles;

	@Before
	public void before() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@After
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}

	@Test
	public void testDefaultProfile() throws Exception {
        // keep in the default profile in properties file then execute this test
		SampleProfileApplication.main(new String[0]);
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("welcome to the default profile"));
	}

	@Test
	public void testDevelopmentProfile() throws Exception {
		System.setProperty("spring.profiles.active", "development");
		SampleProfileApplication.main(new String[0]);
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("welcome to the development profile"));
	}

	@Test
	public void testProductionProfile() throws Exception {
		System.setProperty("spring.profiles.active", "production");
		SampleProfileApplication.main(new String[0]);
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("welcome to the production profile"));
	}

	@Test
	public void testGoodbyeProfileFromCommandline() throws Exception {
		SampleProfileApplication
				.main(new String[] { "--spring.profiles.active=testing" });
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output, output.contains("welcome to the testing profile"));
	}

}
