package com.acc.server.utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * Created by demius on 25.06.2015.
 */
public class StringUtils {

    public static String lpad(String str, int len) {
        int l = str.length();
        if (l < len) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len - l; i++) sb.append(' ');
            return sb.toString() + str;
        } else return str;
    }

    public static String rpad(String str, int len) {
        int l = str.length();
        if (l < len) {
            StringBuilder sb = new StringBuilder(str);
            for (int i = 0; i < len - l; i++) sb.append(' ');
            return sb.toString();
        } else return str;
    }

    public static <T> String join(List<T> list, String delimiter, Function<T,String> translator) {
        return list.stream().map(translator).collect(Collectors.joining(delimiter));
    }

    public static <T> String join(T list[], String delimiter, Function<T,String> translator) {
        return Arrays.stream(list).map(translator).collect(Collectors.joining(delimiter));
    }

    public static String replaceAll(Matcher m, Function<String, String> replacer) {
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, replacer.apply(m.group()));
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
