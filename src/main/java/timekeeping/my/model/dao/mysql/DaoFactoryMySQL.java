package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.model.exception.DaoFactoryException;

import java.lang.reflect.InvocationTargetException;

public class DaoFactoryMySQL extends DaoFactory {

    private static final Logger log = LoggerFactory.getLogger(DaoFactory.class);
    private static DaoFactory instance;

    private DaoFactoryMySQL() {

    }

    public static synchronized DaoFactory getInstance() {
        if(instance == null)
            instance = new DaoFactoryMySQL();
        return instance;
    }

    public <T extends Entity> AbstractDao<T> getDAO(Class<? extends Dao> clazz, ConnectionWrapper connection) {
        AbstractDao<T> dao;
        try {
            dao = (AbstractDao<T>) clazz.getDeclaredConstructor().newInstance();
            dao.setConnection(connection);
            log.trace("Dao was obtained.");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Fail to get dao. Provided wrong meta class ==> " + clazz);
            throw new DaoFactoryException(e);
        }

        return dao;
    }

}

