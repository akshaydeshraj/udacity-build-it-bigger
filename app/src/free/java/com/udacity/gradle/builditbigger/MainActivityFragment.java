package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.axay.displaylibrary.JokeDisplayActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener, JokeFetchListener {

    InterstitialAd mInterstitialAd;
    AdRequest adRequest;

    Button btnJoke;
    ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        btnJoke = (Button) root.findViewById(R.id.btn_joke);
        btnJoke.setOnClickListener(this);

        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        requestNewInterstitial();

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        mAdView.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                fetchJoke();
            }
        });

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_joke:
                showAd();
                break;
        }
    }

    @Override
    public void onJokeFetched(String joke) {
        progressBar.setVisibility(View.GONE);
        startActivity(JokeDisplayActivity.getJokeDisplayActivityIntent(getActivity(), joke));
    }

    private void requestNewInterstitial() {
        adRequest = new AdRequest.Builder()
                .addTestDevice("3288260347D6377A7FDC59C188ACEA78")
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void showAd() {
        mInterstitialAd.show();
    }

    private void fetchJoke() {
        progressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute();
    }
}