package com.topjava.webapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.topjava.webapp.model.sections.Sections;

import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
            .registerTypeAdapter(Sections.class, new JsonSectionAdapter())
            .create();

    public static <T> T read(Reader reader, Class<T> clazz) {return GSON.fromJson(reader, clazz);}
    public static <T> void write(T object, Writer writer) {GSON.toJson(object, writer);}

    public static <T> T read(String value, Class<T> clazz) {
        return GSON.fromJson(value, clazz);
    }
    public static <T> String write(T object) {
        return GSON.toJson(object);
    }
    public static <T> String write(T object, Class<T> clazz) {
        return GSON.toJson(object, clazz);
    }
}
