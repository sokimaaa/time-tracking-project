package timekeeping.my.controller.features.pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Chain {

    void doChain(Chain chain);

    void calculate(List list, HttpServletRequest request, final String[] command);

}
