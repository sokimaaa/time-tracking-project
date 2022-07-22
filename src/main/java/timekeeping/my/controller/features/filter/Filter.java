package timekeeping.my.controller.features.filter;

import timekeeping.my.model.entity.Activity;

import java.util.List;

/**
 * The interface Filter
 * */
public interface Filter {

    /**
     * filters list of activity
     * @param activities the list to filter
     * @return the filtered list
     * */
    List<Activity> filter(List<Activity> activities);
}
