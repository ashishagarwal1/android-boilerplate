package com.agarwal.ashish.qna.room.dao;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.agarwal.ashish.qna.data.local.DatabaseHelper;
import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.test.common.TestDataFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;


/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(AndroidJUnit4.class)
public class RibotDaoTest {

    private AppDatabase mDb;

    @Before
    public void setup() {
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                AppDatabase.class).allowMainThreadQueries().build();
    }

    @Test
    public void setAndGetRibots() {
        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);
        mDb.ribotDao().insert(ribotProfiles);
        assertEquals(mDb.ribotDao().getRibots(), ribotProfiles);
    }

    @Test
    public void setDeleteAllAndgetRibots() {
        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);
        mDb.ribotDao().insert(ribotProfiles);
        assertEquals(mDb.ribotDao().getRibots(), ribotProfiles);
        assertEquals(mDb.ribotDao().getRibots(), ribotProfiles);
        mDb.ribotDao().deleteAll();
        assertEquals(mDb.ribotDao().getRibots(), new ArrayList<>());

    }


    @After
    public void tearDown() {
        mDb.close();
    }
}
