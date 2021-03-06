/*
 * Copyright (C) 2013 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.example.bannerexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.widget.*;
import android.view.View;

/**
 * Main Activity. Inflates main activity xml and child fragments.
 */
public class MyActivity extends ActionBarActivity
{

    private AdView myAdView;
    private Button colorButton;
    private RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        colorButton = (Button) findViewById(R.id.colorButton);
        background = (RelativeLayout) findViewById(R.id.backgroundLayout);

        /** Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in values/strings.xml. */
        myAdView = (AdView) findViewById(R.id.ad_view);

        /** Create an ad request. Check your logcat output for the hashed device ID to
        * get test ads on a physical device. e.g.
        * "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
         */
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        // Start loading the ad in the background.
        myAdView.loadAd(adRequest);
        setupListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        /**
         * Inflate the menu; this adds items to the action bar if it is present.
         */

        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        /**
         * Handle action bar item clicks here. The action bar will
         * automatically handle clicks on the Home/Up button, so long
         * as you specify a parent activity in AndroidManifest.xml.
         */

        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause()
    {
        if (myAdView != null)
        {
            myAdView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume()
    {
        super.onResume();
        if (myAdView != null)
        {
            myAdView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy()
    {
        if (myAdView != null)
        {
            myAdView.destroy();
        }
        super.onDestroy();
    }

    /**
     * Creates the listners for the button which changes colors.
     */
    public void setupListeners()
    {
        colorButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeBackgroundColor();
            }
        });
    }

    /**
     * The method that gets and sets a random color for the background and the button.
     */
    private void changeBackgroundColor()
    {
        int redColor;
        int greenColor;
        int blueColor;

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() * 256);

        background.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));

        redColor = (int) (Math.random() * 256);
        greenColor = (int) (Math.random() * 256);
        blueColor = (int) (Math.random() * 256);

        colorButton.setBackgroundColor(Color.rgb(redColor, greenColor, blueColor));

    }
}
