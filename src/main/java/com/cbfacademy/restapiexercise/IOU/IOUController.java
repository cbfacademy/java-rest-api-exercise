package com.cbfacademy.restapiexercise.IOU;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ious")
public class IOUController {

	private final List<IOU> ious = new ArrayList<>();

	@GetMapping("/ping")
	public String ping() {
		return String.format("Service running successfully "+Instant.now().toString());
	}

	@GetMapping
	public List<IOU> getIOUs() {
		return ious;
	}

	@GetMapping(value="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public IOU getIOUById(@PathVariable UUID id) {
		return ious.stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElse(null);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public IOU createIOU(@RequestBody IOU item) {
		item.setId(UUID.randomUUID());
		item.setDateTime(Instant.now());
        ious.add(item);
        return item;
	}

}
