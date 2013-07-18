package com.tapfortap.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.tapfortap.AdView;
import com.tapfortap.AppWall;
import com.tapfortap.Interstitial;
import com.tapfortap.TapForTap;

public class MainActivity extends Activity {
    private static final String TAG = "TapForTap-Test-App";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        TapForTap.initialize(this, "YOUR API KEY");

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setOrientation(LinearLayout.VERTICAL);

        float density = getResources().getDisplayMetrics().density;
        AdView adView = new AdView(this);
        adView.setListener(new AdView.AdViewListener() {
            @Override
            public void onReceiveAd() {
                Log.d(TAG, "AdView onReceiveAd");
            }

            @Override
            public void onFailToReceiveAd(String reason) {
                Log.d(TAG, "AdViwe onFailToReceiveAd: " + reason);
            }

            @Override
            public void onTapAd() {
                Log.d(TAG, "AdView onTapAd");
            }
        });
        int width = (int)(320 * density);
        int height = (int)(50 * density);
        LinearLayout.LayoutParams viewParams = new LinearLayout.LayoutParams(width, height);
        viewParams.gravity = Gravity.CENTER;
        adView.setLayoutParams(viewParams);

        Interstitial.prepare(this, new Interstitial.InterstitialListener() {
            @Override
            public void onReceiveAd() {
                Log.d(TAG, "Interstitial onReceiveAd");
            }

            @Override
            public void onDismiss() {
                Log.d(TAG, "Interstitial onDismiss");
            }

            @Override
            public void onShow() {
                Log.d(TAG, "Interstitial onShow");
            }

            @Override
            public void onFail(String reason) {
                Log.d(TAG, "Interstitial onFail: " + reason);
            }
        });

        AppWall.prepare(this, new AppWall.AppWallListener() {
            @Override
            public void onReceiveAd() {
                Log.d(TAG, "AppWall onReceiveAd");
            }

            @Override
            public void onDismiss() {
                Log.d(TAG, "AppWall onDismiss");
            }

            @Override
            public void onShow() {
                Log.d(TAG, "AppWall onShow");
            }

            @Override
            public void onFail(String reason) {
                Log.d(TAG, "AppWall onFail: " + reason);
            }
        });

        Button showInterstitial = new Button(this);
        showInterstitial.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showInterstitial.setText("Show Interstitial");
        showInterstitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Interstitial.show(MainActivity.this);
            }
        });

        Button showAppWall = new Button(this);
        showAppWall.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        showAppWall.setText("Show AppWall");
        showAppWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppWall.show(MainActivity.this);
            }
        });

        layout.addView(adView);
        layout.addView(showAppWall);
        layout.addView(showInterstitial);
        setContentView(layout);
	}
}
