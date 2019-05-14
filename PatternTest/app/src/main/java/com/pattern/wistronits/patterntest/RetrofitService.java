package com.pattern.wistronits.patterntest;

import com.pattern.wistronits.patterntest.bean.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("/api/data/Android/10/1")
    Call<ResponseData> loadData();

}
