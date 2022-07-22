package timekeeping.my.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Connection Pool class provided connection
 * */
public final class ConnectionPool {

    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private static final DataSource ds;

    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TimeKeeping");
        } catch (NamingException e) {
            log.error("Connection was not obtained. Provided wrong context.");
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool() {

    }

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            log.error("Fail to get connection.");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
