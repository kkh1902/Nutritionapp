//공공데이터 받아오는 인터페이스
package com.example.project;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    @GET("getFoodNtrItdntList1?serviceKey=qI4qMLNr02e3nPVuyyOkvSevRADw624d%2F3%2FLswq4pBy6%2BuN%2Fl%2Fg4eWIhffSCZptd3ww3IHp1G3HJPbDMRVl%2B7w%3D%3D")
    Call<Model> getAllData(
            @Query("desc_kor") String desc_kor,
            @Query("pageNo") String pageNo,
            @Query("numOfRows") String numOfRows,
            @Query("type") String type
    );


}
