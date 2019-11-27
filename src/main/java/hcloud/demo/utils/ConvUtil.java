package hcloud.demo.utils;

import java.math.BigDecimal;
import java.util.*;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-11-27
 */
public class ConvUtil {
    public ConvUtil() {
    }

    public static String toString(Object obj, String defaultVal) {
        return obj != null ? obj.toString() : defaultVal;
    }

    public static String toString(Object obj) {
        return toString(obj, "");
    }

    public static Integer toInt(Object obj, Integer defaultVal) {
        try {
            return obj != null ? Integer.parseInt(toString(obj, "0")) : defaultVal;
        } catch (Exception var3) {
            return defaultVal;
        }
    }

    public static Integer toInt(Object obj) {
        return toInt(obj, 0);
    }

    public static Integer toInteger(Object obj) {
        return toInt(obj, (Integer)null);
    }

    public static Float toFloat(Object obj, float defaultVal) {
        return obj != null ? Float.parseFloat(toString(obj, "0")) : defaultVal;
    }

    public static Float toFloat(Object obj) {
        return toFloat(obj, 0.0F);
    }

    public static Long toLong(Object obj, long defaultVal) {
        return obj != null ? Long.parseLong(toString(obj)) : defaultVal;
    }

    public static Long toLong(Object obj) {
        return toLong(obj, 0L);
    }

    public static Double toDouble(Object obj, Double defaultVal) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception var3) {
            return defaultVal;
        }
    }

    public static double toDouble(Object obj) {
        return toDouble(obj, 0.0D);
    }

    public static List<Map<String, Object>> converterForMapList(List<Object> list) {
        List<Map<String, Object>> result = new ArrayList();
        Iterator var2 = list.iterator();

        while(var2.hasNext()) {
            Object tempObj = var2.next();
            result.add((HashMap)tempObj);
        }

        return result;
    }

    public static BigDecimal toBigDecimal(Object obj) {
        String str = toString(obj);

        try {
            return StringUtil.isEmpty(str) ? BigDecimal.ZERO : new BigDecimal(str);
        } catch (Exception var3) {
            return BigDecimal.ZERO;
        }
    }
}
