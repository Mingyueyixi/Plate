package com.lu.plate.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;

public class GsonUtil {
    private final static class GsonHolder {
        private static HashMap<Integer, Gson> sGsonMap = new HashMap<>();
        /**
         * 默认的gson
         */
        private static int KEY_DEFAULT = 0;
        /**
         * 友好格式的打印
         */
        private static int KEY_PRETTY = 1;
        /**
         * 序列化null值
         */
        private static int KEY_NULLS = 2;
        /**
         * 格式化的json，带上空值
         */
        private static int KEY_PRETTY_NULLS = 3;

        private static Gson getGson(int key) {
            Gson gson = sGsonMap.get(key);
            if (gson == null) {
                if (key == KEY_PRETTY) {
                    gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
                } else if (key == KEY_NULLS) {
                    gson = new GsonBuilder().serializeNulls().create();
                } else if (key == KEY_PRETTY_NULLS) {
                    gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
                } else {
                    gson = new Gson();
                }
                sGsonMap.put(key, gson);
            }
            return gson;
        }
    }

    private static final Gson sGson = getGson();

    public static Gson getGson() {
        return GsonHolder.getGson(GsonHolder.KEY_DEFAULT);
    }

    public static Gson getPrettyGson() {
        return GsonHolder.getGson(GsonHolder.KEY_PRETTY);
    }

    public static Gson getNullsGson() {
        return GsonHolder.getGson(GsonHolder.KEY_NULLS);
    }

    public static Gson getPrettyNullsGson() {
        return GsonHolder.getGson(GsonHolder.KEY_PRETTY_NULLS);
    }

    public static JsonElement toJsonTree(Object src) {
        return sGson.toJsonTree(src);
    }

    public static JsonElement toJsonTree(Object src, Type typeOfSrc) {
        return sGson.toJsonTree(src, typeOfSrc);
    }

    public static String toJson(Object src) {
        return sGson.toJson(src);
    }

    public static String toJson(Object src, Type typeOfSrc) {
        return sGson.toJson(src, typeOfSrc);
    }

    public static void toJson(Object src, Appendable writer) throws JsonIOException {
        sGson.toJson(src, writer);
    }

    public static void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
        sGson.toJson(src, typeOfSrc, writer);
    }

    public static void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
        sGson.toJson(src, typeOfSrc, writer);
    }

    public static String toJson(JsonElement jsonElement) {
        return sGson.toJson(jsonElement);
    }

    public static void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        sGson.toJson(jsonElement, writer);
    }

    public static void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
        sGson.toJson(jsonElement, writer);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(String json, TypeToken<T> typeOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(Reader json, Class<T> classOfT) throws JsonSyntaxException, JsonIOException {
        return sGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(Reader json, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(Reader json, TypeToken<T> typeOfT) throws JsonIOException, JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return sGson.fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(JsonReader reader, TypeToken<T> typeOfT) throws JsonIOException, JsonSyntaxException {
        return sGson.fromJson(reader, typeOfT);
    }

    public static <T> T fromJson(JsonElement json, Class<T> classOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(JsonElement json, Type typeOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(JsonElement json, TypeToken<T> typeOfT) throws JsonSyntaxException {
        return sGson.fromJson(json, typeOfT);
    }
}