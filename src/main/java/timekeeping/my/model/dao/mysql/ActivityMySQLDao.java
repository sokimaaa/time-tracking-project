package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.ActivityDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMySQLDao extends AbstractDao<Activity> implements ActivityDao {

    private static final Logger log = LoggerFactory.getLogger(ActivityDao.class);

    @Override
    protected void setInsertParams(Activity activity, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setString(++k, activity.getName());
        stmt.setInt(++k, activity.getCategory().getId());
        stmt.setInt(++k, activity.getCity().getId());
        stmt.setString(++k, activity.getDescription());
        stmt.setInt(++k, activity.getStatus().getId());
        stmt.setObject(++k, activity.getCount());
        stmt.setInt(++k, activity.getOwner().getId());

    }

    @Override
    protected void setUpdateParams(Activity activity, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setInt(++k, activity.getStatus().getId());
        stmt.setObject(++k, activity.getCount());

    }

    @Override
    protected String getInsertFields() {
        return String.join(", ", ConstantJDBC.ACTIVITY_FIELDS[1], ConstantJDBC.ACTIVITY_FIELDS[2],
                ConstantJDBC.ACTIVITY_FIELDS[3], ConstantJDBC.ACTIVITY_FIELDS[4],
                ConstantJDBC.ACTIVITY_FIELDS[5], ConstantJDBC.ACTIVITY_FIELDS[6],
                ConstantJDBC.ACTIVITY_FIELDS[7]);
    }

    @Override
    protected String getInsertParamsFields() {
        return "?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    protected String getTable() {
        return ConstantJDBC.ACTIVITY_TABLE_NAME;
    }

    @Override
    protected Activity getEntity(ResultSet rs) throws SQLException {
        Activity activity = new Activity();
        activity.setId(rs.getInt(ConstantJDBC.ACTIVITY_ID));
        activity.setName(rs.getString(ConstantJDBC.ACTIVITY_NAME));
        activity.setStatus(Status.getStatus(rs.getInt(ConstantJDBC.ACTIVITY_STATUS_ID)));
        activity.setCategory(new Category(rs.getInt(ConstantJDBC.ACTIVITY_CATEGORY_ID)));
        activity.setCity(new City(rs.getInt(ConstantJDBC.ACTIVITY_CITY_ID)));
        activity.setDescriptions(rs.getString(ConstantJDBC.ACTIVITY_DESCRIPTION));
        activity.setCount(new BigInteger(rs.getString(ConstantJDBC.ACTIVITY_COUNT)));
        activity.setOwner(new User(rs.getInt(ConstantJDBC.ACTIVITY_OWNER)));

        log.trace("Activity was obtained ==> " + activity);
        return activity;
    }

    @Override
    protected String getUpdateParams() {
        StringBuilder query = new StringBuilder();
        query.append(ConstantJDBC.ACTIVITY_STATUS_ID).append("=?, ");
        query.append(ConstantJDBC.ACTIVITY_COUNT).append("=?");

        log.trace("Update params ==> " + query);
        return query.toString();
    }

    @Override
    public Activity getActivity(String name) throws DaoException {
        return getEntityByParameter(name, ConstantJDBC.ACTIVITY_NAME);
    }
}
