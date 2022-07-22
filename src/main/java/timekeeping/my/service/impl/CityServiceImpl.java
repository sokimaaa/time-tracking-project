package timekeeping.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.dao.mysql.CityMySQLDao;
import timekeeping.my.model.entity.City;
import timekeeping.my.service.CityService;

public class CityServiceImpl extends AbstractService<City> implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityService.class);
    @Override
    public void fillItem(City city) {

    }

    @Override
    protected Dao<City> getDAO(ConnectionWrapper connection) {
        log.trace("Getting city dao. Connection ==> " + connection);
        return daoFactory.getDAO(CityMySQLDao.class, connection);
    }

    @Override
    protected void processEntity(City city) {

    }
}
