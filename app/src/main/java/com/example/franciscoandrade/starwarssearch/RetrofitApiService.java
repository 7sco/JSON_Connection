package com.example.franciscoandrade.starwarssearch;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by franciscoandrade on 12/10/17.
 */

public interface RetrofitApiService {
    @GET("people")
    Call<PeopleResponse> getPeople();

}
