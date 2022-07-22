package timekeeping.my.controller.features.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Category;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CategoryFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(CategoryFilter.class);
    private final Category category;

    public CategoryFilter(Category category) {
        this.category = category;
    }

    @Override
    public List<Activity> filter(List<Activity> activities) {
        log.info("filter category ==> " + category);
        if(Objects.isNull(activities))
            return Collections.emptyList();
        return activities.stream()
                .filter(activity -> activity.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}
