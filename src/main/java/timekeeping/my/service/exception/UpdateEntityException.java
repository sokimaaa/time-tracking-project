package timekeeping.my.service.exception;

import timekeeping.my.model.exception.DaoException;

public class UpdateEntityException extends DaoException {

    public UpdateEntityException(Throwable cause) {
        super(cause);
    }
}
