package com.cbfacademy.restapiexercise;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable> {

    /**
     * Retrieves all entities from the repository.
     *
     * @return a list of all entities
     */
    List<T> retrieveAll();

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the identifier of the entity
     * @return the found entity, or null if no such entity exists
     */
    T retrieve(ID id);

    /**
     * Creates a new entity in the repository.
     *
     * @param entity the {@code <T>} to create
     * @return the created entity
     */
    T create(T entity);

    /**
     * Deletes an entity from the repository based on its unique identifier.
     *
     * @param id the identifier of the entity to be deleted
     * @return true if the entity was successfully deleted; otherwise false
     */
    Boolean delete(ID id);

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity);

}