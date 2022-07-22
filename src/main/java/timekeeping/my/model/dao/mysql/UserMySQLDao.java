package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.UserDao;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMySQLDao extends AbstractDao<User> implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    @Override
    protected void setInsertParams(User user, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setString(++k, user.getUsername());
        stmt.setString(++k, user.getPassword());
        stmt.setInt(++k, Access.ALLOW.getId());

    }

    @Override
    protected void setUpdateParams(User user, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setString(++k, user.getEmail());
        stmt.setString(++k, user.getPhone());
        stmt.setInt(++k, user.getSex().getId());
        stmt.setInt(++k, user.getCity().getId());
        stmt.setInt(++k, user.getAccess().getId());

    }

    @Override
    protected String getUpdateParams() {
        StringBuilder query = new StringBuilder();
        query.append(ConstantJDBC.USER_FIELDS[4]).append("=?, ");
        query.append(ConstantJDBC.USER_FIELDS[5]).append("=?, ");
        query.append(ConstantJDBC.USER_FIELDS[6]).append("=?, ");
        query.append(ConstantJDBC.USER_FIELDS[7]).append("=?, ");
        query.append(ConstantJDBC.USER_FIELDS[8]).append("=?");

        log.trace("Update params ==> " + query);
        return query.toString();
    }

    @Override
    protected String getInsertFields() {
        return String.join(", ", ConstantJDBC.USER_FIELDS[2], ConstantJDBC.USER_FIELDS[3],
                ConstantJDBC.USER_FIELDS[8]);
    }

    @Override
    protected String getInsertParamsFields() {
        return "?, ?, ?";
    }

    @Override
    protected String getTable() {
        return ConstantJDBC.USER_TABLE_NAME;
    }

    @Override
    protected User getEntity(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(ConstantJDBC.USER_ID));
        user.setRole(Role.getRole(rs.getInt(ConstantJDBC.USER_ROLE_ID)));
        user.setUsername(rs.getString(ConstantJDBC.USER_USERNAME));
        user.setPassword(rs.getString(ConstantJDBC.USER_PASSWORD));
        user.setEmail(rs.getString(ConstantJDBC.USER_EMAIL));
        user.setPhone(rs.getString(ConstantJDBC.USER_PHONE));
        user.setSex(Sex.getSex(rs.getInt(ConstantJDBC.USER_SEX_ID)));
        user.setCity(new City(rs.getInt(ConstantJDBC.USER_CITY_ID)));
        user.setAccess(Access.getAccess(rs.getInt(ConstantJDBC.USER_ACCESS_ID)));

        log.trace("User was obtained ==> " + user);
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws DaoException {
        return getEntityByParameter(login, ConstantJDBC.USER_USERNAME);
    }
}
