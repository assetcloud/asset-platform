package com.asset.utils;

import org.apache.commons.lang3.StringUtils;

public class CommonUtils {

    /**
     * 判断字符串数组中是否有字符串a
     * @param array
     * @param a
     * @return
     */
    public static boolean isStringArrayContain(String[] array, String a) {
        for (int i = 0;i<array.length;i++)
        {
            if (!StringUtils.isEmpty(array[i]) && array[i].equals(a))
                return true;
        }
        return false;
    }
}
