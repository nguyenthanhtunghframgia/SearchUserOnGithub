package com.example.tung.gituser.data.source.remote;

import com.example.tung.gituser.data.model.User;
import com.example.tung.gituser.data.source.UserDataSource;
import com.example.tung.gituser.network.ApiClient;
import com.example.tung.gituser.network.ApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GetUserFromApi {
    private UserDataSource.RemoteDataSource.OnFetchDataListener mOnFetchDataListener;

    public GetUserFromApi(UserDataSource.RemoteDataSource.OnFetchDataListener onFetchDataListener) {
        mOnFetchDataListener = onFetchDataListener;
    }

    protected void getListUsers() {
        Retrofit retrofit = ApiClient.getClient();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.fetchAllUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        mOnFetchDataListener.onSuccess(users);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mOnFetchDataListener.onError((Exception) e);
                    }
                });
    }
}
