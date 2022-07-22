package timekeeping.my.service;

import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;

import java.util.List;

public interface ActionService extends Service<Action> {

    /**
     * takes action from database by user and activity
     * @param user the user
     * @param activity the activity
     * @return action the action of specify user and activity
     * @throws EntityNotFoundException in case if such action does not exist
     * */
    Action getAction(User user, Activity activity) throws EntityNotFoundException;

    /**
     * updates action in the database
     * @param action the action to update
     * @return action which was updated
     * @throws UpdateEntityException in case where update is failed
     * */
    Action updateAction(Action action) throws UpdateEntityException;

    /**
     * request meaning a user which send a request for adding to activities
     * or action what has denied access
     * @return list of actions
     * */
    List<Action> getRequests() throws EntityNotFoundException;

    /**
     * gets list of top 10 users by wasted total time
     * @return list of action
     * */
    List<Action> getTopList() throws EntityNotFoundException;
}
