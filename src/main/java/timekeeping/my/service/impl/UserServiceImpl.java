package timekeeping.my.service.impl;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.UserDao;
import timekeeping.my.model.dao.mysql.UserMySQLDao;
import timekeeping.my.model.entity.Role;
import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.model.exception.QueryException;
import timekeeping.my.service.CityService;
import timekeeping.my.service.ServiceFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.entity.City;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.WrongPasswordException;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl extends AbstractService<User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final CityService cityService;

    public UserServiceImpl() {
        cityService = (CityService) ServiceFactory.getInstance().<City>getService(CityServiceImpl.class);
    }

    @Override
    public void fillItem(User user) throws EntityNotFoundException {
        user.setCity(cityService.get(user.getCity().getId()));
        log.trace("User's fields were filled.");
        log.info(user.toString());
    }

    @Override
    protected Dao<User> getDAO(ConnectionWrapper connection) {
        log.trace("Getting user dao. Connection ==> " + connection);
        return daoFactory.getDAO(UserMySQLDao.class, connection);
    }

    @Override
    protected void processEntity(User user) {
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] passwordToEncode = user.getPassword().getBytes();
        String encodedPasswordInHex = Hex.toHexString(digestSHA3.digest(passwordToEncode));
        user.setPassword(encodedPasswordInHex);
    }

    @Override
    public List<User> getUsers() throws EntityNotFoundException {
        return getAll().stream()
                .filter(user -> user.getRole().equals(Role.USER))
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByLogin(String login) throws EntityNotFoundException {
        User user;
        try {
            user = ((UserDao) getDAO(getConnection())).getUserByLogin(login);
            fillItem(user);
            log.trace("User was received. User ==> " + user);
        } catch (DaoException e) {
            log.trace("User was not found by login ==> " + login);
            throw new EntityNotFoundException(e);
        }
        return user;
    }

    @Override
    public User getUser(String login, String password) throws EntityNotFoundException, WrongPasswordException {
        User user;

        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
        byte[] passwordToEncode = password.getBytes();
        String encodedPassword = Hex.toHexString(digestSHA3.digest(passwordToEncode));
        try {
            user = getUserByLogin(login);
            if(!user.getPassword().equals(encodedPassword)) {
                log.trace("Entered wrong password. Access denied.");
                throw new WrongPasswordException();
            }
        } catch (EntityNotFoundException e) {
            log.trace("No such user was found. Perhaps you enter wrong login or password.");
            throw new EntityNotFoundException(e);
        } catch (WrongPasswordException e) {
            log.trace("Passwords are not equal. You entered wrong password..");
            throw new WrongPasswordException();
        }

        return user;
    }

}
