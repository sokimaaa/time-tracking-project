package timekeeping.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.ActivityDao;
import timekeeping.my.model.dao.mysql.ActivityMySQLDao;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.entity.*;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.service.*;
import timekeeping.my.service.exception.EntityNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class ActivityServiceImpl extends AbstractService<Activity> implements ActivityService {

    private static final Logger log = LoggerFactory.getLogger(ActivityService.class);
    private final CategoryService categoryService;
    private final CityService cityService;
    private final UserService userService;

    public ActivityServiceImpl() {
        categoryService = (CategoryService) ServiceFactory.getInstance().<Category>getService(CategoryServiceImpl.class);
        cityService = (CityService) ServiceFactory.getInstance().<City>getService(CityServiceImpl.class);
        userService = (UserService) ServiceFactory.getInstance().<User>getService(UserServiceImpl.class);

    }

    @Override
    public void fillItem(Activity activity) throws EntityNotFoundException {
        activity.setCategory(categoryService.get(activity.getCategory().getId()));
        activity.setCity(cityService.get(activity.getCity().getId()));
        activity.setOwner(userService.get(activity.getOwner().getId()));

        log.trace("Activity's fields were filled.");
    }

    @Override
    protected Dao<Activity> getDAO(ConnectionWrapper connection) {
        log.trace("Getting activity dao. Connection ==> " + connection);
        return daoFactory.getDAO(ActivityMySQLDao.class, connection);
    }

    @Override
    protected void processEntity(Activity activity) {

    }

    @Override
    public Activity getActivity(String name) throws EntityNotFoundException {
        Activity activity;
        try {
            activity = ((ActivityDao) getDAO(getConnection())).getActivity(name);
            log.trace("Activity was found by name ==> " + name);
        } catch (DaoException e) {
            log.trace("Activity was not found. There is no activity named ==> " + name);
            throw new EntityNotFoundException(e);
        }
        return activity;
    }

    @Override
    public List<Activity> getAvailableActivities() throws EntityNotFoundException {
        return getAll().stream()
                .filter(activity -> activity.getStatus().equals(Status.PUBLIC))
                .collect(Collectors.toList());
    }
}
