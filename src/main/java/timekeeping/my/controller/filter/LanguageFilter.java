package timekeeping.my.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.util.Language;
import timekeeping.my.util.LanguageManager;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Language Filter manages application localization
 * */
@WebFilter(filterName = "language")
public class LanguageFilter extends AbstractFilter {
    private static final Logger log = LoggerFactory.getLogger(LanguageFilter.class);
    private static final String LANGUAGE = "lang";
    private static final LanguageManager langManager;

    static {
        langManager = LanguageManager.getInstance();
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.trace("Language Filter get started...");
        Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(LANGUAGE)).findAny().orElse(null);
        log.info("Cookies was obtained ==> " + cookie);
        log.trace("Language Filter setting language...");
        setLanguage(cookie, request, response);
        log.trace("Language Filter ended to set language...");
        Config.set(request.getSession(), Config.FMT_LOCALE, langManager.getLocale());
        request.getSession().setAttribute(LANGUAGE, getCurrentLocale());
        filterChain.doFilter(request, response);
    }

    /**
     * if cookie is empty (or has invalid lang) set current lang into cookie
     * if cookie is not empty (and lang is valid) set lang
     * */
    private void updateLocale(HttpServletResponse response, String currentLanguage, Cookie cookie) {
        String cookieLanguage = cookie.getValue();
        if (!currentLanguage.equalsIgnoreCase(cookieLanguage)) {
            try {
                cookieLanguage = Language.valueOf(cookieLanguage).name();
                log.trace("cookie language was valid...");
            } catch (IllegalArgumentException e) {
                log.trace("cookie language was invalid...");
                context.log("Language value from cookie is not valid. Setting language to default value");
                updateLocale(cookie, response, Language.EN.name());
                cookieLanguage = getCurrentLocale();
            }
            updateLocale(cookie, response, cookieLanguage);
        }
    }

    /**
     * gets current locale name
     * */
    private String getCurrentLocale() {
        return langManager.getLocale().getLanguage();
    }

    /**
     * changes language and save into a cookies
     * @param response the response
     * @param lang the string value of language selected
     * @return string the language which selected
     * */
    private String updateLocale(Cookie cookie, HttpServletResponse response, String lang) {
        log.trace("Updating locale to " + lang);
        langManager.changeLanguage(Language.valueOf(lang));
        cookie.setValue(lang);
        log.info("Update new cookie ==> name=" + cookie.getName() + "; value=" + cookie.getValue());
        response.addCookie(cookie);
        return lang;
    }

    private Cookie addDefaultLangCookie(HttpServletResponse response) {
        log.trace("Adding default lang cookie...");
        Cookie langCookie = new Cookie(LANGUAGE, Language.EN.name());
        log.info("Default lang is ===> " + Language.EN.name());
        langCookie.setMaxAge(2592000);
        response.addCookie(langCookie);
        return langCookie;
    }

    /**
     * sets localization by user's cookies
     * if exist is no cookies available set default lang
     * if cookies available - extract lang and set as current
     * @param cookie the lang cookie
     * @param request the request
     * @param response the response
     * */
    private void setLanguage(Cookie cookie, HttpServletRequest request, HttpServletResponse response) {
        if(Objects.nonNull(cookie)) {
            String selectedLanguage = request.getParameter(LANGUAGE);
            if(Objects.nonNull(selectedLanguage)) {
                log.info("Selected lang is ==> " + selectedLanguage);
                updateLocale(cookie, response, selectedLanguage);
            } else {
                log.trace("Selected lang is null..");
                String currentLanguage = getCurrentLocale();
                log.info("Language in cookie is ==> " + cookie.getValue());
                updateLocale(response, currentLanguage, cookie);
            }
        } else {
            Cookie newCookie = addDefaultLangCookie(response);
            setLanguage(newCookie, request, response);
        }
    }


}
