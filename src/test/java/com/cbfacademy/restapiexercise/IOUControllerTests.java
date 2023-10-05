package com.cbfacademy.restapiexercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cbfacademy.restapiexercise.IOU.IOU;

import java.math.BigDecimal;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IOUControllerTests {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/ious");
	}

	@Test
	@Description("/ping endpoint returns expected response")
	public void ping_ExpectedResponse() {
		ResponseEntity<String> response = restTemplate.getForEntity(base.toString() + "/ping", String.class);

		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().startsWith("Service running successfully"));
	}

	@Test
	public void testCreateIOU() {
		IOU iou = new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(0));
		ResponseEntity<IOU> response = restTemplate.postForEntity("/ious/", iou, IOU.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
	}

	@Test
	public void testGetAllIOUs() {
		List<IOU> ious = new ArrayList<>() {
			{
				add(new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(24)));
				add(new IOU("Bob", "Eve", new BigDecimal("50.00"), getInstant(48)));
				add(new IOU("Charlie", "Grace", new BigDecimal("200.00"), getInstant(72)));
			}
		};

		for (IOU iou : ious) {
			restTemplate.postForEntity("/ious/", iou, IOU.class);
		}

		ResponseEntity<IOU[]> response = restTemplate.getForEntity("/ious/", IOU[].class);
		IOU[] responseIOUs = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(responseIOUs);
		assertTrue(ious.size() <= responseIOUs.length);
	}

	@Test
	public void testGetIOUById() {
		IOU iou = new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(0));
		ResponseEntity<IOU> createResponse = restTemplate.postForEntity("/ious/", iou, IOU.class);

		IOU createdIOU = createResponse.getBody();
		ResponseEntity<IOU> response = restTemplate.getForEntity("/ious/" + createdIOU.getId(), IOU.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(createdIOU.getId(), response.getBody().getId());
	}

	@Test
	public void testUpdateIOU() {
		IOU iou = new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(0));
		ResponseEntity<IOU> createResponse = restTemplate.postForEntity("/ious/", iou, IOU.class);

		IOU createdIOU = createResponse.getBody();
		createdIOU.setLender("UpdatedLender");

		restTemplate.put("/ious/" + createdIOU.getId(), createdIOU);
		ResponseEntity<IOU> response = restTemplate.getForEntity("/ious/" + createdIOU.getId(), IOU.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("UpdatedLender", response.getBody().getLender());
	}

	@Test
	public void testDeleteIOU() {
		IOU iou = new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(0));
		ResponseEntity<IOU> createResponse = restTemplate.postForEntity("/ious/", iou, IOU.class);

		IOU createdIOU = createResponse.getBody();
		restTemplate.delete("/ious/" + createdIOU.getId());

		ResponseEntity<IOU> response = restTemplate.getForEntity("/ious/" + createdIOU.getId(), IOU.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	private Instant getInstant(int hoursToSubtract) {
		// Get the current date and time in the system's default time zone
		ZoneId systemTimeZone = ZoneId.systemDefault();
		ZonedDateTime currentDateTime = ZonedDateTime.now(systemTimeZone);

		// Subtract the specified number of hours using Duration
		Duration duration = Duration.ofHours(hoursToSubtract);
		ZonedDateTime resultDateTime = currentDateTime.minus(duration);

		// Convert to Instant
		Instant instant = resultDateTime.toInstant();

		return instant;
	}
}
