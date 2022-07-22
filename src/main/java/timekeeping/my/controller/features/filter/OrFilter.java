package timekeeping.my.controller.features.filter;

import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.model.entity.Activity;

import java.util.List;

/**
 * Filter by two params
 * Unite by two filters
 * */
public class OrFilter implements Filter {

    private final Filter filter1;
    private final Filter filter2;

    public OrFilter(Filter filter1, Filter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;

    }

    @Override
    public List<Activity> filter(List<Activity> activities) {
        List<Activity> resultFirstFilter = filter1.filter(activities);
        List<Activity> resultSecondFilter = filter2.filter(activities);
        resultSecondFilter.addAll(resultFirstFilter);

        return resultSecondFilter;
    }
}
