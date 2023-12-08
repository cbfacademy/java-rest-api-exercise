package com.cbfacademy.restapiexercise.core;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable> {

    /**
     * Retrieves all entities from the repository.
     *
     * @return a list of all entities
     */
    List<T> retrieveAll() throws PersistenceException;

    /**
     * Finds an entity by its unique identifier.
     *
     * @param id the identifier of the entity
     * @return the found entity, or null if no such entity exists
     */
    T retrieve(ID id) throws IllegalArgumentException, PersistenceException;

    /**
     * Creates a new entity in the repository.
     *
     * @param entity the {@code <T>} to create
     * @return the created entity
     */
    T create(T entity) throws IllegalArgumentException, PersistenceException;

    /**
     * Deletes an entity from the repository based on its unique identifier.
     *
     * @param entity the entity to update
     * @return true if the entity was successfully deleted; otherwise false
     */
    void delete(T entity) throws IllegalArgumentException, PersistenceException;

    /**
     * Updates an existing entity in the repository.
     *
     * @param entity the entity to update
     * @return the updated entity
     */
    T update(T entity) throws IllegalArgumentException, PersistenceException;

}