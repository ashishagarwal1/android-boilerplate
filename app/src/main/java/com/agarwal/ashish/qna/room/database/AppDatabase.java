package com.agarwal.ashish.qna.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.agarwal.ashish.qna.room.converters.DateTypeConverter;
import com.agarwal.ashish.qna.room.converters.RibotNameTypeConverter;
import com.agarwal.ashish.qna.room.dao.RibotDao;
import com.agarwal.ashish.qna.room.entities.RibotProfile;

import javax.inject.Singleton;

/**
 * Created by ashishaggarwal on 29/01/18.
 */

@Database(entities = {RibotProfile.class}, version = 1)
@TypeConverters({RibotNameTypeConverter.class, DateTypeConverter.class})
@Singleton
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract RibotDao ribotDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }
}