package com.cbfacademy.restapiexercise.ious;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.cbfacademy.restapiexercise.ProjectApplication;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = ProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IOUControllerTests {

	@LocalServerPort
	private int port;

	private URI baseURI;

	@Autowired
	private TestRestTemplate restTemplate;

	private List<IOU> defaultIOUs = new ArrayList<>() {
		{
			add(new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(24)));
			add(new IOU("Bob", "Eve", new BigDecimal("50.00"), getInstant(48)));
			add(new IOU("Charlie", "Grace", new BigDecimal("200.00"), getInstant(72)));
		}
	};

	@Autowired
	private FakeIOUService iouService; // Inject the FakeIOUService

	@BeforeEach
	public void setUp() throws Exception {
		this.baseURI = UriComponentsBuilder.newInstance()
				.scheme("http")
				.host("localhost")
				.port(port)
				.path("api/ious")
				.build()
				.toUri();

		// Remove any existing IOUs
		for (Iterator<IOU> it = iouService.getAllIOUs().iterator(); it.hasNext();) {
			it.next();
			it.remove();
		}

		// Add default IOUs
		for (IOU iou : defaultIOUs) {
			iouService.createIOU(iou);
		}
	}

	@Test
	@Description("POST /api/ious creates new IOU")
	public void testCreateIOU() {
		IOU iou = new IOU("John", "Alice", new BigDecimal("100.00"), getInstant(0));
		ResponseEntity<IOU> response = restTemplate.postForEntity(baseURI.toString(), iou, IOU.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertNotNull(response.getBody().getId());
	}

	@Test
	@Description("GET /api/ious returns all IOUs")
	public void testGetAllIOUs() throws URISyntaxException {
		ResponseEntity<List<IOU>> response = restTemplate.exchange(baseURI, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<IOU>>() {
				});
		List<IOU> responseIOUs = response.getBody();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(responseIOUs);
		assertTrue(defaultIOUs.size() == responseIOUs.size());
	}

	@Test
	@Description("GET /api/ious/{id} returns matching IOU")
	public void testGetIOUById() {
		IOU iou = selectRandomIOU();
		URI endpoint = getEndpoint(iou);
		ResponseEntity<IOU> response = restTemplate.getForEntity(endpoint, IOU.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(iou.getId(), response.getBody().getId());
	}

	@Test
	@Description("PUT /api/ious/{id} updates matching IOU")
	public void testUpdateIOU() {
		IOU iou = selectRandomIOU();
		URI endpoint = getEndpoint(iou);

		iou.setLender("UpdatedLender");
		restTemplate.put(endpoint, iou);

		ResponseEntity<IOU> response = restTemplate.getForEntity(endpoint, IOU.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("UpdatedLender", response.getBody().getLender());
	}

	@Test
	@Description("DELETE /api/ious/{id} deletes matching IOU")
	public void testDeleteIOU() {
		URI endpoint = getEndpoint(selectRandomIOU());
		ResponseEntity<IOU> foundResponse = restTemplate.getForEntity(endpoint, IOU.class);

		restTemplate.delete(endpoint);

		ResponseEntity<IOU> deletedResponse = restTemplate.getForEntity(endpoint, IOU.class);

		assertEquals(HttpStatus.OK, foundResponse.getStatusCode());
		assertEquals(HttpStatus.NOT_FOUND, deletedResponse.getStatusCode());
	}

	private IOU selectRandomIOU() {
		List<IOU> ious = iouService.getAllIOUs();

		if (ious.isEmpty()) {
			return null;
		}

		int randomIndex = new Random().nextInt(ious.size());

		return ious.get(randomIndex);
	}

	private URI getEndpoint(IOU iou) {
		return appendPath(baseURI, iou.getId().toString());
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

	private URI appendPath(URI uri, String path) {
		return UriComponentsBuilder.fromUri(uri).pathSegment(path).build().encode().toUri();
	}
}
