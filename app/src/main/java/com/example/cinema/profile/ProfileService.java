package com.example.cinema.profile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfileService {
    @POST("profile")
    Call<ProfileModel> getProfile(@Body TokenModel tokenModel);
}
