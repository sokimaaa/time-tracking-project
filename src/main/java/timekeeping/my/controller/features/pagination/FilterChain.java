package timekeeping.my.controller.features.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.controller.features.filter.CategoryFilter;
import timekeeping.my.model.entity.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class FilterChain implements Chain {
    private static final Logger log = LoggerFactory.getLogger(FilterChain.class);
    private Chain nextInChain;
    @Override
    public void doChain(Chain chain) {
        this.nextInChain = chain;
    }

    @Override
    public void calculate(List list, HttpServletRequest request, final String[] command) {
        log.trace("filter chain... ");
        if(Objects.nonNull(command[1])) {
            Category category = new Category(Integer.parseInt(command[1]));
            Filter filter = new CategoryFilter(category);
            list = filter.filter(list);
        }
        nextInChain.calculate(list, request, command);
    }

}
