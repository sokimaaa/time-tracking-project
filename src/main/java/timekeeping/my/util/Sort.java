package timekeeping.my.util;

import timekeeping.my.model.entity.Activity;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The Sort class provides tools to sort
 * */
public final class Sort {

    private static final Comparator<Activity> SORT_BY_CATEGORY = Comparator.comparing(o -> o.getCategory().getId());
    private static final Comparator<Activity> SORT_BY_NAME = Comparator.comparing(Activity::getName);
    private static final Comparator<Activity> SORT_BY_COUNT = (o1, o2) -> o2.getCount().compareTo(o1.getCount());

    /**
     * @param activities the list to sort
     * @param param the param by sort
     * */
    public static void sort(List<Activity> activities, String param) {
        switch (param) {
            case "name":
                sortByName(activities);
                break;
            case "category":
                sortByCategory(activities);
                break;
            case "count":
                sortByCount(activities);
                break;
            default:
                sortByNone(activities);
        }
    }

    private static void sortByCategory(List<Activity> activities) {
        Collections.sort(activities, SORT_BY_CATEGORY);
    }
    private static void sortByName(List<Activity> activities) {
        Collections.sort(activities, SORT_BY_NAME);
    }
    private static void sortByCount(List<Activity> activities) {
        Collections.sort(activities, SORT_BY_COUNT);
    }

    private static void sortByNone(List<Activity> activities) {
        // any sort cause was not chosen
    }
}
