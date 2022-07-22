package timekeeping.my.model.dao;

import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;

/**
 * The interface User DAO
 * */
public interface UserDao extends Dao<User> {
    /**
     * takes user from database by email.
     * @param email the email
     * @return User the user
    **/
//    User getUserByEmail(String email);

    /**
     * takes user from database by username.
     * @param login the login
     * @return User the user
     * @throws DaoException
     **/
    User getUserByLogin(String login) throws DaoException;

}
