package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.text.TextUtils;

import java.util.concurrent.CountDownLatch;

/**
 * @author akshay
 * @since 8/8/16
 */

public class AsyncTaskTest extends ApplicationTestCase<Application> {

    private String mJsonString = null;
    private Exception mError = null;
    private CountDownLatch signal = null;

    public AsyncTaskTest() {
        super(Application.class);
    }

    @Override
    protected void setUp() throws Exception {
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        signal.countDown();
    }

    public void testEndpointsAsyncTask() throws InterruptedException {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new JokeFetchListener() {
            @Override
            public void onJokeFetched(String joke, Exception e) {
                mJsonString = joke;
                mError = e;
                signal.countDown();
            }
        });

        endpointsAsyncTask.execute();
        signal.await();

        assertNull(mError);
        assertFalse(TextUtils.isEmpty(mJsonString));
    }

}
