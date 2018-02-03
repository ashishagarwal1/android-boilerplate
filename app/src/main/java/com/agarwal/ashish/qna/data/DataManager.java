package com.agarwal.ashish.qna.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import com.agarwal.ashish.qna.data.local.DatabaseHelper;
import com.agarwal.ashish.qna.data.local.PreferencesHelper;
import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.data.remote.RibotsService;

@Singleton
public class DataManager {

    private final RibotsService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RibotsService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<RibotProfile> syncRibots() {
        return mRibotsService.getRibots()
                .flatMap(list ->
                        Observable.fromIterable(list)
                                .map(item -> item.getRibotProfile())
                                .toList()
                                .toObservable())
                .concatMap(ribotNS -> mDatabaseHelper.setRibots(ribotNS));
    }

    public Observable<List<RibotProfile>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

}
