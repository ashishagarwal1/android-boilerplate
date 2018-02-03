package com.agarwal.ashish.qna.room.model;

import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ashishaggarwal on 31/01/18.
 */

public class Ribot {

    @SerializedName("profile")
    private RibotProfile mRibotProfile;

    public RibotProfile getRibotProfile() {
        return mRibotProfile;
    }

    public void setRibotProfile(RibotProfile ribotProfile) {
        this.mRibotProfile = ribotProfile;
    }
}
