package com.example.tung.gituser.ui.main;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tung.gituser.R;
import com.example.tung.gituser.data.model.User;
import com.example.tung.gituser.data.repository.UserRepository;
import com.example.tung.gituser.data.source.local.UserLocalDataSource;
import com.example.tung.gituser.data.source.remote.UserRemoteDataSource;
import com.example.tung.gituser.util.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View, SearchView.OnQueryTextListener {
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {
        mPresenter = new MainPresenter(UserRepository
                .getInstance(UserRemoteDataSource.getInstance(),
                        UserLocalDataSource.getInstance()),
                this);
        mPresenter.getUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void displayUsers(List<User> users) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter(this, users);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void showEmptyUsers() {
        Toast.makeText(this, Constants.DATA_NOT_AVAILABLE, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Exception e) {
        Toast.makeText(this, String.valueOf(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        searchData(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    private void searchData(String s) {
        mPresenter = new MainPresenter(UserRepository
                .getInstance(UserRemoteDataSource.getInstance(),
                        UserLocalDataSource.getInstance()),
                this);
        mPresenter.searchUsers(s);
    }
}
