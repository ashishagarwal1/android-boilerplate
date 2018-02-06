package com.agarwal.ashish.qna;

import com.agarwal.ashish.qna.data.local.DatabaseHelper;
import com.agarwal.ashish.qna.room.dao.RibotDao;
import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.test.common.TestDataFactory;
import com.agarwal.ashish.qna.util.DefaultConfig;
import com.agarwal.ashish.qna.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.util.Arrays;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.when;


/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    private DatabaseHelper mDatabaseHelper;

    @Mock
    public AppDatabase mDb;

    @Mock
    public RibotDao ribotDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ShadowLog.stream = System.out;
        mDatabaseHelper = new DatabaseHelper(mDb);

    }

    @Test
    public void setRibots() {
        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);
        when(mDb.ribotDao()).thenReturn(ribotDao);
        TestObserver<RibotProfile> result = new TestObserver<>();
        mDatabaseHelper.setRibots(ribotProfiles).subscribe(result);
        result.assertNoErrors();
    }

    @Test
    public void getRibots() {
        RibotProfile ribotProfile1 = TestDataFactory.makeRibot("r1");
        RibotProfile ribotProfile2 = TestDataFactory.makeRibot("r2");
        List<RibotProfile> ribotProfiles = Arrays.asList(ribotProfile1, ribotProfile2);
        when(mDb.ribotDao()).thenReturn(ribotDao);
        when(ribotDao.getRibots()).thenReturn(ribotProfiles);
        TestObserver<List<RibotProfile>> result = new TestObserver<>();
        mDatabaseHelper.getRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValue(ribotProfiles);
    }

//    private void resetSingleton(Class clazz, String fieldName) {
//        Field instance;
//        try {
//            instance = clazz.getDeclaredField(fieldName);
//            instance.setAccessible(true);
//            instance.set(null, null);
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }

}
