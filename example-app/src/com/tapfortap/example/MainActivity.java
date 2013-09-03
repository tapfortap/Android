package com.tapfortap.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.tapfortap.AppWall;
import com.tapfortap.Banner;
import com.tapfortap.Interstitial;
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
        Banner banner = Banner.create(this, new Banner.BannerListener() {
            @Override
            public void bannerOnReceive(Banner Banner) {
                Log.d(TAG, "BannerOnReceive");
            }

            @Override
            public void bannerOnFail(Banner Banner, String s, Throwable throwable) {
                Log.d(TAG, "BannerOnFail");
            }

            @Override
            public void bannerOnTap(Banner Banner) {
                Log.d(TAG, "BannerOnTap");
            }
        });

        int width = (int)(320 * density);
        int height = (int)(50 * density);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(width, height);
        viewParams.gravity = Gravity.CENTER;
        banner.setLayoutParams(viewParams);

        final Interstitial interstitial = Interstitial.create(this, new Interstitial.InterstitialListener() {
            @Override
            public void interstitialOnReceive(Interstitial interstitial) {
                Log.d(TAG, "InterstitialOnReceive");
            }

            @Override
            public void interstitialOnFail(Interstitial interstitial, String reason, Throwable throwable) {
                Log.d(TAG, "InterstitialOnFail");
                interstitial.load();
            }

            @Override
            public void interstitialOnShow(Interstitial interstitial) {
                Log.d(TAG, "InterstitialOnShow");
            }

            @Override
            public void interstitialOnTap(Interstitial interstitial) {
                Log.d(TAG, "InterstitialOnTap");
            }

            @Override
            public void interstitialOnDismiss(Interstitial interstitial) {
                Log.d(TAG, "InterstitialOnDismiss");
            }
        });

        final AppWall appWall = AppWall.create(this, new AppWall.AppWallListener() {
            @Override
            public void appWallOnReceive(AppWall appWall) {
                Log.d(TAG, "AppWallOnReceive");
            }

            @Override
            public void appWallOnFail(AppWall appWall, String s, Throwable throwable) {
                Log.d(TAG, "AppWallOnFail");
                // If we fail try to load again.
                appWall.load();
            }

            @Override
            public void appWallOnShow(AppWall appWall) {
                Log.d(TAG, "AppWallOnShow");
            }

            @Override
            public void appWallOnTap(AppWall appWall) {
                Log.d(TAG, "AppWallOnTap");
            }

            @Override
            public void appWallOnDismiss(AppWall appWall) {
                Log.d(TAG, "AppWallOnDismiss");
            }
        });

        Button showInterstitial = new Button(this);
        showInterstitial.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showInterstitial.setText("Show Interstitial");
        showInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interstitial.showAndLoad();
            }
        });

        Button showAppWall = new Button(this);
        showAppWall.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showAppWall.setText("Show AppWall");
        showAppWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appWall.showAndLoad();
            }
        });

        layout.addView(banner);
        layout.addView(showAppWall);
        layout.addView(showInterstitial);
        setContentView(layout);
	}
}
