package com.example.kittisak.top20.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;

/**
 * Created by kittisak on 9/20/2017 AD.
 */

public interface SampleAPI {

    @GET("wp-content/uploads/csv/20170701-asx200.csv")
    @Streaming
    Call<ResponseBody> downloadFile();
}
