package dao;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.CategoryDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.DaoFactory;
import timekeeping.my.model.dao.mysql.CategoryMySqlDao;
import timekeeping.my.model.dao.mysql.DaoFactoryMySQL;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoTest {

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
        categoryDao = (CategoryDao) daoFactory.<Category>getDAO(CategoryMySqlDao.class, connectionWrapper);
    }

    private static CategoryDao categoryDao;

    @Test
    public void testInsertTrue() throws SQLException {
        Category expectedCategory = new Category(1);
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Category actualCategory = categoryDao.insert(expectedCategory);

        Assert.assertTrue(CoreMatchers.is(actualCategory).matches(expectedCategory));
    }

    @Test(expected = DaoException.class)
    public void testInsertFalse() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        categoryDao.insert(new Category());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateTrue() throws SQLException {
        Category expectedCategory = new Category(1);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Category actualCategory = categoryDao.update(expectedCategory);

        Assert.assertTrue(CoreMatchers.is(actualCategory).matches(expectedCategory));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateFalseNull() throws SQLException {
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        categoryDao.update(new Category());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateFalse() throws SQLException {
        Category category = new Category(1);

        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        categoryDao.update(category);
    }

    @Test
    public void testRemoveTrue() throws SQLException {
        int expectedId = 1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        int actualId = categoryDao.remove(expectedId);

        Assert.assertTrue(CoreMatchers.is(actualId).matches(expectedId));
    }

    @Test(expected = DaoException.class)
    public void testRemoveFalse() throws SQLException {
        int expectedId = -1;
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(0);
        categoryDao.remove(expectedId);
    }

    @Test
    public void testGet() throws SQLException {
        int id = 1;
        String name = "sport";

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true);

        Mockito.when(resultSet.getInt(ConstantJDBC.CATEGORY_ID)).thenReturn(id);
        Mockito.when(resultSet.getString(ConstantJDBC.CATEGORY_NAME)).thenReturn(name);

        Category expectedCategory = new Category();
        expectedCategory.setId(id);
        expectedCategory.setCategory(name);

        Category actualCategory = categoryDao.get(1);

        Assert.assertTrue(CoreMatchers.is(actualCategory).matches(expectedCategory));
    }

    @Test
    public void testGetAll() throws SQLException {
        List<Category> expectedCategories = new ArrayList<>();

        Category expectedCategorySport = new Category();
        Category expectedCategoryOther = new Category();

        List<Integer> categoriesId = new ArrayList<>() {
            {
                add(1);
                add(2);
            }
        };
        List<String> categoriesName = new ArrayList<>() {
            {
                add("Sport");
                add("Other");
            }
        };

        expectedCategorySport.setId(categoriesId.get(0));
        expectedCategorySport.setCategory(categoriesName.get(0));

        expectedCategoryOther.setId(categoriesId.get(1));
        expectedCategoryOther.setCategory(categoriesName.get(1));

        expectedCategories.add(expectedCategorySport);
        expectedCategories.add(expectedCategoryOther);

        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        Mockito.when(resultSet.getInt(ConstantJDBC.CATEGORY_ID)).thenReturn(categoriesId.get(0)).thenReturn(categoriesId.get(1));
        Mockito.when(resultSet.getString(ConstantJDBC.CATEGORY_NAME)).thenReturn(categoriesName.get(0)).thenReturn(categoriesName.get(1));

        List<Category> actualCategories = categoryDao.getAll();

        Assert.assertTrue(CoreMatchers.is(actualCategories).matches(expectedCategories));
        Assert.assertTrue(CoreMatchers.is(actualCategories.get(0)).matches(expectedCategories.get(0)));
    }
}
