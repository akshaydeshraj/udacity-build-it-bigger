package com.udacity.gradle.builditbigger;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * @author akshay
 * @since 7/8/16
 */

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest extends ActivityTestRule<MainActivity> {

    public EndpointsAsyncTaskTest(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Before
    public void setUp() throws Exception {

    }

}