package com.whut.teaching.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
    private static GsonBuilder gb;

    static {
        gb = new GsonBuilder();
    }

    public static Gson getDateFormatGson() {
        gb.setDateFormat("yyyy-MM-dd");
        return gb.create();
    }

    public static String toJson(Object o) {
        return gb.create().toJson(o);
    }

    public static <T> T parse(String json, Class<T> tClass) {
        return gb.create().fromJson(json, tClass);
    }
}
