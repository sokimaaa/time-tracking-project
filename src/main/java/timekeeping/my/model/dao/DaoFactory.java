package timekeeping.my.model.dao;

import timekeeping.my.model.entity.Entity;
import timekeeping.my.model.connection.ConnectionWrapper;

public abstract class DaoFactory {

    /**
     * gets dao
     * */
    public abstract <T extends Entity> Dao<T> getDAO(Class<? extends Dao> clazz, ConnectionWrapper connection);

}
