package timekeeping.my.model.connection.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The implement ConnectionWrapper Interface.
 * */
public final class ConnectionWrapperMySQL implements ConnectionWrapper {

    private static final Logger log = LoggerFactory.getLogger(ConnectionWrapper.class);
    private final Connection connection;
    private boolean inTransaction;

    public ConnectionWrapperMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void beginTransaction() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
            log.info("Transaction get started....");
        } catch (SQLException e) {
            log.error("Fail to start transaction.");
            throw new TransactionException(e);
        }
    }

    @Override
    public void commitTransaction() {
        try {
            connection.commit();
            connection.setAutoCommit(true);
            inTransaction = false;
            log.info("Transaction was committed...");
        } catch (SQLException e) {
            log.error("Fail to commit transaction.");
            throw new TransactionException(e);
        }
    }

    @Override
    public void rollbackTransaction() {
        try {
            connection.rollback();
            connection.setAutoCommit(true);
            inTransaction = false;
            log.info("Transaction was rolled back...");
        } catch (SQLException e) {
            log.error("Fail to roll back transaction.");
            throw new TransactionException(e);
        }
    }

    @Override
    public void close() {
        try {
            if(inTransaction) {
                rollbackTransaction();
            }
            connection.close();
            log.info("Connection was closed...");
        } catch (SQLException e) {
            log.error("Fail to close connection.");
            throw new TransactionException(e);
        }
    }

    @Override
    public Connection getConnection() {
        log.info("Getting connection...");
        return connection;
    }
}
