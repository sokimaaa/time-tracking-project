package timekeeping.my.model.dao;

import timekeeping.my.model.entity.Entity;
import timekeeping.my.model.exception.DaoException;

import java.util.List;

/**
 * The interface DAO.
 * @param <T> the type parameter
 */
public interface Dao<T extends Entity> {

    /**
     * inserts entity to database
     * @param t the entity need to save
     * @return t the inserted object
     * @throws DaoException
     **/
    T insert(T t) throws DaoException;

    /**
     * updates entity in the database
     * @param t the entity to update
     * @return t the updated object
     * @throws DaoException
     **/
    T update(T t) throws DaoException;

    /**
     * removes entity by id.
     * @param id the id
     * @return id the removed object's id
     * @throws DaoException
     **/
    int remove(int id) throws DaoException;

    /**
     * gets entity by id.
     * @param id the id
     * @return t the entity
     * @throws DaoException
     **/
    T get(int id) throws DaoException;

    /**
     * gets all entities
     * @return List of all entities
     **/
    List<T> getAll() throws DaoException;

}
