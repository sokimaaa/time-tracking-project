package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.model.exception.QueryException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T extends Entity> implements Dao<T> {

    private static final Logger log = LoggerFactory.getLogger(AbstractDao.class);
    protected ConnectionWrapper connection;

    @Override
    public T insert(T t) throws DaoException {
        String query = ConstantJDBC.INSERT + getTable() + " (" + getInsertFields() + ')'
                + ConstantJDBC.VALUES + '(' + getInsertParamsFields() + ')';

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            setInsertParams(t, stmt);
            int i = stmt.executeUpdate();
            if(i != 1) {
                log.error("Item " + t + " was not inserted.");
                throw new QueryException("No item was saving. Perhaps query was wrong ==>" + query);
            } else {
                return t;
            }
        } catch (SQLException e) {
            log.error("Fail to insert item. Query ==> " + query);
            throw new DaoException("Insert was failed...", e);
        } catch (NullPointerException e) {
            log.error("Trying to insert with empty (null) parameters..");
            throw new DaoException("Update was failed...", e);
        }
    }

    @Override
    public T update(T t) throws DaoException {
        String query = ConstantJDBC.UPDATE + getTable() + ConstantJDBC.SET +
                getUpdateParams() + ConstantJDBC.WHERE + ConstantJDBC.ENTITY_ID + '=' + t.getId();

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            setUpdateParams(t, stmt);
            int i = stmt.executeUpdate();
            if(i != 1) {
                log.error("Item " + t + " was not updated.");
                throw new QueryException("Item was not updated. Perhaps query was wrong ==> " + query);
            } else {
                return t;
            }
        } catch (SQLException e) {
            log.error("Fail to update item. Query ==> " + query);
            throw new DaoException("Update was failed...", e);
        } catch (NullPointerException e) {
            log.error("Trying to update with empty (null) parameters..");
            throw new DaoException("Update was failed...", e);
        }
    }

    @Override
    public int remove(int id) throws DaoException {
        String query = ConstantJDBC.DELETE + ConstantJDBC.FROM + getTable() +
                ConstantJDBC.WHERE + ConstantJDBC.ENTITY_ID + '=' + id;
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            int i = stmt.executeUpdate();
            if(i != 1) {
                log.error("Item with id=" + id + " was not deleted.");
                throw new QueryException("Item was not deleted. Perhaps query was wrong ==> " + query);
            }
            return id;
        } catch (SQLException e) {
            log.error("Fail to delete item. Query ==> " + query);
            throw new DaoException("Delete was failed...", e);
        }
    }

    @Override
    public T get(int id) throws DaoException {
        return getEntityByParameter(id, ConstantJDBC.ENTITY_ID);
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> result = new ArrayList<>();
        String query = ConstantJDBC.GET_QUERY + getTable();
        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(getEntity(rs));
            }
            return result;
        } catch (SQLException e) {
            log.error("Fail while getting all entities. Query ==> " + query);
            throw new DaoException("Fail to get all entities.", e);
        }
    }

    public void setConnection(ConnectionWrapper connection) {
        this.connection = connection;
    }

    protected T getEntityByParameter(Object value, String parameter) throws DaoException {
        T result;
        String query = ConstantJDBC.GET_QUERY + getTable() + ConstantJDBC.WHERE + parameter + "=?";

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setObject(1, value);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                result = getEntity(rs);
            } else {
                log.error("Nothing was found by parameter=" + parameter + ", value=" + value);
                throw new QueryException("Nothing was found. Perhaps query was wrong ==> " + query);
            }
        } catch (SQLException e) {
            log.error("Fail to get entity by parameter. Query ==> " + query);
            throw new DaoException("Entity was not obtained by parameter.", e);
        }

        return result;
    }

    /**
     * set the insertion's params
     * */
    protected abstract void setInsertParams(T t, PreparedStatement stmt) throws SQLException;

    /**
     * set the update's params
     * */
    protected abstract void setUpdateParams(T t, PreparedStatement stmt) throws SQLException;

    /**
     * @return string the string names of inserting field separated by comma
     * */
    protected abstract String getInsertFields();

    /**
     * @return string the string parameters of inserting field separated by comma
     * */
    protected abstract String getInsertParamsFields();

    /**
     * @return string the table's name
     * */
    protected abstract String getTable();

    /**
     * @return T the Entity
     * */
    protected abstract T getEntity(ResultSet rs) throws SQLException;

    /**
     * @return string the list parameters of updating field separated by comma
     * */
    protected abstract String getUpdateParams();

}
