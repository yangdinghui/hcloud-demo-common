package hcloud.demo.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 添加脱敏工具类DesensitizedUtil
 * @author tomato
 */
public class DesensitizedUtil {

    public DesensitizedUtil() {
    }

    public static String phoneDesensitized(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } else {
            str = "";
        }
        return str;
    }

    public static String emailDesensitized(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = str.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
        } else {
            str = "";
        }
        return str;
    }

    public static String nameDesensitized(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = str.replaceAll("([\\d\\D]{1})(.*)", "$1**");
        } else {
            str = "";
        }
        return str;
    }
}
