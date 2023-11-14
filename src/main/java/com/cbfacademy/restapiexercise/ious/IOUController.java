package com.cbfacademy.restapiexercise.ious;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class to implement IOU API endpoints.
 */
@RestController
@RequestMapping("/api/ious")
public class IOUController {

    private final IOUService iouService;

    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    /**
     * Retrieve a list of all IOUs.
     *
     * @return A ResponseEntity containing a list of all IOUs and HttpStatus OK if
     *         successful.
     */
    @GetMapping
    public ResponseEntity<List<IOU>> getAllIOUs() {
        List<IOU> ious = iouService.getAllIOUs();

        return new ResponseEntity<>(ious, HttpStatus.OK);
    }

    /**
     * Retrieve a specific IOU by its ID.
     *
     * @param id The ID of the IOU to retrieve.
     * @return A ResponseEntity containing the requested IOU and HttpStatus OK if
     *         found,
     *         or HttpStatus NOT_FOUND if the ID is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IOU> getIOU(@PathVariable UUID id) {
        IOU iou = iouService.getIOU(id);

        if (iou != null) {
            return new ResponseEntity<>(iou, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new IOU.
     *
     * @param iou The IOU object to create.
     * @return A ResponseEntity containing the created IOU and HttpStatus CREATED if
     *         successful.
     */
    @PostMapping
    public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
        IOU createdIOU = iouService.createIOU(iou);

        return new ResponseEntity<>(createdIOU, HttpStatus.CREATED);
    }

    /**
     * Update an existing IOU by ID.
     *
     * @param id         The ID of the IOU to update.
     * @param updatedIOU The updated IOU object.
     * @return A ResponseEntity containing the updated IOU and HttpStatus OK if
     *         successful,
     *         or HttpStatus NOT_FOUND if the ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<IOU> updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        IOU iou = iouService.updateIOU(id, updatedIOU);

        if (iou != null) {
            return new ResponseEntity<>(iou, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an IOU by ID.
     *
     * @param id The ID of the IOU to delete.
     * @return A ResponseEntity with HttpStatus NO_CONTENT if the IOU was
     *         successfully deleted,
     *         or HttpStatus NOT_FOUND if the ID was not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIOU(@PathVariable UUID id) {
        boolean deleted = iouService.deleteIOU(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
