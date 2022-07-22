package dao;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.CityDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.dao.mysql.CityMySQLDao;
import timekeeping.my.model.dao.mysql.DaoFactoryMySQL;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDaoTest {

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
        cityDao = (CityDao) daoFactory.<City>getDAO(CityMySQLDao.class, connectionWrapper);
    }

    private static CityDao cityDao;

    @Test(expected = UnsupportedOperationException.class)
    public void testInsertTrue() throws SQLException {
        City expectedCity = new City(1);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        City actualCity = cityDao.insert(expectedCity);

        Assert.assertTrue(CoreMatchers.is(actualCity).matches(expectedCity));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInsertFalse() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        cityDao.insert(new City());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateTrue() throws SQLException {
        City expectedCity = new City(1);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        City actualCity = cityDao.update(expectedCity);

        Assert.assertTrue(CoreMatchers.is(actualCity).matches(expectedCity));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateFalseNull() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        cityDao.update(new City());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateFalse() throws SQLException {
        City city = new City(1);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        cityDao.update(city);
    }

    @Test
    public void testRemoveTrue() throws SQLException {
        int expectedId = 1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        int actualId = cityDao.remove(expectedId);

        Assert.assertTrue(CoreMatchers.is(actualId).matches(expectedId));
    }

    @Test(expected = DaoException.class)
    public void testRemoveFalse() throws SQLException {
        int expectedId = -1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        cityDao.remove(expectedId);
    }

    @Test
    public void testGet() throws SQLException {
        int id = 1;
        String name = "Kharkov";

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt(ConstantJDBC.CITY_ID)).thenReturn(id);
        Mockito.when(resultSet.getString(ConstantJDBC.CITY_NAME)).thenReturn(name);

        City expectedCity = new City();
        expectedCity.setId(id);
        expectedCity.setName(name);

        City actualCity = cityDao.get(1);

        Assert.assertTrue(CoreMatchers.is(actualCity).matches(expectedCity));
    }

    @Test
    public void testGetAll() throws SQLException {
        List<City> expectedCities = new ArrayList<>();

        City expectedCityKyiv = new City();
        City expectedCityKharkov = new City();

        List<Integer> citiesId = new ArrayList<>() {
            {
                add(1);
                add(2);
            }
        };
        List<String> citiesName = new ArrayList<>() {
            {
                add("Kyiv");
                add("Kharkov");
            }
        };

        expectedCityKyiv.setId(citiesId.get(0));
        expectedCityKyiv.setName(citiesName.get(0));

        expectedCityKharkov.setId(citiesId.get(1));
        expectedCityKharkov.setName(citiesName.get(1));

        expectedCities.add(expectedCityKyiv);
        expectedCities.add(expectedCityKharkov);

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getInt(ConstantJDBC.CITY_ID)).thenReturn(citiesId.get(0)).thenReturn(citiesId.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.CITY_NAME)).thenReturn(citiesName.get(0)).thenReturn(citiesName.get(1));

        List<City> actualCities = cityDao.getAll();

        System.out.println("actual ===>" + actualCities);
        System.out.println("expected ===>" + expectedCities);

        Assert.assertTrue(CoreMatchers.is(actualCities).matches(expectedCities));
        Assert.assertTrue(CoreMatchers.is(actualCities.get(0)).matches(expectedCities.get(0)));
    }
}
