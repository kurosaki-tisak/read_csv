package com.example.kittisak.top20.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kittisak on 9/20/2017 AD.
 */

public class Download implements Parcelable {

    private int progress;
    private int currentFileSize;
    private int totalFileSize;
    private String filePath;


    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(int currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(int totalFileSize) {
        this.totalFileSize = totalFileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.progress);
        dest.writeInt(this.currentFileSize);
        dest.writeInt(this.totalFileSize);
        dest.writeString(this.filePath);
    }

    public Download() {
    }

    protected Download(Parcel in) {
        this.progress = in.readInt();
        this.currentFileSize = in.readInt();
        this.totalFileSize = in.readInt();
        this.filePath = in.readString();
    }

    public static final Parcelable.Creator<Download> CREATOR = new Parcelable.Creator<Download>() {
        @Override
        public Download createFromParcel(Parcel source) {
            return new Download(source);
        }

        @Override
        public Download[] newArray(int size) {
            return new Download[size];
        }
    };
}
