package timekeeping.my.controller.features.filter;

import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.model.entity.Activity;

import java.util.List;

/**
 * And Filter
 * Intersection result by first and second filters
 * */
public class AndFilter implements Filter {
    private final Filter filter1;
    private final Filter filter2;

    public AndFilter(Filter filter1, Filter filter2) {
        this.filter1 = filter1;
        this.filter2 = filter2;

    }

    @Override
    public List<Activity> filter(List<Activity> activities) {
        return filter2.filter(filter1.filter(activities));
    }
}
