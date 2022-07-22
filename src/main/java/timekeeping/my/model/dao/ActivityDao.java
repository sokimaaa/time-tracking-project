package timekeeping.my.model.dao;

import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.exception.DaoException;

/**
 * The interface Activity DAO
 * */
public interface ActivityDao extends Dao<Activity> {

    /**
     * Take all activities from database by category.
     *
     * @param category the Category
     * @return List<Activity> the activities
     **/
//    List<Activity> getActivitiesByCategory(Category category);

    /**
     * Take all activities from database by status.
     *
     * @param status the Status
     * @return List<Activity> the activities
     **/
//    List<Activity> getActivitiesByStatus(Status status);

    /**
     * gets activity by name
     * @param name the name activity
     * @return activity the activity
     * @throws DaoException
     * */
    Activity getActivity(String name) throws DaoException;

}
