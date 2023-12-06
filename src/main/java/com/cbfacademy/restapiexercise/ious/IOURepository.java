package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import com.cbfacademy.restapiexercise.Repository;

/**
 * The IOURepository interface defines the operations for managing IOUs in the system.
 * It provides methods for retrieving, saving, updating, and deleting IOU records.
 */
public interface IOURepository extends Repository<IOU, UUID> {

    /**
     * Searches for IOUs where the borrower's name matches the provided string.
     *
     * @param name the name of the borrower
     * @return a list of IOUs that match the borrower's name
     */
    List<IOU> searchByBorrower(String name);

    /**
     * Searches for IOUs where the lender's name matches the provided string.
     *
     * @param name the name of the lender
     * @return a list of IOUs that match the lender's name
     */
    List<IOU> searchByLender(String name);

}
