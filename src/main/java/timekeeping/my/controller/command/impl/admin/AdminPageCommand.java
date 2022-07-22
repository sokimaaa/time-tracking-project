package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.controller.features.filter.StatusFilter;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.CategoryService;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.impl.CategoryServiceImpl;
import timekeeping.my.service.impl.UserServiceImpl;
import timekeeping.my.service.ActionService;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.impl.ActionServiceImpl;
import timekeeping.my.view.form.ConstantForm;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * command to redirect admin page
 * and to open specify tab
 * */
public class AdminPageCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(AdminPageCommand.class);
    private final UserService userService;
    private final ActivityService activityService;
    private final CategoryService categoryService;
    private final ActionService actionService;

    public AdminPageCommand() {
        userService = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        categoryService = (CategoryService) serviceFactory.<Category>getService(CategoryServiceImpl.class);
        actionService = (ActionService) serviceFactory.<Action>getService(ActionServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        setAllAttributes(request);
        log.trace("Forwarding to the admin page");
        return "/admin.jsp";
    }

    /**
     * sets all attributes
     * - users: all users
     * - activities: all activities
     * - categories: all categories
     * - requests: all user's requests for updating
     * */
    private void setAllAttributes(HttpServletRequest request) {
        // USERS
        setUsers(request);

        // ACTIVITIES
        setActivities(request);

        // CATEGORIES
        setCategories(request);

        // REQUESTS
        setRequests(request);
    }

    private void setRequests(HttpServletRequest request) {
        log.trace("Command request obtained.. Setting request's attribute...");
        try {
            List<Action> requests = actionService.getRequests();
            request.setAttribute("requests", requests);
            log.trace("Requests attribute is set.");
        } catch (EntityNotFoundException e) {
            log.error("Requests attribute is not set.");
        }
    }

    private void setCategories(HttpServletRequest request) {
        try {
            log.trace("Command categories obtained.. Setting categories attribute...");
            List<Category> categories = categoryService.getAll();
            Form categoriesForm = (Form) request.getSession().getAttribute("form");
            if(Objects.isNull(categoriesForm) || !categoriesForm.getId().equals(ConstantForm.FORM_ADMIN_CATEGORIES_ID))
                categoriesForm = formBuilder.getAdminCategoriesForm();
            request.setAttribute("form", categoriesForm);
            request.setAttribute("categories", categories);
            log.trace("Categories attribute is set.");
        } catch (EntityNotFoundException e) {
            log.error("Categories attribute is not set.");
        }
    }

    private void setActivities(HttpServletRequest request) {
        try {
            log.trace("Command activities obtained.. Setting activities attribute...");
            List<Activity> activities = activityService.getAll();
            activities = setFilter(activities, request.getParameter("filter"));
            request.setAttribute("activities", activities);
            log.trace("Activities attribute is set.");
        } catch (EntityNotFoundException e) {
            log.error("Activities attribute is not set.");
        }
    }

    private void setUsers(HttpServletRequest request) {
        try {
            log.trace("Command users obtained.. Setting attributes...");
            List<User> users = userService.getUsers();
            request.setAttribute("users", users);
            log.trace("Users attribute is set.");
        } catch (EntityNotFoundException e) {
            log.error("Users attribute is not set.");
        }
    }

    private List<Activity> setFilter(List<Activity> activities, String param) {
        if (Objects.nonNull(param)) {
            Filter filter = new StatusFilter(param);
            return filter.filter(activities);
        }
        return activities;
    }

}
