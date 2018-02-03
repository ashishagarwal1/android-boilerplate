package com.agarwal.ashish.qna.room.model;

import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ashishaggarwal on 31/01/18.
 */

public class Ribot {

    @SerializedName("profile")
    private RibotProfile ribotProfile;

    public RibotProfile getRibotProfile() {
        return ribotProfile;
    }

    public void setRibotProfile(RibotProfile ribotProfile) {
        this.ribotProfile = ribotProfile;
    }
}
