package com.luyichao.staticserver.util;

import io.netty.util.internal.StringUtil;

public class TypeConverter {
    public static Integer stringToInteger(String s) {
        if (StringUtil.isNullOrEmpty(s)) {
            return null;
        }
        try {
            Integer result = Integer.valueOf(s);
            return result;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
