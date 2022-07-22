package timekeeping.my.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.util.LanguageManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(ApplicationListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.trace("====================  Application is started  =========================");
        initDB();
        initCC();
        log.trace("====================  Application is successfully initialized  =========================");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.trace("====================  Application is finished  =========================");
    }

    /**
     * init database
     * */
    private void initDB() {
        try {
            Class.forName("timekeeping.my.util.ConnectionPool");
            log.trace("Data source is obtained successfully.. Application continue work..");
        } catch (ClassNotFoundException e) {
            log.error("Fail to obtain data source. Application terminated.");
            throw new RuntimeException(e);
        }
    }

    /**
     * init command container
     * */
    private void initCC() {
        try {
            Class.forName("timekeeping.my.controller.command.CommandContainer");
            log.trace("Command container is obtained successfully.. Application continue work..");
        } catch (ClassNotFoundException e) {
            log.error("Fail to obtain command container. Application terminated.");
            throw new RuntimeException(e);
        }
    }

}
