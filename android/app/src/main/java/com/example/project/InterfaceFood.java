//서버(Record) 상호작용 인터페이스
package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceFood {

    @GET("{food_name}")
    Call<ArrayList<ModelFood>> getFoodData(@Path("food_name") String id);

    @FormUrlEncoded
    @POST("{food_name}")
    Call<ArrayList<ModelFood>> postFoodData(@FieldMap HashMap<String, Object> param, @Path("food_name") String cat);

    @FormUrlEncoded
    @PATCH("{food_name}")
    Call<ModelFood> patchFoodData(@FieldMap HashMap<String, Object> param , @Path("food_name") String id);

    @DELETE("{food_name}")
    Call<Void> deleteFoodData(@Path("food_name") String id);
}
