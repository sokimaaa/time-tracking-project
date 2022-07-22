package timekeeping.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.ActionDao;
import timekeeping.my.model.dao.mysql.ActionMySqlDao;
import timekeeping.my.model.entity.Access;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.User;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.model.exception.QueryException;
import timekeeping.my.service.ServiceFactory;
import timekeeping.my.service.UserService;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ActionServiceImpl extends AbstractService<Action> implements ActionService {

    private static final Logger log = LoggerFactory.getLogger(ActionService.class);
    private final ActivityService activityService;
    private final UserService userService;

    public ActionServiceImpl() {
        activityService = (ActivityService) ServiceFactory.getInstance().<Activity>getService(ActivityServiceImpl.class);
        userService = (UserService) ServiceFactory.getInstance().<User>getService(UserServiceImpl.class);

    }

    @Override
    protected Dao<Action> getDAO(ConnectionWrapper connection) {
        log.trace("Getting action dao. Connection ==> " + connection);
        return daoFactory.getDAO(ActionMySqlDao.class, connection);
    }

    @Override
    protected void processEntity(Action action) {

    }

    @Override
    public void fillItem(Action action) throws EntityNotFoundException {
        action.setUser(userService.get(action.getUser().getId()));
        action.setActivity(activityService.get(action.getActivity().getId()));

        log.trace("Action's fields were filled.");
    }

    @Override
    public Action getAction(User user, Activity activity) throws EntityNotFoundException {
        try {
            return ((ActionDao) getDAO(getConnection())).getAction(user.getId(), activity.getId());
        } catch (DaoException e) {
            log.trace("Fail to obtain action. User: " + user.getId() + "; activity: " + activity.getId());
            throw new EntityNotFoundException(e);
        }
    }

    @Override
    public Action updateAction(Action action) throws UpdateEntityException {
        Action result;
        try {
            result = ((ActionDao) getDAO(getConnection())).updateAction(action);
            log.trace("Action was updated. Action ==> " + action);
        } catch (DaoException e) {
            log.trace("Action was not updated. Something went wrong.");
            throw new UpdateEntityException(e);
        }
        return result;
    }

    @Override
    public List<Action> getRequests() throws EntityNotFoundException {
        return getAll().stream()
                .filter(action -> action.getAccess().equals(Access.DENY))
                .collect(Collectors.toList());
    }

    @Override
    public List<Action> getTopList() throws EntityNotFoundException {
        return getAll().stream()
                .sorted((o1, o2) -> o2.getTotal().compareTo(o1.getTotal()))
                .limit(10)
                .collect(Collectors.toList());
    }
}
