package dao;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.dao.UserDao;
import timekeeping.my.model.dao.mysql.DaoFactoryMySQL;
import timekeeping.my.model.dao.mysql.UserMySQLDao;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoTest {

    private static final ConnectionWrapper connectionWrapper = Mockito.mock(ConnectionWrapper.class);
    private static final Connection connection = Mockito.mock(Connection.class);
    private static final PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    private static final ResultSet resultSet = Mockito.mock(ResultSet.class);


    @BeforeClass
    public static void globalSetUp() throws Exception {
        daoFactory = DaoFactoryMySQL.getInstance();
        Mockito.when(connectionWrapper.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
    }

    private static DaoFactory daoFactory;

    @Before
    public void setUp() {
        userDao = (UserDao) daoFactory.<User>getDAO(UserMySQLDao.class, connectionWrapper);
    }

    private static UserDao userDao;

    @Test
    public void testInsertTrue() throws SQLException {
        User expectedUser = new User(1);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        User actualUser = userDao.insert(expectedUser);

        Assert.assertTrue(CoreMatchers.is(actualUser).matches(expectedUser));
    }

    @Test(expected = DaoException.class)
    public void testInsertFalse() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        userDao.insert(new User());
    }

    @Test
    public void testUpdateTrue() throws SQLException {
        User expectedUser = new User();
        expectedUser.setCity(new City(1));
        expectedUser.setSex(Sex.MALE);
        expectedUser.setAccess(Access.ALLOW);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        User actualUser = userDao.update(expectedUser);

        Assert.assertTrue(CoreMatchers.is(actualUser).matches(expectedUser));
    }

    @Test(expected = DaoException.class)
    public void testUpdateFalseNull() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        userDao.update(new User());
    }

    @Test(expected = DaoException.class)
    public void testUpdateFalse() throws SQLException {
        User user = new User();
        user.setCity(new City(1));
        user.setSex(Sex.MALE);
        user.setAccess(Access.ALLOW);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        userDao.update(user);
    }

    @Test
    public void testRemoveTrue() throws SQLException {
        int expectedId = 1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        int actualId = userDao.remove(expectedId);

        Assert.assertTrue(CoreMatchers.is(actualId).matches(expectedId));
    }

    @Test(expected = DaoException.class)
    public void testRemoveFalse() throws SQLException {
        int expectedId = -1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        userDao.remove(expectedId);
    }

    @Test
    public void testGet() throws SQLException {
        int id = 1;
        int roleId = 1;
        String username = "Ivan";
        String password = "password";
        String email = "email@gmail.com";
        String phone = "0952562858";
        int sexId = 1;
        int cityId = 1;
        int accessId = 1;

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ID)).thenReturn(id);
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ROLE_ID)).thenReturn(roleId);
        Mockito.when(resultSet.getString(ConstantJDBC.USER_USERNAME)).thenReturn(username);
        Mockito.when(resultSet.getString(ConstantJDBC.USER_PASSWORD)).thenReturn(password);
        Mockito.when(resultSet.getString(ConstantJDBC.USER_EMAIL)).thenReturn(email);
        Mockito.when(resultSet.getString(ConstantJDBC.USER_PHONE)).thenReturn(phone);
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_SEX_ID)).thenReturn(sexId);
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_CITY_ID)).thenReturn(cityId);
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ACCESS_ID)).thenReturn(accessId);

        User expectedUser = new User();
        expectedUser.setId(id);
        expectedUser.setRole(Role.getRole(roleId));
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);
        expectedUser.setEmail(email);
        expectedUser.setPhone(phone);
        expectedUser.setSex(Sex.getSex(sexId));
        expectedUser.setCity(new City(cityId));
        expectedUser.setAccess(Access.getAccess(accessId));

        User actualUser = userDao.get(1);

        Assert.assertTrue(CoreMatchers.is(actualUser).matches(expectedUser));
    }

    @Test
    public void testGetAll() throws SQLException {

        List<User> expectedUsers = new ArrayList<>();

        User expectedUserIvan = new User();
        User expectedUserLana = new User();

        List<Integer> usersId = new ArrayList<>() {
            {
                add(1);
                add(2);
            }
        };
        List<Integer> usersRoleId = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };
        List<String> usersUsername = new ArrayList<>() {
            {
                add("Ivan");
                add("Lana");
            }
        };
        List<String> usersPassword = new ArrayList<>() {
            {
                add("password");
                add("password");
            }
        };
        List<String> usersEmail = new ArrayList<>() {
            {
                add("emailIvan@gmail.com");
                add("emailLana@gmail.com");
            }
        };
        List<String> usersPhone = new ArrayList<>() {
            {
                add("0952562877");
                add("0953362858");
            }
        };
        List<Integer> usersSexId = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };
        List<Integer> usersCityId = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };
        List<Integer> usersAccessId = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };

        expectedUserIvan.setId(usersId.get(0));
        expectedUserIvan.setRole(Role.getRole(usersRoleId.get(0)));
        expectedUserIvan.setUsername(usersUsername.get(0));
        expectedUserIvan.setPassword(usersPassword.get(0));
        expectedUserIvan.setEmail(usersEmail.get(0));
        expectedUserIvan.setPhone(usersPhone.get(0));
        expectedUserIvan.setSex(Sex.getSex(usersSexId.get(0)));
        expectedUserIvan.setCity(new City(usersCityId.get(0)));
        expectedUserIvan.setAccess(Access.getAccess(usersAccessId.get(0)));

        expectedUserLana.setId(usersId.get(1));
        expectedUserLana.setRole(Role.getRole(usersRoleId.get(1)));
        expectedUserLana.setUsername(usersUsername.get(1));
        expectedUserLana.setPassword(usersPassword.get(1));
        expectedUserLana.setEmail(usersEmail.get(1));
        expectedUserLana.setPhone(usersPhone.get(1));
        expectedUserLana.setSex(Sex.getSex(usersSexId.get(1)));
        expectedUserLana.setCity(new City(usersCityId.get(1)));
        expectedUserLana.setAccess(Access.getAccess(usersAccessId.get(1)));

        expectedUsers.add(expectedUserIvan);
        expectedUsers.add(expectedUserLana);

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ID)).thenReturn(usersId.get(0)).thenReturn(usersId.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ROLE_ID)).thenReturn(usersRoleId.get(0)).thenReturn(usersRoleId.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.USER_USERNAME)).thenReturn(usersUsername.get(0)).thenReturn(usersUsername.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.USER_PASSWORD)).thenReturn(usersPassword.get(0)).thenReturn(usersPassword.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.USER_EMAIL)).thenReturn(usersEmail.get(0)).thenReturn(usersEmail.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.USER_PHONE)).thenReturn(usersPhone.get(0)).thenReturn(usersPhone.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_SEX_ID)).thenReturn(usersSexId.get(0)).thenReturn(usersSexId.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_CITY_ID)).thenReturn(usersCityId.get(0)).thenReturn(usersCityId.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.USER_ACCESS_ID)).thenReturn(usersAccessId.get(0)).thenReturn(usersAccessId.get(1));

        List<User> actualUsers = userDao.getAll();

        Assert.assertTrue(CoreMatchers.is(actualUsers).matches(expectedUsers));
        Assert.assertTrue(CoreMatchers.is(actualUsers.get(0)).matches(expectedUsers.get(0)));
    }

}
