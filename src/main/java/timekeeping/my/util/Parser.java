package timekeeping.my.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timekeeping.my.controller.features.filter.Filter;
import timekeeping.my.controller.features.filter.CategoryFilter;
import timekeeping.my.model.entity.Activity;
import timekeeping.my.model.entity.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The util class Parser provided methods to extract info from uri\path
 * */
public final class Parser {

    private static final Logger log = LoggerFactory.getLogger(Parser.class);

    /**
     * parses url path to command-string
     * */
    public static String parseCommand(String path) {
        log.info("Parser command get started.. Received path ==> " + path);
        Pattern p = Pattern.compile("/.+?/");
        Matcher m = p.matcher(path);
        if(m.find())
            path = path.substring(m.start(), m.end() - 1);

        log.info("Parser command has finished.. Received command ==> " + path);
        return path;
    }

    /**
     * Parse uri '/../page=?;filter=?;sort=?/'
     * @param uri the uri matches example
     * @return String[] the array retrieved params
     * */
    public static String[] parseUri(String uri) {
        log.trace("parse uri get started...");
        String[] uriSplit = uri.split("[/;]");
        String[] result = new String[5];

        log.trace("uri split array ==> " + Arrays.toString(uriSplit));
        for (String command: uriSplit) {
            if(command.startsWith("page=")) {
                result[0] = command.substring("page=".length());
            }
            if(command.startsWith("filter=")) {
                result[1] = command.substring("filter=".length());
            }
            if(command.startsWith("sort=")) {
                result[2] = command.substring("sort=".length());
            }
        }
        if(Objects.isNull(result[0]) || result[0].isEmpty()) {
            result[0] = "1";
        }
        if(Objects.isNull(result[3]) || result[3].isEmpty()) {
            result[3] = "list";
        }
        if(Objects.isNull(result[4]) || result[4].isEmpty()) {
            result[4] = "5";
        }
        log.trace("result array ==> " + Arrays.toString(result));
        return result;
    }

}
