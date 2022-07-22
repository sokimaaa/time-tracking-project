package timekeeping.my.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.ConstantPath;
import timekeeping.my.model.entity.Role;
import timekeeping.my.util.Parser;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The security filter manages access to different part of web-site
 * */
@WebFilter(filterName="security")
public class SecurityFilter extends AbstractFilter {
    private static final Logger log = LoggerFactory.getLogger(SecurityFilter.class);
    /**
     * map which has Role and did not access url for this role
     * */
    private static final Map<Role, List<String>> URL = new HashMap<>() {
        {
            put(Role.ADMIN, ConstantPath.ADMIN_URL);
            put(Role.USER, ConstantPath.USER_URL);
            put(Role.ANONYMOUS, ConstantPath.ANONYMOUS_URL);
        }
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String path = request.getServletPath();
        log.info("Current path ==> " + path);

        Role role = (Role) session.getAttribute("role");
        if(Objects.isNull(role)) {
            role = Role.ANONYMOUS;
            session.setAttribute("role", role);
        }
        log.info("Current role ==> " + role.getRole());

        List<String> unavailablePaths = URL.get(role);
        path = Parser.parseCommand(path);

        if(unavailablePaths.contains(path)) {
            response.sendRedirect(ConstantPath.ACCESS_DENY);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
