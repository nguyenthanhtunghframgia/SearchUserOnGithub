package com.example.tung.gituser.network;

import com.example.tung.gituser.data.model.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("google/repos")
    Single<List<User>> fetchAllUser();

    @GET("{user}/repos")
    Single<List<User>> searchUser(@Path("user") String user);
}
