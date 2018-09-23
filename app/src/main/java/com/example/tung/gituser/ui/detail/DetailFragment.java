package com.example.tung.gituser.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tung.gituser.R;
import com.example.tung.gituser.data.model.User;
import com.squareup.picasso.Picasso;

public class DetailFragment extends Fragment {
    private User mUser;
    private static final String ARGUMENT_USER = "USER";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mUser = getArguments().getParcelable(ARGUMENT_USER);
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    public static DetailFragment newInstance(User user) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_USER, user);
        detailFragment.setArguments(args);
        return detailFragment;
    }

    private void initData() {
        TextView textName = getView().findViewById(R.id.text_detail_name);
        TextView textId = getView().findViewById(R.id.text_detail_id);
        ImageView imageUser = getView().findViewById(R.id.image_detail);
        textName.setText(mUser.getName());
        textId.setText(String.valueOf(mUser.getId()));
        Picasso.get()
                .load(mUser.getOwner().getAvatarUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imageUser);
    }
}
