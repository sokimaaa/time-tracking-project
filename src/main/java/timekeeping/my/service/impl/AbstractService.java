package timekeeping.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.dao.mysql.DaoFactoryMySQL;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.model.exception.QueryException;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.connection.mysql.ConnectionWrapperMySQL;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.service.Service;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.exception.RemoveEntityException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.util.ConnectionPool;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractService<T extends Entity> implements Service<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);
    protected DaoFactory daoFactory;

    protected AbstractService() {
        this.daoFactory = DaoFactoryMySQL.getInstance();
    }

    @Override
    public T insert(T t) throws InsertEntityException {
        processEntity(t);
        ConnectionWrapper connection = getConnection();
        try {
            connection.beginTransaction();
            getDAO(connection).insert(t);
            connection.commitTransaction();
            log.info("Insert item " + t + " was successful!!");
            return t;
        } catch (DaoException e) {
            log.info("Insert item " + t + " was unsuccessful.");
            connection.rollbackTransaction();
            throw new InsertEntityException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public T update(T t) throws UpdateEntityException {
        ConnectionWrapper connection = getConnection();
        try {
            connection.beginTransaction();
            getDAO(connection).update(t);
            connection.commitTransaction();
            log.info("Update item " + t + " was successful!!!");
            return t;
        } catch (DaoException e) {
            log.info("Update item " + t + " was unsuccessful.");
            connection.rollbackTransaction();
            throw new UpdateEntityException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public int remove(int id) throws RemoveEntityException {
        ConnectionWrapper connection = getConnection();
        try {
            connection.beginTransaction();
            getDAO(connection).remove(id);
            connection.commitTransaction();
            log.info("Remove item with id=" + id + " was successful!!");
            return id;
        } catch (DaoException e) {
            log.info("Remove item with id=" + id + " was unsuccessful.");
            connection.rollbackTransaction();
            throw new RemoveEntityException(e);
        } finally {
            connection.close();
        }
    }

    @Override
    public T get(int id) throws EntityNotFoundException {
        T t;
        try(ConnectionWrapper connection = getConnection()) {
            t = getDAO(connection).get(id);
            fillItem(t);
            log.info("Entity was found!!!");
        } catch (DaoException e) {
            log.info("Entity was not found.");
            throw new EntityNotFoundException(e);
        }

        return t;
    }

    @Override
    public List<T> getAll() throws EntityNotFoundException {
        List<T> all;
        try(ConnectionWrapper connection = getConnection()) {
            all = getDAO(connection).getAll();
            for (T item: all)
                fillItem(item);
            log.info("Entities were found!!");
        } catch (DaoException e) {
            log.info("Entities were not found.");
            throw new EntityNotFoundException(e);
        }
        return all;
    }

    protected final ConnectionWrapper getConnection() {
        Connection connection = ConnectionPool.getConnection();
        log.trace("Getting connection...");
        return new ConnectionWrapperMySQL(connection);
    }

    protected abstract Dao<T> getDAO(ConnectionWrapper connection);

    /**
     * encryption entity in SHA-3-512 encoding
     * works only for password!
     * @param t the entity which has field to encode
     * */
    protected abstract void processEntity(T t);

    /**
     * fills item's fields concrete object extracted from database
     * @param t the item need to fill
     * @throws EntityNotFoundException in case when entity is not found
     * */
    protected abstract void fillItem(T t) throws EntityNotFoundException;
}
