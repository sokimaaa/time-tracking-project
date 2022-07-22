package timekeeping.my.controller.features.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.util.Sort;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class SorterChain implements Chain {
    private static final Logger log = LoggerFactory.getLogger(SorterChain.class);
    private Chain nextInChain;
    @Override
    public void doChain(Chain chain) {
        this.nextInChain = chain;
    }

    @Override
    public void calculate(List list, HttpServletRequest request, final String[] command) {
        log.trace("sort chain...");
        if(Objects.nonNull(command[2])) {
            Sort.sort(list, command[2]);
        }
        nextInChain.calculate(list, request, command);
    }
}
