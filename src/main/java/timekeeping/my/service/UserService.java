package timekeeping.my.service;

import timekeeping.my.model.entity.User;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.WrongPasswordException;

import java.util.List;

public interface UserService extends Service<User> {

    /**
     * @return list of customers (where role equals USER)
     * @throws EntityNotFoundException
     * */
    List<User> getUsers() throws EntityNotFoundException;

    /**
     * gets user by login
     * @return user the user
     * @throws EntityNotFoundException
     * */
    User getUserByLogin(String login) throws EntityNotFoundException;

    /**
     * gets user by login and password.
     * checks passwords for equality.
     * @return user the user by login if passwords are equal
     * @throws WrongPasswordException if passwords are not equal
     * @throws EntityNotFoundException if such user is not exist
     * */
    User getUser(String login, String password) throws EntityNotFoundException, WrongPasswordException;

}
