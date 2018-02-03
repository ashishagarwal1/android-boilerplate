package com.agarwal.ashish.qna.room.converters;

import android.arch.persistence.room.TypeConverter;

import com.agarwal.ashish.qna.room.model.RibotName;
import com.google.gson.Gson;

/**
 * Created by ashishaggarwal on 31/01/18.
 */

public class RibotNameTypeConverter {

    @TypeConverter
    public static RibotName toName(String nameObject) {
        return new Gson().fromJson(nameObject, RibotName.class);
    }

    @TypeConverter
    public static String fromName(RibotName value) {
        return new Gson().toJson(value);
    }
}
