package timekeeping.my.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.service.CategoryService;
import timekeeping.my.model.connection.ConnectionWrapper;
import timekeeping.my.model.dao.Dao;
import timekeeping.my.model.dao.mysql.CategoryMySqlDao;
import timekeeping.my.model.entity.Category;

public class CategoryServiceImpl extends AbstractService<Category> implements CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Override
    protected Dao<Category> getDAO(ConnectionWrapper connection) {
        log.trace("Getting category dao. Connection ==> " + connection);
        return daoFactory.getDAO(CategoryMySqlDao.class, connection);
    }

    @Override
    protected void processEntity(Category category) {

    }

    @Override
    public void fillItem(Category category) {

    }
}
