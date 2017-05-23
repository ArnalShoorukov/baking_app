package com.mosquefinder.arnal.bakingapp.data.remote;

import com.mosquefinder.arnal.bakingapp.data.model.SOAnswersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by arnal on 5/23/17.
 */

public interface SOService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<SOAnswersResponse> getAnswers();

    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
