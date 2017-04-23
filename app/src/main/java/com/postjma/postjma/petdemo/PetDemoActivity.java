package com.postjma.postjma.petdemo;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import static android.R.drawable.sym_def_app_icon;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class PetDemoActivity extends AppCompatActivity {

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private View mControlsView;
    private ImageView mImageView;
    private Drawable mDraw;
    private Button mFeedButton;
    private Button mPetButton;
    private Button mWashButton;
    private Button mStatButton;
    private TranslateAnimation mTranslateAnimation;

    private void initAnimation()
    {
        mImageView.setImageDrawable(mDraw);

        mTranslateAnimation= new TranslateAnimation(-100.0f, 100.0f, 0.0f, 0.0f);
        mTranslateAnimation.setDuration(2000);
        mTranslateAnimation.setRepeatCount(Animation.INFINITE);
        mTranslateAnimation.setRepeatMode(Animation.REVERSE);
        mTranslateAnimation.setFillAfter(true);
        mImageView.startAnimation(mTranslateAnimation);
    }

    private void feedHandler()
    {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);
        mImageView = (ImageView)findViewById(R.id.imageView);
        mDraw = getResources().getDrawable(sym_def_app_icon);

        initAnimation();

        mFeedButton = (Button)findViewById(R.id.feedbutton);
        mFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                feedHandler();
            }
        });

        mPetButton = (Button)findViewById(R.id.petbutton);
        mPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mContentView.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);
                ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                tg.startTone(ToneGenerator.TONE_PROP_BEEP, 100);
            }
        });

        mWashButton = (Button)findViewById(R.id.washbutton);
        mWashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mContentView.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);
                ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                tg.startTone(ToneGenerator.TONE_PROP_BEEP, 100);
            }
        });

        mStatButton = (Button)findViewById(R.id.statbutton);
        mStatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //mContentView.playSoundEffect(SoundEffectConstants.NAVIGATION_DOWN);
                ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
                tg.startTone(ToneGenerator.TONE_PROP_BEEP, 100);
            }
        });
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);

        // Schedule a runnable to remove the status and navigation bar after a delay
        //mHideHandler.removeCallbacks(mShowPart2Runnable);
        //mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);

        // Schedule a runnable to display UI elements after a delay
        //mHideHandler.removeCallbacks(mHidePart2Runnable);
        //mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }
}
