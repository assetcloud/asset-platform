package com.asset.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 条件构造器
 */
public class Condition {
    public Condition() {
    }

    public static <T> IPage<T> getPage(Query query) {
        Page page = new Page((long) toInt(query.getPage(), 1), (long) toInt(query.getSize(), 10));
        page.setAsc(toStrArray(query.getAscs()));
        page.setDesc(toStrArray(query.getDescs()));
        return page;
    }

    public static <T> QueryWrapper<T> getQueryWrapper(T entity) {
        return new QueryWrapper(entity);
    }

    public static <T> QueryWrapper<T> getQueryWrapper(Map<String, Object> query, Class<T> clazz) {
        query.remove("page");
        query.remove("size");
        QueryWrapper<T> qw = new QueryWrapper();
//        qw.setEntity(BeanUtil.newInstance(clazz));
        qw.setEntity(BeanUtils.instantiateClass(clazz));
        if (isNotEmpty(query)) {
            query.forEach((k, v) -> {
                if (isNotEmpty(v)) {
                    qw.like(humpToUnderline(k), v);
                }

            });
        }

        return qw;
    }

    public static String[] toStrArray(String str) {
        return toStrArray1(",", str);
    }

    public static String[] toStrArray1(String split, String str) {
        return isBlank(str) ? new String[0] : str.split(split);
    }

    public static boolean isBlank(@Nullable final CharSequence cs) {
        return isBlank1(cs);
    }

    public static boolean isBlank1(final CharSequence cs) {
        return !hasText(cs);
    }

    public static boolean hasText(@Nullable CharSequence str) {
        return str != null && str.length() > 0 && containsText(str);
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();

        for(int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static int toInt(final Object value, final int defaultValue) {
        return toInt1(String.valueOf(value), defaultValue);
    }
    public static int toInt1(@Nullable final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.valueOf(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }
    public static String humpToUnderline(String para) {
        para = lowerFirst(para);
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;

        for(int i = 0; i < para.length(); ++i) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                ++temp;
            }
        }

        return sb.toString().toLowerCase();
    }

    public static String lowerFirst(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] = (char)(arr[0] + 32);
            return new String(arr);
        } else {
            return str;
        }
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(@Nullable Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof Optional) {
            return !((Optional)obj).isPresent();
        } else if (obj instanceof CharSequence) {
            return ((CharSequence)obj).length() == 0;
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) == 0;
        } else if (obj instanceof Collection) {
            return ((Collection)obj).isEmpty();
        } else {
            return obj instanceof Map ? ((Map)obj).isEmpty() : false;
        }
    }
}
