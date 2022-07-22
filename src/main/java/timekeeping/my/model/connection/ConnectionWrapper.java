package timekeeping.my.model.connection;

import java.sql.Connection;

/**
 * The interface ConnectionWrapper to manage connection.
 */
public interface ConnectionWrapper extends AutoCloseable{

    /**
     * begins transaction
     */
    void beginTransaction();

    /**
     * commits transaction
     */
    void commitTransaction();

    /**
     * rollbacks transaction
     */
    void rollbackTransaction();

    /**
     * closes connection
     * */
    void close();

    /**
     * gets connection
     * @return the connection
     */
    Connection getConnection();

}
