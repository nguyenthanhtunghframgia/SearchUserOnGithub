package com.example.tung.gituser.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.tung.gituser.R;
import com.example.tung.gituser.data.model.User;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private Context mContext;
    private List<User> mUsers;

    public MainAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new MainHolder(view, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.bindData(mUsers.get(i));
    }

    @Override
    public int getItemCount() {
        return mUsers.isEmpty() ? 0 : mUsers.size();
    }

    public static class MainHolder extends RecyclerView.ViewHolder {
        private Context mContext;
        private TextView mTextId;
        private TextView mTextName;
        private ImageView mImageUser;

        public MainHolder(@NonNull View itemView, Context context) {
            super(itemView);
            mContext = context;
            mTextId = itemView.findViewById(R.id.user_item_id);
            mTextName = itemView.findViewById(R.id.user_item_name);
            mImageUser = itemView.findViewById(R.id.user_item_image);
        }

        private void bindData(User user) {
            mTextId.setText(String.valueOf(user.getId()));
            mTextName.setText(user.getName());
            Glide.with(mContext)
                    .load(user.getUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .centerInside())
                    .into(mImageUser);
        }
    }
}
