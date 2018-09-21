package com.example.tung.gituser.ui.main;

import android.annotation.SuppressLint;

import com.example.tung.gituser.data.model.User;
import com.example.tung.gituser.data.repository.UserRepository;
import com.example.tung.gituser.data.source.UserDataSource;

import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

public class MainPresenter implements MainContract.Presenter {
    private UserRepository mUserRepository;
    private MainContract.View mView;

    @SuppressLint("RestrictedApi")
    public MainPresenter(UserRepository userRepository, MainContract.View view) {
        mUserRepository = checkNotNull(userRepository);
        mView = checkNotNull(view);
        mView.setPresenter(this);
    }

    @Override
    public void getUsers() {
        mUserRepository.getUsersRemote(new UserDataSource.RemoteDataSource.OnFetchDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                mView.displayUsers(users);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showEmptyUsers();
            }

            @Override
            public void onError(Exception e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void searchUsers(String s) {
        mUserRepository.searchUserRemote(s, new UserDataSource.RemoteDataSource.OnFetchDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                mView.displayUsers(users);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showEmptyUsers();
            }

            @Override
            public void onError(Exception e) {
                mView.showError(e);
            }
        });
    }

    @Override
    public void start() {

    }
}
