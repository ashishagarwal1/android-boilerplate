package com.agarwal.ashish.qna.jobscheduler;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.agarwal.ashish.qna.jobscheduler.jobs.DemoSyncJob;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;

/**
 * Created by ashishaggarwal on 11/02/18.
 */

public class MyJobCreator implements JobCreator {

    @Nullable
    public Job create(@NonNull String tag) {
        switch (tag) {
            case DemoSyncJob.TAG:
                return new DemoSyncJob();
            default:
                return null;
        }
    }
}
