package timekeeping.my.service;

import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.exception.RemoveEntityException;
import timekeeping.my.service.exception.UpdateEntityException;

import java.util.List;

/**
 * The interface service provided CRUD methods.
 * */
public interface Service<T> {

    /**
     * inserts entity
     * @param t the entity to insert
     * @return t the inserted entity
     * @throws InsertEntityException if entity was not inserted
     * */
    T insert(T t) throws InsertEntityException;

    /**
     * updates entity
     * @param t the object t with need to update fields
     * @return t the updated t
     * @throws UpdateEntityException if entity was not updated
     * */
    T update(T t) throws UpdateEntityException;

    /**
     * removes object by id
     * @param id the object id
     * @return id the id of entity was removed
     * @throws RemoveEntityException if any was removed
     * */
    int remove(int id) throws RemoveEntityException;

    /**
     * gets object by id
     * @param id the object's id
     * @return t the object t with id
     * @throws EntityNotFoundException in case when entity is not found
     * */
    T get(int id) throws EntityNotFoundException;

    /**
     * gets all objects T from database
     * @return list
     * @throws EntityNotFoundException in case when entity is not found
     * */
    List<T> getAll() throws EntityNotFoundException;

}
