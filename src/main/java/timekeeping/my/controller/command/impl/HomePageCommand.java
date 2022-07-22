package timekeeping.my.controller.command.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.Command;
import timekeeping.my.model.entity.Action;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.ActivityService;
import timekeeping.my.service.CategoryService;
import timekeeping.my.service.Service;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.ActivityServiceImpl;
import timekeeping.my.service.impl.CategoryServiceImpl;
import timekeeping.my.util.Parser;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * command to forward to the home page
 * shows list of activities
 * */
public class HomePageCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(HomePageCommand.class);
    private final ActivityService activityService;
    private final CategoryService categoryService;

    public HomePageCommand() {
        activityService = (ActivityService) serviceFactory.<Activity>getService(ActivityServiceImpl.class);
        categoryService = (CategoryService) serviceFactory.<Category>getService(CategoryServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String uri = request.getRequestURI();

        String[] command = Parser.parseUri(uri);
        command[3] = "activities";
        command[4] = "3";
        log.info("Parse uri ===> " + Arrays.toString(command));
        try {
            List<Activity> activities = activityService.getAvailableActivities();
            List<Category> categories = categoryService.getAll();

            log.trace("start pagination...");
            pagination(request, activities, command);
            log.trace("end pagination...");

            request.setAttribute("categories", categories);
            log.trace("showing home page...");
            return "/home.jsp";
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain activities or categories.");
            log.trace("fail to show home page...");
            return "/error.jsp";
        }
    }

}
