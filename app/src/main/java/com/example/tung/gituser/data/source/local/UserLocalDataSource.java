package com.example.tung.gituser.data.source.local;

import com.example.tung.gituser.data.source.UserDataSource;

public class UserLocalDataSource implements UserDataSource.LocalDataSource {
    private static UserLocalDataSource sInstance;

    public UserLocalDataSource() {
    }

    public static UserLocalDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new UserLocalDataSource();
        }
        return sInstance;
    }

    // TODO: 9/21/2018
}
