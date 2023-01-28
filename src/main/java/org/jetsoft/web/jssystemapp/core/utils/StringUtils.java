package org.jetsoft.web.jssystemapp.core.utils;

import org.springframework.lang.NonNull;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class StringUtils {

    public static String EMPTY_STRING = "";

    private StringUtils() {}

    public static String nullToEmpty(String target) {

        return target == null ? "" : target;
    }

    public static boolean startsWithBlankPositive(@NonNull String source, String prefix) {

        if (isBlank(prefix)) {

            return true;
        }

        return source.startsWith(prefix);
    }
}
