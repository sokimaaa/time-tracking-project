package timekeeping.my.controller.command.impl;

import timekeeping.my.controller.features.pagination.*;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.service.ServiceFactory;
import timekeeping.my.controller.command.Command;
import timekeeping.my.util.LanguageManager;
import timekeeping.my.view.form.FormBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Abstract command
 * */
public abstract class AbstractCommand implements Command {

    protected final FormBuilder formBuilder;
    protected final LanguageManager languageManager;
    protected final ServiceFactory serviceFactory;

    protected AbstractCommand() {
        this.serviceFactory = ServiceFactory.getInstance();
        this.formBuilder = FormBuilder.getInstance();
        this.languageManager = LanguageManager.getInstance();
    }

    /**
     * @param tList the list
     * @param request the request to set attribute
     * @param command the String[] command has six string-command
     *  if you need to skip chain enter null parameter
     *  0. number page by default 1.
     *  1. filter param
     *  2. sort param
     *  3. attribute name by default list.
     *  4. divider param by default 5.
     * */
    protected  <T extends Entity> void pagination(HttpServletRequest request, List<T> tList, String[] command) {
        Chain filter = new FilterChain();
        Chain sorter = new SorterChain();
        Chain divider = new DividerChain();
        Chain setting = new SettingChain();

        filter.doChain(sorter);
        sorter.doChain(divider);
        divider.doChain(setting);

        filter.calculate(tList, request, command);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
