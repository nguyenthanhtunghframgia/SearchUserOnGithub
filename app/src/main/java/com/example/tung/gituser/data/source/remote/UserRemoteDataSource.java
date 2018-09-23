package com.example.tung.gituser.data.source.remote;

import com.example.tung.gituser.data.source.UserDataSource;

public class UserRemoteDataSource implements UserDataSource.RemoteDataSource {
    private static UserRemoteDataSource sInstance;

    public static UserRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new UserRemoteDataSource();
        }
        return sInstance;
    }

    public UserRemoteDataSource() {
    }

    @Override
    public void getUsersRemote(OnFetchDataListener onFetchDataListener) {
        new GetUserFromApi(onFetchDataListener).getListUsers();
    }

    @Override
    public void searchUserRemote(String user, OnFetchDataListener onFetchDataListener) {
        new SearchUserFromApi(onFetchDataListener).searchUsers(user);
    }
}
