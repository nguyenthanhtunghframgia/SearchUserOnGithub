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
import com.example.tung.gituser.ui.OnItemUserClickListener;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {
    private Context mContext;
    private List<User> mUsers;
    private OnItemUserClickListener mOnItemUserClickListener;

    public MainAdapter(Context context, List<User> users) {
        mContext = context;
        mUsers = users;
    }

    public void setOnItemUserClickListener(OnItemUserClickListener onItemUserClickListener) {
        mOnItemUserClickListener = onItemUserClickListener;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);
        return new MainHolder(view, mContext, mUsers, mOnItemUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int i) {
        mainHolder.bindData(mUsers.get(i));
    }

    @Override
    public int getItemCount() {
        return mUsers.isEmpty() ? 0 : mUsers.size();
    }

    public static class MainHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Context mContext;
        private TextView mTextId;
        private TextView mTextName;
        private ImageView mImageUser;
        private List<User> mUsers;
        private OnItemUserClickListener mOnItemUserClickListener;

        public MainHolder(@NonNull View itemView, Context context,
                          List<User> users,
                          OnItemUserClickListener onItemUserClickListener) {
            super(itemView);
            mContext = context;
            mTextId = itemView.findViewById(R.id.user_item_id);
            mTextName = itemView.findViewById(R.id.user_item_name);
            mImageUser = itemView.findViewById(R.id.user_item_image);
            mUsers = users;
            mOnItemUserClickListener = onItemUserClickListener;
        }

        private void bindData(User user) {
            mTextId.setText(String.valueOf(user.getId()));
            mTextName.setText(user.getName());
            Glide.with(mContext)
                    .load(user.getOwner().getAvatarUrl())
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .centerInside())
                    .into(mImageUser);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemUserClickListener.onItemClick(mUsers.get(getAdapterPosition()));
        }
    }
}
