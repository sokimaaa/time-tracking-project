package timekeeping.my.controller.features.filter;

import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusFilter implements Filter {

    private static final Map<String, Status> PARAMS = new HashMap<>() {
        {
            put("private", Status.PRIVATE);
            put("public", Status.PUBLIC);
        }
    };

    private final Status param;

    public StatusFilter(String param) {
        this.param = PARAMS.get(param);

    }

    @Override
    public List<Activity> filter(List<Activity> activities) {
        return activities.stream()
                .filter(activity -> activity.getStatus().equals(param))
                .collect(Collectors.toList());
    }
}
