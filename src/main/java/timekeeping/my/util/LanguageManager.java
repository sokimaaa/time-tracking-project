package timekeeping.my.util;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The Language Manager class manages resource bundle
 * */
public class LanguageManager {

    private static final String RESOURCE_NAME;
    private static LanguageManager instance;

    static {
        RESOURCE_NAME = PropertiesUtil.getProperty("resource.bundle.path");
    }

    public static synchronized LanguageManager getInstance() {
        if(Objects.isNull(instance))
            instance = new LanguageManager();
        return instance;
    }

    private ResourceBundle bundle;
    private Locale locale;

    /**
     * sets default language
     * */
    private LanguageManager() {
        changeLanguage(Language.EN);
    }

    /**
     * Change resource bundle according to Language
     * @param lang the Language enum
     * */
    public void changeLanguage(Language lang) {
        this.locale = new Locale(lang.name().toLowerCase());
        bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * gets localized property from active resource bundle
     * @param key the key
     * @return string the localized property value
     * */
    public String getString(String key) {
        return bundle.getString(key);
    }

    /**
     * gets current locale
     * @return active locale
     * */
    public Locale getLocale() {
        return locale;
    }
}
