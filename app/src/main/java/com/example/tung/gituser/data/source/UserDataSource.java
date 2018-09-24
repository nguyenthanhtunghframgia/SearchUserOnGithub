package com.example.tung.gituser.data.source;

import com.example.tung.gituser.data.model.User;

import java.util.List;

public interface UserDataSource {
    interface LocalDataSource {
        // TODO: 9/21/2018
    }

    interface RemoteDataSource {
        void getUsersRemote(OnFetchDataListener onFetchDataListener);

        void searchUserRemote(String user, OnFetchDataListener onFetchDataListener);

        interface OnFetchDataListener {
            void onSuccess(List<User> users);

            void onDataNotAvailable();

            void onError(Exception e);
        }
    }
}
