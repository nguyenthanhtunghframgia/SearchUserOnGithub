package com.example.tung.gituser.data.repository;

import android.annotation.SuppressLint;

import com.example.tung.gituser.data.source.UserDataSource;
import com.example.tung.gituser.data.source.local.UserLocalDataSource;
import com.example.tung.gituser.data.source.remote.UserRemoteDataSource;

import static android.support.v4.util.Preconditions.checkNotNull;

public class UserRepository implements UserDataSource.RemoteDataSource,
        UserDataSource.LocalDataSource {
    private static UserRepository sInstance;
    private UserRemoteDataSource mUserRemoteDataSource;
    private UserLocalDataSource mUserLocalDataSource;

    @SuppressLint("RestrictedApi")
    public UserRepository(UserRemoteDataSource userRemoteDataSource,
                          UserLocalDataSource userLocalDataSource) {
        mUserRemoteDataSource = checkNotNull(userRemoteDataSource);
        mUserLocalDataSource = checkNotNull(userLocalDataSource);
    }

    @SuppressLint("RestrictedApi")
    public static UserRepository getInstance(UserRemoteDataSource userRemoteDataSource,
                                             UserLocalDataSource userLocalDataSource) {
        if (sInstance == null) {
            sInstance = new UserRepository(checkNotNull(userRemoteDataSource),
                    checkNotNull(userLocalDataSource));
        }
        return sInstance;
    }

    @Override
    public void getUsersRemote(OnFetchDataListener onFetchDataListener) {
        mUserRemoteDataSource.getUsersRemote(onFetchDataListener);
    }

    @Override
    public void searchUserRemote(String user, OnFetchDataListener onFetchDataListener) {
        mUserRemoteDataSource.searchUserRemote(user, onFetchDataListener);
    }
}
