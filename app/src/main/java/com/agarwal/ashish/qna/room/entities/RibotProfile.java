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
    private String email;

    @ColumnInfo(name = RivotDbConstants.COLUMN_NAME)
    @TypeConverters({RibotNameTypeConverter.class})
    private RibotName name;

    @ColumnInfo(name = RivotDbConstants.COLUMN_HEX_COLOR)
    @NonNull
    private String hexColor;

    @ColumnInfo(name = RivotDbConstants.COLUMN_DATE_OF_BIRTH)
    @NonNull
    @SerializedName("dateOfBirth")
    @TypeConverters({DateTypeConverter.class})
    private Date dob;

    @ColumnInfo(name = RivotDbConstants.COLUMN_AVATAR)
    private String avatar;

    @ColumnInfo(name = RivotDbConstants.COLUMN_BIO)
    private String bio;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RibotName getName() {
        return name;
    }

    public void setName(RibotName name) {
        this.name = name;
    }

    @NonNull
    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(@NonNull String hexColor) {
        this.hexColor = hexColor;
    }

    @NonNull
    public Date getDob() {
        return dob;
    }

    public void setDob(@NonNull Date dob) {
        this.dob = dob;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public int compareTo(@NonNull RibotProfile another) {
        return name.getFirst().compareToIgnoreCase(another.getName().getFirst());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof RibotProfile)) return false;
        RibotProfile o = (RibotProfile) other;
        return isEqulas(email, o.getEmail()) &&
                isEqulas(name.getFirst(), o.getName().getFirst());
    }

    private boolean isEqulas(String a, String b) {
        if (a == null && b == null)
            return true;
        if (a != null)
            return a.equals(b);
        return false;
    }
}
