package com.example.tung.gituser.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Owner implements Parcelable {
    @SerializedName("id")
    @NonNull
    private int mId;
    @SerializedName("login")
    @Nullable
    private String mLogin;
    @SerializedName("avatar_url")
    @Nullable
    private String mAvatarUrl;
    @SerializedName("url")
    @Nullable
    private String mUrl;
    @SerializedName("site_admin")
    @Nullable
    private boolean mSiteAdmin;

    public Owner(OwnerBuilder ownerBuilder) {
        this.mId = ownerBuilder.mId;
        this.mLogin = ownerBuilder.mLogin;
        this.mAvatarUrl = ownerBuilder.mAvatarUrl;
        this.mUrl = ownerBuilder.mUrl;
        this.mSiteAdmin = ownerBuilder.mSiteAdmin;
    }

    public class OwnerBuilder {
        @SerializedName("id")
        @NonNull
        private int mId;
        @SerializedName("login")
        @Nullable
        private String mLogin;
        @SerializedName("avatar_url")
        @Nullable
        private String mAvatarUrl;
        @SerializedName("url")
        @Nullable
        private String mUrl;
        @SerializedName("site_admin")
        @Nullable
        private boolean mSiteAdmin;

        public OwnerBuilder setId(int id) {
            mId = id;
            return this;
        }

        public OwnerBuilder setLogin(String login) {
            mLogin = login;
            return this;
        }

        public OwnerBuilder setAvatarUrl(String avatarUrl) {
            mAvatarUrl = avatarUrl;
            return this;
        }

        public OwnerBuilder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public OwnerBuilder setSiteAdmin(boolean siteAdmin) {
            mSiteAdmin = siteAdmin;
            return this;
        }

        public Owner getOwner() {
            return new Owner(this);
        }
    }

    public int getId() {
        return mId;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    public boolean isSiteAdmin() {
        return mSiteAdmin;
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mLogin);
        dest.writeString(mAvatarUrl);
        dest.writeString(mUrl);
        dest.writeByte((byte) (mSiteAdmin ? 1 : 0));
    }

    public Owner(Parcel in) {
        mId = in.readInt();
        mLogin = in.readString();
        mAvatarUrl = in.readString();
        mUrl = in.readString();
        mSiteAdmin = in.readByte() != 0;
    }
}
