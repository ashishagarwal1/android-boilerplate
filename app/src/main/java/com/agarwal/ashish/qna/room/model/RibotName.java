package com.agarwal.ashish.qna.room.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashishaggarwal on 31/01/18.
 */

public class RibotName {

    @SerializedName("first")
    private String mFirst;

    @SerializedName("last")
    private String mLast;

    public String getFirst() {
        return mFirst;
    }

    public String getLast() {
        return mLast;
    }

    public void setLast(String last) {
        this.mLast = last;
    }

    public void setFirst(String first) {
        this.mFirst = first;
    }
}
