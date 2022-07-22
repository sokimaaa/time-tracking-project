package timekeeping.my.service.exception;

import timekeeping.my.model.exception.DaoException;

public class RemoveEntityException extends DaoException {

    public RemoveEntityException(Throwable cause) {
        super(cause);
    }
}
