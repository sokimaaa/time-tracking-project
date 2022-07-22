package dao;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.ActivityDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.dao.mysql.ActivityMySQLDao;
import timekeeping.my.model.dao.mysql.DaoFactoryMySQL;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoTest {
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
        activityDao = (ActivityDao) daoFactory.<Activity>getDAO(ActivityMySQLDao.class, connectionWrapper);
    }

    private static ActivityDao activityDao;

    @Test
    public void testInsertTrue() throws SQLException {
        Activity expectedActivity = new Activity(1);
        expectedActivity.setCity(new City(1));
        expectedActivity.setCategory(new Category(1));
        expectedActivity.setStatus(Status.PUBLIC);
        expectedActivity.setOwner(new User(1));

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Activity actualActivity = activityDao.insert(expectedActivity);

        Assert.assertTrue(CoreMatchers.is(actualActivity).matches(expectedActivity));
    }

    @Test(expected = DaoException.class)
    public void testInsertFalse() throws SQLException {
        Activity activity = new Activity(1);
        activity.setCity(new City(1));
        activity.setCategory(new Category(1));
        activity.setStatus(Status.PUBLIC);
        activity.setOwner(new User(1));

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        activityDao.insert(activity);
    }

    @Test
    public void testUpdateTrue() throws SQLException {
        Activity expectedActivity = new Activity();
        expectedActivity.setCount(new BigInteger("1023"));
        expectedActivity.setStatus(Status.PRIVATE);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Activity actualActivity = activityDao.update(expectedActivity);

        Assert.assertTrue(CoreMatchers.is(actualActivity).matches(expectedActivity));
    }

    @Test(expected = DaoException.class)
    public void testUpdateFalseNull() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        activityDao.update(new Activity());
    }

    @Test(expected = DaoException.class)
    public void testUpdateFalse() throws SQLException {
        Activity activity = new Activity();
        activity.setCount(new BigInteger("1023"));
        activity.setStatus(Status.PRIVATE);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        activityDao.update(activity);
    }

    @Test
    public void testRemoveTrue() throws SQLException {
        int expectedId = 1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        int actualId = activityDao.remove(expectedId);

        Assert.assertTrue(CoreMatchers.is(actualId).matches(expectedId));
    }

    @Test(expected = DaoException.class)
    public void testRemoveFalse() throws SQLException {
        int expectedId = -1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        activityDao.remove(expectedId);
    }

    @Test
    public void testGet() throws SQLException {

        int id = 1;
        String name = "Java Course";
        int statusId = 2;
        int categoryId = 2;
        int cityId = 1;
        String description = "empty";
        String count = "10023";
        int ownerId = 2;

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_ID)).thenReturn(id);
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_NAME)).thenReturn(name);
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_STATUS_ID)).thenReturn(statusId);
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_CATEGORY_ID)).thenReturn(categoryId);
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_CITY_ID)).thenReturn(cityId);
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_DESCRIPTION)).thenReturn(description);
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_COUNT)).thenReturn(count);
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_OWNER)).thenReturn(ownerId);

        Activity expectedActivity = new Activity();
        expectedActivity.setId(id);
        expectedActivity.setName(name);
        expectedActivity.setStatus(Status.getStatus(statusId));
        expectedActivity.setCategory(new Category(categoryId));
        expectedActivity.setCity(new City(cityId));
        expectedActivity.setDescriptions(description);
        expectedActivity.setCount(new BigInteger(count));
        expectedActivity.setOwner(new User(ownerId));

        Activity actualActivity = activityDao.get(1);

        Assert.assertTrue(CoreMatchers.is(actualActivity).matches(expectedActivity));
    }

    @Test
    public void testGetAll() throws SQLException {

        List<Activity> expectedActivity = new ArrayList<>();

        Activity expectedActivityJava = new Activity();
        Activity expectedActivityGo = new Activity();

        List<Integer> activitiesId = new ArrayList<>() {
            {
                add(1);
                add(2);
            }
        };
        List<String> activitiesName = new ArrayList<>() {
            {
                add("Java Course");
                add("Golang Course");
            }
        };
        List<Integer> activitiesStatusId = new ArrayList<>() {
            {
                add(2);
                add(2);
            }
        };
        List<Integer> activitiesCategoryId = new ArrayList<>() {
            {
                add(2);
                add(2);
            }
        };
        List<Integer> activitiesCityId = new ArrayList<>() {
            {
                add(2);
                add(2);
            }
        };
        List<String> activitiesDescription = new ArrayList<>() {
            {
                add("Java Course Winter");
                add("Golang Course Winter");
            }
        };
        List<String> activitiesCount = new ArrayList<>() {
            {
                add("1000");
                add("2323");
            }
        };
        List<Integer> activitiesOwnerId = new ArrayList<>() {
            {
                add(1);
                add(1);
            }
        };

        expectedActivityJava.setId(activitiesId.get(0));
        expectedActivityJava.setName(activitiesName.get(0));
        expectedActivityJava.setStatus(Status.getStatus(activitiesStatusId.get(0)));
        expectedActivityJava.setCategory(new Category(activitiesCategoryId.get(0)));
        expectedActivityJava.setCity(new City(activitiesCityId.get(0)));
        expectedActivityJava.setDescriptions(activitiesDescription.get(0));
        expectedActivityJava.setCount(new BigInteger(activitiesCount.get(0)));
        expectedActivityJava.setOwner(new User(activitiesOwnerId.get(0)));

        expectedActivityGo.setId(activitiesId.get(1));
        expectedActivityGo.setName(activitiesName.get(1));
        expectedActivityGo.setStatus(Status.getStatus(activitiesStatusId.get(1)));
        expectedActivityGo.setCategory(new Category(activitiesCategoryId.get(1)));
        expectedActivityGo.setCity(new City(activitiesCityId.get(1)));
        expectedActivityGo.setDescriptions(activitiesDescription.get(1));
        expectedActivityGo.setCount(new BigInteger(activitiesCount.get(1)));
        expectedActivityGo.setOwner(new User(activitiesOwnerId.get(1)));

        expectedActivity.add(expectedActivityJava);
        expectedActivity.add(expectedActivityGo);

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_ID)).thenReturn(activitiesId.get(0)).thenReturn(activitiesId.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_NAME)).thenReturn(activitiesName.get(0)).thenReturn(activitiesName.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_STATUS_ID)).thenReturn(activitiesStatusId.get(0)).thenReturn(activitiesStatusId.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_CATEGORY_ID)).thenReturn(activitiesCategoryId.get(0)).thenReturn(activitiesCategoryId.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_CITY_ID)).thenReturn(activitiesCityId.get(0)).thenReturn(activitiesCityId.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_DESCRIPTION)).thenReturn(activitiesDescription.get(0)).thenReturn(activitiesDescription.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.ACTIVITY_COUNT)).thenReturn(activitiesCount.get(0)).thenReturn(activitiesCount.get(1));
        Mockito.when(resultSet.getInt(ConstantJDBC.ACTIVITY_OWNER)).thenReturn(activitiesOwnerId.get(0)).thenReturn(activitiesOwnerId.get(1));

        List<Activity> actualActivities = activityDao.getAll();

        Assert.assertTrue(CoreMatchers.is(actualActivities).matches(expectedActivity));
        Assert.assertTrue(CoreMatchers.is(actualActivities.get(0)).matches(expectedActivity.get(0)));
    }
}
