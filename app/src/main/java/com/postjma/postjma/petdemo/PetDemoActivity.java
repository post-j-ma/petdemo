package com.postjma.postjma.petdemo;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
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
    private static final int UI_FEED_ANIMATION_DELAY = 1000;
    private final Handler mAnimateHandler = new Handler();
    private View mContentView;
    private View mControlsView;
    private ImageView mImageView;
    private Drawable mDraw;
    private Button mFeedButton;
    private Button mPetButton;
    private Button mWashButton;
    private Button mStatButton;
    private TranslateAnimation mTranslateAnimation;

    private final Runnable mFeedPart1Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            feedAnimate(3);
            mAnimateHandler.postDelayed(mFeedPart2Runnable, UI_FEED_ANIMATION_DELAY);
        }
    };
    private final Runnable mFeedPart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            feedAnimate(2);
            mAnimateHandler.postDelayed(mFeedPart3Runnable, UI_FEED_ANIMATION_DELAY);
        }
    };
    private final Runnable mFeedPart3Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            feedAnimate(1);
            mAnimateHandler.postDelayed(mFeedPart4Runnable, UI_FEED_ANIMATION_DELAY);
        }
    };
    private final Runnable mFeedPart4Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            feedAnimate(0);

            mControlsView.setVisibility(View.VISIBLE);
            mImageView.startAnimation(mTranslateAnimation);
        }
    };

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

    private void sleep(int millisec) {
        try {
            Thread.sleep(millisec);
        }catch (InterruptedException ie) {
            // ignore
        }
    }

    private void playFeedSound()
    {
        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
        tg.startTone(ToneGenerator.TONE_PROP_BEEP, 100);
    }

    private void feedAnimate(int stages)
    {
        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sym_def_app_icon);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);

        canvas.drawBitmap(bitmap, 0, 0, null);
        if (stages > 0)
        canvas.drawRect(40.0f, 5.0f, 45.0f, 10.0f, paint);
        if (stages > 1)
        canvas.drawRect(45.0f, 5.0f, 50.0f, 10.0f, paint);
        if (stages > 2)
        canvas.drawRect(40.0f, 10.0f, 45.0f, 15.0f, paint);
        if (stages > 3)
        canvas.drawRect(45.0f, 10.0f, 50.0f, 15.0f, paint);
        mImageView.setImageDrawable(new BitmapDrawable(getResources(), canvasBitmap));

        playFeedSound();
    }

    private void feedHandler()
    {
        mTranslateAnimation.cancel();
        mTranslateAnimation.reset();

        mControlsView.setVisibility(View.GONE);

        feedAnimate(4);
        mAnimateHandler.postDelayed(mFeedPart1Runnable, UI_FEED_ANIMATION_DELAY);
    }

    private void pettingHandler()
    {
    }

    private void washHandler()
    {
    }

    private void statHandler()
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
                pettingHandler();
            }
        });

        mWashButton = (Button)findViewById(R.id.washbutton);
        mWashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                washHandler();
            }
        });

        mStatButton = (Button)findViewById(R.id.statbutton);
        mStatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                statHandler();
            }
        });
    }
}
