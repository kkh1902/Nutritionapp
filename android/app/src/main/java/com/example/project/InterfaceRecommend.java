//서버(Record) 상호작용 인터페이스
package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceRecommend {

    @GET("food/recommend")
    Call<ArrayList<ModelRecommend>> getRecommendFood();

//    @FormUrlEncoded
//    @POST("{user_id}")
//    Call<ArrayList<ModelRecommend>> postFoodData(@FieldMap HashMap<String, Object> param, @Path("user_id") String cat);
//
//    @FormUrlEncoded
//    @PATCH("{user_id}")
//    Call<ModelRecommend> patchFoodData(@FieldMap HashMap<String, Object> param , @Path("user_id") String id);
//
//    @DELETE("{user_id}")
//    Call<Void> deleteFoodData(@Path("user_id") String id);
}
