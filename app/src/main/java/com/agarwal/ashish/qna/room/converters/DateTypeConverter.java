package com.agarwal.ashish.qna.room.converters;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import timber.log.Timber;

/**
 * Created by ashishaggarwal on 01/02/18.
 */

public class DateTypeConverter {

    @TypeConverter
    public String fromDate(Date value) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return df.format(value);
    }

    @TypeConverter
    public Date toDate(String value) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        try {
            Date today = df.parse(value);
            return today;
        } catch (ParseException e) {
            Timber.e("toDate %s %s" , e, value);
        }
        return null;
    }
}
