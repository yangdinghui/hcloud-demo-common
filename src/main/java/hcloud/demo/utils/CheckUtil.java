package hcloud.demo.utils;

import hcloud.demo.exception.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-11-27
 */
public final class CheckUtil {
    public CheckUtil() {
    }

    public static final boolean isDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static boolean isNumber(Object obj) {
        try {
            String str = String.valueOf(obj);
            String reg = "^[0-9]+(.[0-9]+)?$";
            return str.matches(reg);
        } catch (Exception var3) {
            throw new BusinessException(201, "判断传入字符串是否为数值异常");
        }
    }

    public static final boolean valid(String src) {
        return src != null && !"".equals(src.trim());
    }

    public static final boolean valid(String... src) {
        String[] var1 = src;
        int var2 = src.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            String s = var1[var3];
            if (!valid(s)) {
                return false;
            }
        }

        return true;
    }

    public static final boolean valid(Object obj) {
        return null != obj;
    }

    public static final boolean valid(Object... objs) {
        return objs != null && objs.length != 0;
    }

    public static final boolean valid(Collection col) {
        return col != null && !col.isEmpty();
    }

    public static final boolean valid(Collection... cols) {
        Collection[] var1 = cols;
        int var2 = cols.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Collection c = var1[var3];
            if (!valid(c)) {
                return false;
            }
        }

        return true;
    }

    public static final boolean valid(Map map) {
        return map != null && !map.isEmpty();
    }

    public static final boolean valid(Map... maps) {
        Map[] var1 = maps;
        int var2 = maps.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Map m = var1[var3];
            if (!valid(m)) {
                return false;
            }
        }

        return true;
    }
}

