package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.CityDao;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.entity.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMySQLDao extends AbstractDao<City> implements CityDao {

    private static final Logger log = LoggerFactory.getLogger(CityDao.class);

    @Override
    protected void setInsertParams(City city, PreparedStatement stmt) throws SQLException {
        throw new UnsupportedOperationException("CityMySQLDao#setInsertParams");
    }

    @Override
    protected void setUpdateParams(City city, PreparedStatement stmt) throws SQLException {
        throw new UnsupportedOperationException("CityMySQLDao#setUpdateParams");
    }

    @Override
    protected String getInsertFields() {
        throw new UnsupportedOperationException("CityMySQLDao#getInsertFields");
    }

    @Override
    protected String getInsertParamsFields() {
        throw new UnsupportedOperationException("CityMySQLDao#getInsertParamsFields");
    }

    @Override
    protected String getTable() {
        return ConstantJDBC.CITY_TABLE_NAME;
    }

    @Override
    protected City getEntity(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getInt(ConstantJDBC.CITY_ID));
        city.setName(rs.getString(ConstantJDBC.CITY_NAME));

        log.trace("City was obtained ==> " + city);
        return city;
    }

    @Override
    protected String getUpdateParams() {
        throw new UnsupportedOperationException("CityMySQLDao#getUpdateParams");
    }
}
