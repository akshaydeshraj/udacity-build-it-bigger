package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.axay.displaylibrary.JokeDisplayActivity;
import com.axay.jokes.backend.jokesApi.JokesApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * @author akshay
 * @since 7/8/16
 */

class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

    private static JokesApi myApiService = null;
    private Context mContext;

    public EndpointsAsyncTask(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(Void... arg0) {
        if (myApiService == null) {  // Only do this once
            JokesApi.Builder builder = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("https://build-it-bigger-1470576893982.appspot.com/_ah/api/");

            myApiService = builder.build();
        }


        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mContext.startActivity(JokeDisplayActivity.getJokeDisplayActivityIntent(mContext, result));
    }
}