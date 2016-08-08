package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.axay.displaylibrary.JokeDisplayActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeFetchListener {

    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button btnJoke = (Button) root.findViewById(R.id.btn_joke);
        btnJoke.setOnClickListener(this);

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_joke:
                fetchJoke();
                break;
        }
    }

    @Override
    public void onJokeFetched(String joke, Exception e) {
        progressBar.setVisibility(View.GONE);
        startActivity(JokeDisplayActivity.getJokeDisplayActivityIntent(getActivity(), joke));
    }

    private void fetchJoke() {
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute();
    }
}