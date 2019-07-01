# Backfround Jobs

Android O has more restriction about background jobs.

____

## JobService

Suits for **SHORT** background task (less than 10 minutes)

```java
public class MyJobService extends JobService {  
   
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob");
        doJob(params);
        return true;
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob");
        // whether or not you would like JobScheduler to automatically retry your failed job.
        return false;
    }
    private void doJob(JobParameters params) {
        // I am on the main thread, so if you need to do background work,
        // be sure to start up an AsyncTask, Thread, or IntentService!
    }
}
```

Needs Permission : 

```xml
<service android:name=".MyJobService"  
         android:permission="android.permission.BIND_JOB_SERVICE" />
```

____

## Job Scheduler

<https://medium.com/google-developers/scheduling-jobs-like-a-pro-with-jobscheduler-286ef8510129>

This is a service so needs a `<service>` tag in menifest, and it requires `BIND_JOB_SERVICE` permission, too.

- It runs on mainthread.
- Performs work based on conditions, not on time.
- JobScheduler is guaranteed to get your job done

```java
JobScheduler jobScheduler =
    (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
jobScheduler.schedule(new JobInfo.Builder(LOAD_ARTWORK_JOB_ID,
    new ComponentName(this, DownloadArtworkJobService.class))
    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
    .build());
```

And we can specific on what condition should this job be started. For example : `Network` metered/unmetered, `charging` or `idle`.


____

## Foreground Service

- This will pop a notification, thus you can run your service in foreground.
- can do some jobs that takes a lot of time. Since this is in the foreground, it will not be killed.