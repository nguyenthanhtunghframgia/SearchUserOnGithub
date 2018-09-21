package com.example.tung.gituser.data.source.remote;

import com.example.tung.gituser.data.model.User;
import com.example.tung.gituser.data.source.UserDataSource;
import com.example.tung.gituser.network.ApiClient;
import com.example.tung.gituser.network.ApiService;
import com.example.tung.gituser.network.Config;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUserFromApi {
    private UserDataSource.RemoteDataSource.OnFetchDataListener mOnFetchDataListener;

    public GetUserFromApi(UserDataSource.RemoteDataSource.OnFetchDataListener onFetchDataListener) {
        mOnFetchDataListener = onFetchDataListener;
    }

    protected void getListUsers() {
        Retrofit retrofit = ApiClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<User>> call = apiService.fetchAllUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                if (users.isEmpty()) {
                    mOnFetchDataListener.onDataNotAvailable();
                } else {
                    mOnFetchDataListener.onSuccess(users);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mOnFetchDataListener.onError((Exception) t);
            }
        });
    }
}