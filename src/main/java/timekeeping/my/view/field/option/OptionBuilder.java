package timekeeping.my.view.field.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.model.entity.Entity;
import timekeeping.my.service.CategoryService;
import timekeeping.my.service.ServiceFactory;
import timekeeping.my.service.exception.EntityNotFoundException;
import timekeeping.my.service.impl.CategoryServiceImpl;
import timekeeping.my.model.entity.Category;
import timekeeping.my.model.entity.City;
import timekeeping.my.model.entity.Sex;
import timekeeping.my.service.CityService;
import timekeeping.my.service.impl.CityServiceImpl;
import timekeeping.my.util.LanguageManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The option builder class provided tools to create option
 * */
public final class OptionBuilder {

    private static final Logger log = LoggerFactory.getLogger(OptionBuilder.class);
    private static OptionBuilder INSTANCE;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final LanguageManager languageManager;

    private OptionBuilder() {
        this.cityService = (CityService) ServiceFactory.getInstance().<City>getService(CityServiceImpl.class);
        this.categoryService = (CategoryService) ServiceFactory.getInstance().<Category>getService(CategoryServiceImpl.class);
        this.languageManager = LanguageManager.getInstance();
    }

    public static synchronized OptionBuilder getInstance() {
        if(Objects.isNull(INSTANCE))
            INSTANCE = new OptionBuilder();
        return INSTANCE;
    }

    public List<Option> getEmptyOptions() {
        return Collections.emptyList();
    }

    public List<Option> getSexesOptions() {
        Sex[] sexes = Sex.values();
        return Arrays.stream(sexes)
                .map(sex -> new Option(
                        languageManager.getString(sex.getName()),
                        sex.getName()
                ))
                .collect(Collectors.toList());
    }

    public List<Option> getCitiesOptions() {
        try {
            return cityService.getAll()
                    .stream()
                    .map(city -> new Option(
                            languageManager.getString(city.getName()),
                            city.getId().toString()
                    ))
                    .collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain cities.");
            throw new RuntimeException();
        }
    }

    public List<Option> getCategoriesOptions() {
        try {
            return categoryService.getAll()
                    .stream()
                    .map(category -> new Option(
                            category.getName(),
                            category.getId().toString()
                    ))
                    .collect(Collectors.toList());
        } catch (EntityNotFoundException e) {
            log.error("Fail to obtain categories.");
            throw new RuntimeException();
        }
    }

}
