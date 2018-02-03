package com.agarwal.ashish.qna;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;
import com.agarwal.ashish.qna.data.local.DatabaseHelper;
import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.test.common.TestDataFactory;
import com.agarwal.ashish.qna.util.DefaultConfig;
import com.agarwal.ashish.qna.util.RxSchedulersOverrideRule;

import static junit.framework.Assert.assertEquals;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private DatabaseHelper mDatabaseHelper;
    private AppDatabase mDb;

    @Before
    public void setup() {
        if (mDatabaseHelper == null) {
            mDb = AppDatabase.getAppDatabase(RuntimeEnvironment.application);
            mDatabaseHelper = new DatabaseHelper(mDb);
        }
    }

    @Test
    public void setRibots() {


        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);
        TestObserver<RibotProfile> result = new TestObserver<>();
        mDatabaseHelper.setRibots(ribotProfiles).subscribe(result);
        result.assertNoErrors();
        TestObserver<List<RibotProfile>> newResult = new TestObserver<>();
        mDatabaseHelper.getRibots().subscribe(newResult);
        assertEquals(2, newResult.values().get(0).size());
        newResult.assertValue(ribotProfiles);
    }

    @Test
    public void getRibots() {
        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);

        mDatabaseHelper.setRibots(ribotProfiles).subscribe();

        TestObserver<List<RibotProfile>> result = new TestObserver<>();
        mDatabaseHelper.getRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValue(ribotProfiles);
    }


    @After
    public void finishComponentTesting() {
        // sInstance is the static variable name which holds the singleton instance
        resetSingleton(AppDatabase.class, "database");
    }

    private void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
