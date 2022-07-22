package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.model.exception.QueryException;
import timekeeping.my.model.dao.ActionDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.entity.Access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActionMySqlDao extends AbstractDao<Action> implements ActionDao {

    private static final Logger log = LoggerFactory.getLogger(ActionDao.class);

    @Override
    public Action getAction(Integer userId, Integer activityId) throws DaoException {
        Action action;
        String query = ConstantJDBC.GET_QUERY + getTable() + ConstantJDBC.WHERE + ConstantJDBC.ACTION_USER_ID + "=?" +
                ConstantJDBC.AND + ConstantJDBC.ACTION_ACTIVITY_ID + "=?";

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            stmt.setObject(1, userId);
            stmt.setObject(2, activityId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                action = getEntity(rs);
            } else {
                log.error("Fail to obtain action. Perhaps such action does not exist. Check ==> " + query);
                throw new DaoException();
            }
        } catch (SQLException e) {
            log.error("Fail to obtain action. Query does not committed. Check ==> " + query);
            throw new DaoException(e);
        }

        return action;
    }

    @Override
    public Action updateAction(Action action) throws DaoException {
        String query = ConstantJDBC.UPDATE + getTable() + ConstantJDBC.SET +
                getUpdateParams() + ConstantJDBC.WHERE + ConstantJDBC.ACTION_USER_ID + '=' + action.getUser().getId()
                + ConstantJDBC.AND + ConstantJDBC.ACTION_ACTIVITY_ID + '=' + action.getActivity().getId();

        try(PreparedStatement stmt = connection.getConnection().prepareStatement(query)) {
            setUpdateParams(action, stmt);
            int i = stmt.executeUpdate();
            if(i != 1) {
                log.error("No item was updating. Perhaps entered fields are not suit ==> " + query);
                throw new DaoException();
            } else {
                return action;
            }
        } catch (SQLException e) {
            log.error("No item was updating. Query was wrong. Check ==> " + query);
            throw new DaoException("Something went wrong while updating...", e);
        }
    }

    @Override
    protected void setInsertParams(Action action, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setInt(++k, action.getUser().getId());
        stmt.setInt(++k, action.getActivity().getId());
        stmt.setInt(++k, action.getLast());
        stmt.setInt(++k, action.getTotal());
        stmt.setInt(++k, action.getAccess().getId());

    }

    @Override
    protected String getInsertFields() {
        return String.join(", ", ConstantJDBC.ACTION_FIELDS);
    }

    @Override
    protected String getInsertParamsFields() {
        return "?, ?, ?, ?, ?";
    }

    @Override
    protected String getTable() {
        return ConstantJDBC.ACTION_TABLE_NAME;
    }

    @Override
    protected Action getEntity(ResultSet rs) throws SQLException {
        Action action = new Action();
        action.setActivity(new Activity(rs.getInt(ConstantJDBC.ACTION_ACTIVITY_ID)));
        action.setUser(new User(rs.getInt(ConstantJDBC.ACTION_USER_ID)));
        action.setTotalSpent(rs.getInt(ConstantJDBC.ACTION_SPENT_TIME_ALL));
        action.setSpentLastSession(rs.getInt(ConstantJDBC.ACTION_SPENT_TIME_LAST));
        action.setAccess(Access.getAccess(rs.getInt(ConstantJDBC.ACTION_ACCESS_ID)));

        log.trace("Action was obtained ==> " + action);
        return action;
    }

    @Override
    protected String getUpdateParams() {
        StringBuilder query = new StringBuilder();
        query.append(ConstantJDBC.ACTION_FIELDS[2]).append("=?, ");
        query.append(ConstantJDBC.ACTION_FIELDS[3]).append("=?, ");
        query.append(ConstantJDBC.ACTION_FIELDS[4]).append("=?");

        log.trace("Update params ==> " + query);
        return query.toString();
    }

    @Override
    protected void setUpdateParams(Action action, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setInt(++k, action.getLast());
        stmt.setInt(++k, action.getTotal());
        stmt.setInt(++k, action.getAccess().getId());

    }
}
