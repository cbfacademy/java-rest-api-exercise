package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

public interface IOUService {

    /**
     * Retrieve a list of all IOUs.
     *
     * @return A list of all IOUs.
     */
    List<IOU> getAllIOUs();

    /**
     * Retrieve an IOU by its ID.
     *
     * @param id The ID of the IOU to retrieve.
     * @return The IOU with the specified ID, or null if not found.
     */
    IOU getIOU(UUID id);

    /**
     * Create a new IOU.
     *
     * @param iou The IOU object to create.
     * @return The created IOU.
     */
    IOU createIOU(IOU iou);

    /**
     * Update an existing IOU by its ID.
     *
     * @param id         The ID of the IOU to update.
     * @param updatedIOU The updated IOU object.
     * @return The updated IOU, or null if the ID is not found.
     */
    IOU updateIOU(UUID id, IOU updatedIOU);

    /**
     * Delete an IOU by its ID.
     *
     * @param id The ID of the IOU to delete.
     * @return true if the IOU was successfully deleted, false if the ID was not
     *         found.
     */
    boolean deleteIOU(UUID id);

}