package timekeeping.my.service;

import timekeeping.my.model.entity.Activity;
import timekeeping.my.service.exception.EntityNotFoundException;

import java.util.List;

public interface ActivityService extends Service<Activity> {

    /**
     * gets activity by name
     * @param name the name of activity
     * @return activity the activity which was found
     * @throws EntityNotFoundException in case when does not exist activity with specified name
     * */
    Activity getActivity(String name) throws EntityNotFoundException;

    /**
     * gets available activities where status equals public
     * @return list of available activities
     * @throws EntityNotFoundException in case when does not exist available activities
     * */
    List<Activity> getAvailableActivities() throws EntityNotFoundException;
}
