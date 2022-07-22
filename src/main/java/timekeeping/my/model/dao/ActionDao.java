package timekeeping.my.model.dao;

import timekeeping.my.model.entity.Action;
import timekeeping.my.model.exception.DaoException;

/**
 * The interface Action DAO
 * */
public interface ActionDao extends Dao<Action> {
    /**
     * takes Action from database by user id and activity id
     * @param userId the user's id
     * @param activityId the activity's id
     * @return action the action
     * */
    Action getAction(Integer userId, Integer activityId) throws DaoException;

    /**
     * takes Action from database by user id and activity id
     * @param action the action to update
     * @return action the action which was updated
     * */
    Action updateAction(Action action) throws DaoException;
}
