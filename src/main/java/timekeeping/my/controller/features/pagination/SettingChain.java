package timekeeping.my.controller.features.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class SettingChain implements Chain {

    private static final Logger log = LoggerFactory.getLogger(SettingChain.class);
    @Override
    public void doChain(Chain chain) {

    }
    @Override
    public void calculate(List list, HttpServletRequest request, final String[] command) {
        String attributeName = command[3];
        String page = command[0];

        log.trace("setting chain...");
        if(!list.isEmpty())
            request.setAttribute(attributeName, list.get(Integer.parseInt(page) - 1));
    }
}
