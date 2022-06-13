//서버(Record) 상호작용 인터페이스
package com.example.project;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface InterfaceUser {

    @GET("user/spec/{user_id}")
    Call<ModelUser> getUserData(@Path("user_id") String id);

    @FormUrlEncoded
    @POST("user/spec/{user_id}")
    Call<ModelUser> postUserData(@FieldMap HashMap<String, Object> param, @Path("user_id") String id);

    @FormUrlEncoded
    @PATCH("user/spec/{user_id}")
    Call<ModelUser> patchUserData(@FieldMap HashMap<String, Object> param , @Path("user_id") String id);

    @DELETE("user/spec/{user_id}")
    Call<Void> deleteUserData(@Path("user_id") String id);
}
