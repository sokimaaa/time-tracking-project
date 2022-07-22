package timekeeping.my.service.exception;

import timekeeping.my.model.exception.DaoException;

public class WrongPasswordException extends DaoException {

    public WrongPasswordException() {
        super();
    }
}
