package timekeeping.my.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.service.exception.ServiceFactoryException;
import timekeeping.my.service.impl.AbstractService;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * The service factory class
 * */
public class ServiceFactory {

    private static final Logger log = LoggerFactory.getLogger(ServiceFactory.class);
    private static ServiceFactory instance;

    private ServiceFactory() {

    }

    public static synchronized ServiceFactory getInstance() {
        if(Objects.isNull(instance))
            instance = new ServiceFactory();
        return instance;
    }

    /**
     * gets service
     * */
    public <T extends Entity> AbstractService<T> getService(Class<? extends AbstractService> clazz) {
        AbstractService<T> service;
        try {
            service = (AbstractService<T>) clazz.getDeclaredConstructor().newInstance();
            log.info("Service was successfully received!");
        } catch (InvocationTargetException | InstantiationException
                 | IllegalAccessException | NoSuchMethodException e) {
            log.error("Service was not received. Provided wrong meta class ==> " + clazz);
            throw new ServiceFactoryException(e);
        }
        return service;
    }

}
