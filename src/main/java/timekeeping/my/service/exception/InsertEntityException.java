package timekeeping.my.service.exception;

import timekeeping.my.model.exception.DaoException;

public class InsertEntityException extends DaoException {

    public InsertEntityException(Throwable cause) {
        super(cause);
    }
}
