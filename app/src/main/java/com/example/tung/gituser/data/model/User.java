package com.example.tung.gituser.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("id")
    @NonNull
    private int mId;
    @SerializedName("name")
    @Nullable
    private String mName;
    @SerializedName("private")
    @Nullable
    private boolean mPrivate;
    @SerializedName("owner")
    @Nullable
    private Owner mOwner;
    @SerializedName("url")
    @Nullable
    private String mUrl;
    @SerializedName("license")
    @Nullable
    private License mLicense;

    public User(UserBuilder userBuilder) {
        this.mId = userBuilder.mId;
        this.mName = userBuilder.mName;
        this.mPrivate = userBuilder.mPrivate;
        this.mOwner = userBuilder.mOwner;
        this.mUrl = userBuilder.mUrl;
        this.mLicense = userBuilder.mLicense;
    }

    protected User(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mPrivate = in.readByte() != 0;
        mOwner = in.readParcelable(Owner.class.getClassLoader());
        mUrl = in.readString();
        mLicense = in.readParcelable(License.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeByte((byte) (mPrivate ? 1 : 0));
        dest.writeParcelable(mOwner, flags);
        dest.writeString(mUrl);
        dest.writeParcelable(mLicense, flags);
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public boolean isPrivate() {
        return mPrivate;
    }

    public Owner getOwner() {
        return mOwner;
    }

    public String getUrl() {
        return mUrl;
    }

    public License getLicense() {
        return mLicense;
    }

    public class UserBuilder {
        @SerializedName("id")
        @NonNull
        private int mId;
        @SerializedName("name")
        @Nullable
        private String mName;
        @SerializedName("private")
        @Nullable
        private boolean mPrivate;
        @SerializedName("owner")
        @Nullable
        private Owner mOwner;
        @SerializedName("url")
        @Nullable
        private String mUrl;
        @SerializedName("license")
        @Nullable
        private License mLicense;

        public UserBuilder setId(int id) {
            mId = id;
            return this;
        }

        public UserBuilder setName(String name) {
            mName = name;
            return this;
        }

        public UserBuilder setPrivate(boolean aPrivate) {
            mPrivate = aPrivate;
            return this;
        }

        public UserBuilder setOwner(Owner owner) {
            mOwner = owner;
            return this;
        }

        public UserBuilder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public UserBuilder setLicense(License license) {
            mLicense = license;
            return this;
        }

        public User getUser() {
            return new User(this);
        }
    }
}
