package com.example.tung.gituser.ui.main;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tung.gituser.R;
import com.example.tung.gituser.data.model.User;
import com.example.tung.gituser.data.repository.UserRepository;
import com.example.tung.gituser.data.source.local.UserLocalDataSource;
import com.example.tung.gituser.data.source.remote.UserRemoteDataSource;
import com.example.tung.gituser.ui.OnItemUserClickListener;
import com.example.tung.gituser.ui.detail.DetailFragment;
import com.example.tung.gituser.util.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        OnItemUserClickListener {
    private static final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";
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
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchData(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void displayUsers(List<User> users) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter mainAdapter = new MainAdapter(this, users);
        mainAdapter.setOnItemUserClickListener(this);
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

    private void searchData(String s) {
        mPresenter = new MainPresenter(UserRepository
                .getInstance(UserRemoteDataSource.getInstance(),
                        UserLocalDataSource.getInstance()),
                this);
        mPresenter.searchUsers(s);
    }

    @Override
    public void onItemClick(User user) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_main, DetailFragment.newInstance(user), DETAIL_FRAGMENT)
                .addToBackStack(DETAIL_FRAGMENT)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_asc:
                Toast.makeText(getApplicationContext(), Constants.ASC, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_des:
                Toast.makeText(getApplicationContext(), Constants.DES, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
