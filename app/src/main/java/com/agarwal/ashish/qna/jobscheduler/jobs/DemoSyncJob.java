package com.agarwal.ashish.qna.jobscheduler.jobs;

import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

/**
 * Created by ashishaggarwal on 11/02/18.
 */

public class DemoSyncJob extends Job {

    public static final String TAG = "job_demo_tag";

    @Override
    @NonNull
    protected Job.Result onRunJob(Job.Params params) {
        // run your job here
        return Job.Result.SUCCESS;
    }

    public static void scheduleJob() {
        new JobRequest.Builder(DemoSyncJob.TAG)
                .setExecutionWindow(1000L, 2000L)
                .build()
                .schedule();
    }
}
