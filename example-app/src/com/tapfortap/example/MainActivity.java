package com.tapfortap.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.tapfortap.AppWallAd;
import com.tapfortap.BannerAd;
import com.tapfortap.InterstitialAd;
import com.tapfortap.TapForTap;

public class MainActivity extends Activity {
    private static final String TAG = "TapForTap-Test-App";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        TapForTap.enableTestMode();
        TapForTap.setEnvironment("development");
        TapForTap.initialize(this, "1a5c984d14f48bcb875913fab43c54b9");

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        float density = getResources().getDisplayMetrics().density;
        BannerAd bannerAd = BannerAd.createAndShow(this, new BannerAd.BannerAdListener() {
            @Override
            public void bannerAdOnReceive(BannerAd bannerAd) {
                Log.d(TAG, "bannerAdOnReceive");
            }

            @Override
            public void bannerAdOnFail(BannerAd bannerAd, String s, Throwable throwable) {
                Log.d(TAG, "bannerAdOnFail");
            }

            @Override
            public void bannerAdOnTap(BannerAd bannerAd) {
                Log.d(TAG, "bannerAdOnTap");
            }
        });

        int width = (int)(320 * density);
        int height = (int)(50 * density);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(width, height);
        viewParams.gravity = Gravity.CENTER;
        bannerAd.setLayoutParams(viewParams);

        final InterstitialAd interstitialAd = InterstitialAd.createAndLoad(this, new InterstitialAd.InterstitialAdListener() {
            @Override
            public void interstitialAdOnReceive(InterstitialAd interstitialAd) {
                Log.d(TAG, "interstitialAdOnReceive");
            }

            @Override
            public void interstitialAdOnFail(InterstitialAd interstitialAd, String s, Throwable throwable) {
                Log.d(TAG, "interstitialAdOnFail");
                interstitialAd.load();
            }

            @Override
            public void interstitialAdOnShow(InterstitialAd interstitialAd) {
                Log.d(TAG, "interstitialAdOnShow");
            }

            @Override
            public void interstitialAdOnTap(InterstitialAd interstitialAd) {
                Log.d(TAG, "interstitialAdOnTap");
            }

            @Override
            public void interstitialAdOnDismiss(InterstitialAd interstitialAd) {
                Log.d(TAG, "interstitialAdOnDismiss");
            }
        });

        final AppWallAd appWallAd = AppWallAd.createAndLoad(this, new AppWallAd.AppWallAdListener() {
            @Override
            public void appWallAdOnReceive(AppWallAd appWallAd) {
                Log.d(TAG, "appWallAdOnReceive");
            }

            @Override
            public void appWallAdOnFail(AppWallAd appWallAd, String s, Throwable throwable) {
                Log.d(TAG, "appWallAdOnFail");
                // If we fail try to load again.
                appWallAd.load();
            }

            @Override
            public void appWallAdOnShow(AppWallAd appWallAd) {
                Log.d(TAG, "appWallAdOnShow");
            }

            @Override
            public void appWallAdOnTap(AppWallAd appWallAd) {
                Log.d(TAG, "appWallAdOnTap");
            }

            @Override
            public void appWallAdOnDismiss(AppWallAd appWallAd) {
                Log.d(TAG, "appWallAdOnDismiss");
            }
        });

        Button showInterstitial = new Button(this);
        showInterstitial.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showInterstitial.setText("Show Interstitial");
        showInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitialAd.show();
            }
        });

        Button showAppWall = new Button(this);
        showAppWall.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showAppWall.setText("Show AppWall");
        showAppWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appWallAd.show();
            }
        });

        layout.addView(bannerAd);
        layout.addView(showAppWall);
        layout.addView(showInterstitial);
        setContentView(layout);
	}
}
