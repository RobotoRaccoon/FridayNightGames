package org.mcau.robotoraccoon.fridaynightgames.utility;

import org.mcau.robotoraccoon.fridaynightgames.Config;

public class LangUtil {

    public static String getPrefix() {
        return Config.getLang().getString("prefix");
    }

    public static String getError() {
        return formatPrefix(Config.getLang().getString("error.prefix"));
    }

    public static String formatPrefixKey(String key) {
        return formatPrefix(Config.getLang().getString(key));
    }
    public static String formatPrefix(String message) {
        return String.format("%s %s", getPrefix(), message);
    }

    public static String formatErrorKey(String key) {
        return formatError(Config.getLang().getString(key));
    }
    public static String formatError(String message) {
        return String.format("%s %s", getError(), message);
    }
}
