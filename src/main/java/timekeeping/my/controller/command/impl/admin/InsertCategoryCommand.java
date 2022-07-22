package timekeeping.my.controller.command.impl.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.exception.DaoException;
import timekeeping.my.service.CategoryService;
import timekeeping.my.service.exception.InsertEntityException;
import timekeeping.my.service.impl.CategoryServiceImpl;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to insert category
 * after execution redirect to the admin page
 * */
public class InsertCategoryCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(InsertCategoryCommand.class);
    private final CategoryService categoryService;

    public InsertCategoryCommand() {
        categoryService = (CategoryService) serviceFactory.<Category>getService(CategoryServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("category");

        Category category = new Category();
        category.setCategory(name);
        try {
            log.trace("Inserting category..");
            categoryService.insert(category);
            log.trace("Insert was successful. Redirect to admin page...");
        } catch (InsertEntityException e) {
            log.info("Fail to insert category.. Category: " + name);
            Form categoriesForm = formBuilder.getAdminCategoriesForm();
            categoriesForm.addError(languageManager.getString("message.error.category.exist"));

            log.trace("Such category already exist. Redirect to admin page with error..");
            request.getSession().setAttribute("form", categoriesForm);
        }

        return "redirect:/admin";
    }

}
