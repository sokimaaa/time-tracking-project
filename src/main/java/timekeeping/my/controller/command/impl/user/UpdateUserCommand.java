package timekeeping.my.controller.command.impl.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.command.impl.AbstractCommand;
import timekeeping.my.controller.features.validate.ValidateException;
import timekeeping.my.controller.features.validate.Validation;
import timekeeping.my.controller.features.validate.Validator;
import timekeeping.my.model.entity.City;
import timekeeping.my.model.entity.Sex;
import timekeeping.my.model.entity.User;
import timekeeping.my.service.UserService;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.exception.UpdateEntityException;
import timekeeping.my.service.impl.UserServiceImpl;
import timekeeping.my.view.form.Form;

import javax.servlet.http.HttpServletRequest;

/**
 * command to update user
 * after execution redirect to the personal cabinet
 * */
public class UpdateUserCommand extends AbstractCommand {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserCommand.class);
    private final UserService userService;

    public UpdateUserCommand() {
        userService = (UserService) serviceFactory.<User>getService(UserServiceImpl.class);
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        Sex sex = Sex.getSex(request.getParameter("sex"));
        City city = new City(Integer.parseInt(request.getParameter("city")));

        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setCity(city);

        try {
            log.trace("Validating entered fields..");
            Validator.validate(email, Validation.EMAIL);
            Validator.validate(phone, Validation.PHONE);

            log.trace("Updating user....");
            userService.update(user);

            user = userService.get(user.getId());
            request.getSession().setAttribute("user", user);
        } catch (UpdateEntityException | EntityNotFoundException e) {
            log.info("Updating was unsuccessful. Entered email: " + email + "; phone: " + phone
                    + "; sex: " + sex.name() + "; city: " + city.getName());
            Form cabinetForm = formBuilder.getPersonalCabinetForm();
            cabinetForm.addError(languageManager.getString("message.error.non.unique.email.phone"));

            log.trace("Entered unique email or phone. Redirect to cabinet page with error..");
            request.getSession().setAttribute("form", cabinetForm);
        } catch (ValidateException e) {
            log.info("Validating failed. Entered email: " + email + "; phone: " + phone + ".");
            Form cabinetForm = formBuilder.getPersonalCabinetForm();
            cabinetForm.addError(languageManager.getString("message.error.invalid.email.phone"));

            log.trace("Validation was not passed.. Redirect to cabinet page with error..");
            request.getSession().setAttribute("form", cabinetForm);
        }
        return "redirect:/cabinet";
    }

}