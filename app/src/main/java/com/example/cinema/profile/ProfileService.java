package com.example.cinema.profile;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProfileService {
    @GET ("profile")
    Call<ProfileModel> getProfile(@Query("token") String token);
}
