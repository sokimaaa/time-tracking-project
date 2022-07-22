package timekeeping.my.controller.filter;

import javax.servlet.*;

public abstract class AbstractFilter implements Filter {

    protected FilterConfig filterConfig;
    protected ServletContext context;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        context = filterConfig.getServletContext();
    }

    @Override
    public void destroy() {

    }
}
