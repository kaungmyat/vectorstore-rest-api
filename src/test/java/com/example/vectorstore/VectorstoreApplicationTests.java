package com.example.vectorstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.vectorstore.data.PCFilter;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class VectorstoreApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetAnswerService() {
		PCFilter pcFilter = new PCFilter();
		pcFilter.setFacilityCode("LTC-FCY");

		ResponseEntity<String> response = restTemplate.postForEntity("/pinecone/get-inquiry", pcFilter, String.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotEmpty();
	}
}
