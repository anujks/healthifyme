package com.android.healthifyme.service;

import com.android.healthifyme.Utils.NetworkUtils;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by anuj on 15/06/17.
 */

public interface BookingService {

    String BASE_URL="http://108.healthifyme.com/api/v1/booking/";
    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @GET("slots/all")
    Call<JsonElement> getSlots(@Query("username") String username,@Query("api_key") String key,@Query("vc") String vc,@Query("expert_username") String expert,@Query("format") String format);

    class Creator {

        public static BookingService getService() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(NetworkUtils.createClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(BookingService.class);
        }
    }

}
