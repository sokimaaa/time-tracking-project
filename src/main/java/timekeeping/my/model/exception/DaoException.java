package timekeeping.my.model.exception;

import java.sql.SQLException;

public class DaoException extends SQLException {

    public DaoException() {super();}

    public DaoException(Throwable cause) {super(cause);}

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
