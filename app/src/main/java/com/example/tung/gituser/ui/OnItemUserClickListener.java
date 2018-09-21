package com.example.tung.gituser.ui;

import com.example.tung.gituser.data.model.User;

import java.util.List;

public interface OnItemUserClickListener {
    void onItemClick(List<User> users, int position);
}
