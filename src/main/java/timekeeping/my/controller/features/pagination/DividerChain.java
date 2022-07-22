package timekeeping.my.controller.features.pagination;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DividerChain implements Chain {
    private static final Logger log = LoggerFactory.getLogger(DividerChain.class);
    private Chain nextInChain;
    @Override
    public void doChain(Chain chain) {
        this.nextInChain = chain;
    }

    @Override
    public void calculate(List list, HttpServletRequest request, final String[] command) {
        log.trace("divide chain...");
        log.trace("list before divide ==> " + list);
        if(Objects.nonNull(command[4])) {
            list = divideList(list, Integer.parseInt(command[4]));
            log.trace("list after divide ==> " + list);
            log.trace("list size ==> " + list.size());
        }
        request.setAttribute("length", list.size());
        nextInChain.calculate(list, request, command);
    }

    private List divideList(List list, int count) {
        if(Objects.isNull(list))
            return Collections.emptyList();

        List<List> chunks = new ArrayList<>();
        List chunk = new ArrayList();
        int k = 0;
        for (Object obj: list) {
            if(k == count) {
                chunks.add(chunk);
                chunk = new ArrayList();
                k = 0;
            }
            chunk.add(obj);
            k++;
        }

        if(k != 0) {
            chunks.add(chunk);
        }
        return chunks;
    }

}
