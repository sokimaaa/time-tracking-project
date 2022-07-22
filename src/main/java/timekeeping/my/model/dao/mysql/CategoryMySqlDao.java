package timekeeping.my.model.dao.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.dao.ConstantJDBC;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.dao.CategoryDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMySqlDao extends AbstractDao<Category> implements CategoryDao {

    private static final Logger log = LoggerFactory.getLogger(CategoryDao.class);

    @Override
    protected void setInsertParams(Category category, PreparedStatement stmt) throws SQLException {
        int k = 0;
        stmt.setString(++k, category.getName());
    }

    @Override
    protected String getInsertFields() {
        return ConstantJDBC.CATEGORY_FIELDS[1];
    }

    @Override
    protected String getInsertParamsFields() {
        return "?";
    }

    @Override
    protected void setUpdateParams(Category category, PreparedStatement stmt) throws SQLException {
        throw new UnsupportedOperationException("CategoryMySqlDao#setUpdateParams");
    }

    @Override
    protected String getTable() {
        return ConstantJDBC.CATEGORY_TABLE_NAME;
    }

    @Override
    protected Category getEntity(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt(ConstantJDBC.CATEGORY_ID));
        category.setCategory(rs.getString(ConstantJDBC.CATEGORY_NAME));
        log.trace("Category was obtained ==> " + category);
        return category;
    }

    @Override
    protected String getUpdateParams() {
        throw new UnsupportedOperationException("CategoryMySqlDao#getUpdateParams");
    }
}
