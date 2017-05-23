package com.mosquefinder.arnal.bakingapp.data.remote;

/**
 * Created by arnal on 5/23/17.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}

