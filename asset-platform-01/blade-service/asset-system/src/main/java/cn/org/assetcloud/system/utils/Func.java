package cn.org.assetcloud.system.utils;

import org.springblade.core.tool.utils.StringUtil;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 变量校验工具类
 */
public class Func {

    public static boolean isNull(@Nullable Object obj) {
        return Objects.isNull(obj);
    }

    public static boolean hasEmpty(Object... os) {
        Object[] var1 = os;
        int var2 = os.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object o = var1[var3];
            if (ObjectUtils.isEmpty(o)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !ObjectUtils.isEmpty(obj);
    }

    public static List<Long> toIntList(String str) {
        return Arrays.asList(toIntArray(str));
    }

    public static Long[] toIntArray(String str) {
        return toIntArray(",", str);
    }

    public static Long[] toIntArray(String split, String str) {
        if (StringUtil.isEmpty(str)) {
            return new Long[0];
        } else {
            String[] arr = str.split(split);
            Long[] ints = new Long[arr.length];

            for(int i = 0; i < arr.length; ++i) {
                Long v = toLong(arr[i], 0);
                ints[i] = v;
            }

            return ints;
        }
    }

    public static String[] toStrArray(String str) {
        return toStrArray(",", str);
    }

    public static String[] toStrArray(String split, String str) {
        return !StringUtils.hasText(str) ? new String[0] : str.split(split);
    }

    public static List<String> toStrList(String str) {
        return Arrays.asList(toStrArray(str));
    }

    public static List<String> toStrList(String split, String str) {
        return Arrays.asList(toStrArray(split, str));
    }

    public static List<Long> toLongList(String str) {
        return Collections.singletonList(toLong(str));
    }

    public static List<Long> toLongList(String split, String str) {
        return Arrays.asList(toLongArray(split, str));
    }

    public static Long[] toLongArray(String split, String str) {
        if (StringUtil.isEmpty(str)) {
            return new Long[0];
        } else {
            String[] arr = str.split(split);
            Long[] ints = new Long[arr.length];

            for(int i = 0; i < arr.length; ++i) {
                Long v = toLong(arr[i], 0);
                ints[i] = v;
            }

            return ints;
        }
    }

    public static long toLong(final Object value) {
        return toLong(String.valueOf(value));
    }

    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    public static long toLong(final Object value, final long defaultValue) {
        return toLong(String.valueOf(value), defaultValue);
    }

    public static long toLong(@Nullable final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Long.valueOf(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }
}
