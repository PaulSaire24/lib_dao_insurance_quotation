package com.bbva.pisd.lib.r601.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonHelper {

    private static final String DATE = "yyyy-MM-dd";
    private static final JsonHelper INSTANCE = new JsonHelper();


    private final Gson gson;

    private JsonHelper() {
        gson = new GsonBuilder()
                .setDateFormat(DATE)
                .create();
    }

    public static JsonHelper getInstance() { return INSTANCE; }

    public String toJsonString(Object o) { return this.gson.toJson(o); }

}
