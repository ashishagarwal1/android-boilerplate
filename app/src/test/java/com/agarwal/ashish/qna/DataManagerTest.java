package com.agarwal.ashish.qna;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import com.agarwal.ashish.qna.data.DataManager;
import com.agarwal.ashish.qna.data.local.DatabaseHelper;
import com.agarwal.ashish.qna.data.local.PreferencesHelper;
import com.agarwal.ashish.qna.data.remote.RibotsService;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.room.model.Ribot;
import com.agarwal.ashish.qna.test.common.TestDataFactory;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock DatabaseHelper mMockDatabaseHelper;
    @Mock PreferencesHelper mMockPreferencesHelper;
    @Mock RibotsService mMockRibotsService;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockRibotsService, mMockPreferencesHelper,
                mMockDatabaseHelper);
    }

    @Test
    public void syncRibotsEmitsValues() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibotProfile("r1"),
                TestDataFactory.makeRibotProfile("r2"));
        stubSyncRibotsHelperCalls(ribots);


        List<RibotProfile> list = new ArrayList<>();
        for (Ribot ribot : ribots) {
            list.add(ribot.getRibotProfile());
        }

        TestObserver<RibotProfile> result = new TestObserver<>();
        mDataManager.syncRibots().subscribe(result);
        result.assertNoErrors();
        result.assertValueSequence(list);
    }

    @Test
    public void syncRibotsCallsApiAndDatabase() {
        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibotProfile("r1"),
                TestDataFactory.makeRibotProfile("r2"));
        stubSyncRibotsHelperCalls(ribots);

        List<RibotProfile> list = new ArrayList<>();
        for (Ribot ribot : ribots) {
            list.add(ribot.getRibotProfile());
        }

        mDataManager.syncRibots().subscribe();
        // Verify right calls to helper methods
        verify(mMockRibotsService).getRibots();
        verify(mMockDatabaseHelper).setRibots(list);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));

        mDataManager.syncRibots().subscribe(new TestObserver<RibotProfile>());
        // Verify right calls to helper methods
        verify(mMockRibotsService).getRibots();
        verify(mMockDatabaseHelper, never()).setRibots(ArgumentMatchers.<RibotProfile>anyList());
    }

    private void stubSyncRibotsHelperCalls(List<Ribot> ribots) {
        // Stub calls to the ribot service and database helper.
        when(mMockRibotsService.getRibots())
                .thenReturn(Observable.just(ribots));

        List<RibotProfile> list = new ArrayList<>();
        for (Ribot ribot : ribots) {
            list.add(ribot.getRibotProfile());
        }
        when(mMockDatabaseHelper.setRibots(list)).thenReturn(Observable.fromIterable(list));
    }

}
