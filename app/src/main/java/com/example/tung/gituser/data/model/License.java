package com.example.tung.gituser.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class License implements Parcelable {
    @SerializedName("key")
    @NonNull
    private String mKey;
    @SerializedName("name")
    @Nullable
    private String mName;
    @SerializedName("spdx_id")
    @Nullable
    private String mSpdxId;
    @SerializedName("url")
    @Nullable
    private String mUrl;
    @SerializedName("node_id")
    @Nullable
    private String mNodeId;

    public License(LicenseBuilder licenseBuilder) {
        this.mKey = licenseBuilder.mKey;
        this.mName = licenseBuilder.mName;
        this.mSpdxId = licenseBuilder.mSpdxId;
        this.mUrl = licenseBuilder.mUrl;
        this.mNodeId = licenseBuilder.mNodeId;
    }

    public class LicenseBuilder {
        @SerializedName("key")
        @NonNull
        private String mKey;
        @SerializedName("name")
        @Nullable
        private String mName;
        @SerializedName("spdx_id")
        @Nullable
        private String mSpdxId;
        @SerializedName("url")
        @Nullable
        private String mUrl;
        @SerializedName("node_id")
        @Nullable
        private String mNodeId;

        public LicenseBuilder setKey(String key) {
            mKey = key;
            return this;
        }

        public LicenseBuilder setName(String name) {
            mName = name;
            return this;
        }

        public LicenseBuilder setSpdxId(String spdxId) {
            mSpdxId = spdxId;
            return this;
        }

        public LicenseBuilder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public LicenseBuilder setNodeId(String nodeId) {
            mNodeId = nodeId;
            return this;
        }

        public License getLicense() {
            return new License(this);
        }
    }

    protected License(Parcel in) {
        mKey = in.readString();
        mName = in.readString();
        mSpdxId = in.readString();
        mUrl = in.readString();
        mNodeId = in.readString();
    }

    public static final Creator<License> CREATOR = new Creator<License>() {
        @Override
        public License createFromParcel(Parcel in) {
            return new License(in);
        }

        @Override
        public License[] newArray(int size) {
            return new License[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mKey);
        dest.writeString(mName);
        dest.writeString(mSpdxId);
        dest.writeString(mUrl);
        dest.writeString(mNodeId);
    }
}
