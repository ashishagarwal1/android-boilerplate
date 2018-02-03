package com.agarwal.ashish.qna.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.agarwal.ashish.qna.room.constants.RivotDbConstants;
import com.agarwal.ashish.qna.room.converters.DateTypeConverter;
import com.agarwal.ashish.qna.room.converters.RibotNameTypeConverter;
import com.agarwal.ashish.qna.room.model.RibotName;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by ashishaggarwal on 29/01/18.
 */

@Entity(tableName = RivotDbConstants.TABLE_NAME)
public class RibotProfile implements Comparable<RibotProfile> {

    @PrimaryKey
    @ColumnInfo(name = RivotDbConstants.COLUMN_EMAIL)
    @NonNull
    @SerializedName("email")
    private String mEmail;

    @ColumnInfo(name = RivotDbConstants.COLUMN_NAME)
    @TypeConverters({RibotNameTypeConverter.class})
    @SerializedName("name")
    private RibotName mName;

    @ColumnInfo(name = RivotDbConstants.COLUMN_HEX_COLOR)
    @NonNull
    @SerializedName("hexColor")
    private String mHexColor;

    @ColumnInfo(name = RivotDbConstants.COLUMN_DATE_OF_BIRTH)
    @NonNull
    @SerializedName("dateOfBirth")
    @TypeConverters({DateTypeConverter.class})
    private Date mDob;

    @ColumnInfo(name = RivotDbConstants.COLUMN_AVATAR)
    @SerializedName("avatar")
    private String mAvatar;

    @ColumnInfo(name = RivotDbConstants.COLUMN_BIO)
    @SerializedName("bio")
    private String mBio;

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public RibotName getName() {
        return mName;
    }

    public void setName(RibotName name) {
        this.mName = name;
    }

    @NonNull
    public String getHexColor() {
        return mHexColor;
    }

    public void setHexColor(@NonNull String hexColor) {
        this.mHexColor = hexColor;
    }

    @NonNull
    public Date getDob() {
        return mDob;
    }

    public void setDob(@NonNull Date dob) {
        this.mDob = dob;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        this.mAvatar = avatar;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        this.mBio = bio;
    }

    @Override
    public int compareTo(@NonNull RibotProfile another) {
        return mName.getFirst().compareToIgnoreCase(another.getName().getFirst());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RibotProfile)) return false;
        RibotProfile o = (RibotProfile) other;
        return isEqulas(mEmail, o.getEmail()) &&
                isEqulas(mName.getFirst(), o.getName().getFirst());
    }

    private boolean isEqulas(String a, String b) {
        if (a == null && b == null)
            return true;
        if (a != null)
            return a.equals(b);
        return false;
    }
}
