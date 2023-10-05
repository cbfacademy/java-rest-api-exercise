package com.cbfacademy.restapiexercise.IOU;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ious")
public class IOUController {

	private final List<IOU> ious = new ArrayList<>();

	@GetMapping("/ping")
	public String ping() {
		return String.format("Service running successfully " + Instant.now().toString());
	}

	@GetMapping("/")
	public List<IOU> getIOUs() {
		return ious;
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<IOU> getIOUById(@PathVariable UUID id) {
		for (IOU iou : ious) {
			if (iou.getId().equals(id)) {
				return ResponseEntity.ok(iou);
			}
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public IOU createIOU(@RequestBody IOU iou) {
		iou.setId(UUID.randomUUID());
		iou.setDateTime(Instant.now());
		ious.add(iou);

		return iou;
	}

	// Update an existing IOU by ID
	@PutMapping("/{id}")
	public IOU updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
		for (IOU iou : ious) {
			if (iou.getId().equals(id)) {
				iou.setLender(updatedIOU.getLender());
				iou.setBorrower(updatedIOU.getBorrower());
				iou.setAmount(updatedIOU.getAmount());
				iou.setDateTime(updatedIOU.getDateTime());

				return iou;
			}
		}

		return null;
	}

	// Delete an IOU by ID
	@DeleteMapping("/{id}")
	public void deleteIOU(@PathVariable UUID id) {
		ious.removeIf(iou -> iou.getId().equals(id));
	}

}
