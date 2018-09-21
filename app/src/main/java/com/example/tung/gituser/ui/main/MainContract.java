package com.example.tung.gituser.ui.main;

import com.example.tung.gituser.BasePresenter;
import com.example.tung.gituser.BaseView;
import com.example.tung.gituser.data.model.User;

import java.util.List;

public interface MainContract {
    interface Presenter extends BasePresenter {
        void getUsers();

        void searchUsers(String s);
    }

    interface View extends BaseView<Presenter> {
        void displayUsers(List<User> users);

        void showEmptyUsers();

        void showError(Exception e);
    }
}
