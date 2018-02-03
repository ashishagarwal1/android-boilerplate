package com.agarwal.ashish.qna.data.local;

import com.agarwal.ashish.qna.room.database.AppDatabase;
import com.agarwal.ashish.qna.room.entities.RibotProfile;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

@Singleton
public class DatabaseHelper {

    private final AppDatabase mDb;

    @Inject
    public DatabaseHelper(AppDatabase appDatabase) {
        this.mDb = appDatabase;
    }

    public Observable<RibotProfile> setRibots(final Collection<RibotProfile> newRibotProfiles) {
        return Observable.create(new ObservableOnSubscribe<RibotProfile>() {
            @Override
            public void subscribe(ObservableEmitter<RibotProfile> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                mDb.ribotDao().deleteAll();
                mDb.ribotDao().insert(newRibotProfiles);
                emitter.onComplete();

            }
        });
    }

    public Observable<List<RibotProfile>> getRibots() {
        return Observable.create(new ObservableOnSubscribe<List<RibotProfile>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RibotProfile>> emitter) throws Exception {
                if (emitter.isDisposed()) return;
                emitter.onNext(mDb.ribotDao().getRibots());
                emitter.onComplete();
            }
        });
    }

}
