package timekeeping.my.service.exception;

import timekeeping.my.model.exception.DaoException;

public class EntityNotFoundException extends DaoException {

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
