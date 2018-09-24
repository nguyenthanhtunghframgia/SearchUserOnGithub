package com.example.tung.gituser.network;

import com.example.tung.gituser.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("google/repos")
    Call<List<User>> fetchAllUser();

    @GET("{user}/repos")
    Call<List<User>> searchUser(@Path("user") String user);
}
