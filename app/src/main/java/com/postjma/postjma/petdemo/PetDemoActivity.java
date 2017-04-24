package com.postjma.postjma.petdemo;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private static final int UI_WASH_ANIMATION_DELAY = 1500;
    private static final int UI_JOY_ANIMATION_DELAY = 500;
    private final Handler mAnimateHandler = new Handler();
    private boolean mIsAlive;
    private View mContentView;
    private View mControlsView;
    private ImageView mImageView;
    private Drawable mDraw;
    private Button mFeedButton;
    private Button mJoyButton;
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

    private final Runnable mWashPart1Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            washAnimate(3);
            mAnimateHandler.postDelayed(mWashPart2Runnable, UI_WASH_ANIMATION_DELAY);
        }
    };
    private final Runnable mWashPart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            washAnimate(2);
            mAnimateHandler.postDelayed(mWashPart3Runnable, UI_WASH_ANIMATION_DELAY);
        }
    };
    private final Runnable mWashPart3Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            washAnimate(1);
            mAnimateHandler.postDelayed(mWashPart4Runnable, UI_WASH_ANIMATION_DELAY);
        }
    };
    private final Runnable mWashPart4Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            washAnimate(0);

            mControlsView.setVisibility(View.VISIBLE);
            mImageView.setImageDrawable(mDraw);
            mImageView.startAnimation(mTranslateAnimation);
        }
    };

    private final Runnable mJoyPart1Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(2);
            mAnimateHandler.postDelayed(mJoyPart2Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(1);
            mAnimateHandler.postDelayed(mJoyPart3Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart3Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(0);
            mAnimateHandler.postDelayed(mJoyPart4Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart4Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(3);
            mAnimateHandler.postDelayed(mJoyPart5Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart5Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(2);
            mAnimateHandler.postDelayed(mJoyPart6Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart6Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(1);
            mAnimateHandler.postDelayed(mJoyPart7Runnable, UI_JOY_ANIMATION_DELAY);
        }
    };
    private final Runnable mJoyPart7Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            joyAnimate(0);

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

    private void playJoySound()
    {
        ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        tg.startTone(ToneGenerator.TONE_DTMF_0, 100);
    }

    private void feedAnimate(int stages)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
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

    private void drawWashLine(float yPos, Canvas canvas, Paint paint)
    {
        float yPosEnd = yPos + 5.0f;
        canvas.drawArc(new RectF(5.0f, yPos, 10.0f, yPosEnd), 180.0f, 180.0f, false, paint);
        canvas.drawArc(new RectF(10.0f, yPos, 15.0f, yPosEnd), 180.0f, -180.0f, false, paint);
        canvas.drawArc(new RectF(15.0f, yPos, 20.0f, yPosEnd), 180.0f, 180.0f, false, paint);
        canvas.drawArc(new RectF(20.0f, yPos, 25.0f, yPosEnd), 180.0f, -180.0f, false, paint);
        canvas.drawArc(new RectF(25.0f, yPos, 30.0f, yPosEnd), 180.0f, 180.0f, false, paint);
        canvas.drawArc(new RectF(30.0f, yPos, 35.0f, yPosEnd), 180.0f, -180.0f, false, paint);
        canvas.drawArc(new RectF(35.0f, yPos, 40.0f, yPosEnd), 180.0f, 180.0f, false, paint);
        canvas.drawArc(new RectF(40.0f, yPos, 45.0f, yPosEnd), 180.0f, -180.0f, false, paint);
    }

    private void joyAnimate(int stages)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sym_def_app_icon);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);

        canvas.drawBitmap(bitmap, 0, 0, null);
        switch (stages)
        {
            case 3:
                canvas.drawOval(new RectF(40.0f, 10.0f, 45.0f, 15.0f), paint);
                break;
            case 2:
                canvas.drawOval(new RectF(37.5f, 7.5f, 45.0f, 15.0f), paint);
                break;
            case 1:
                canvas.drawOval(new RectF(35.0f, 5.0f, 45.0f, 15.0f), paint);

                canvas.drawPoint(40.0f, 3.0f, paint); // North
                canvas.drawPoint(40.0f, 17.0f, paint); // South
                canvas.drawPoint(33.0f, 10.0f, paint); // East
                canvas.drawPoint(47.5f, 10.0f, paint); // West

                canvas.drawPoint(34.5f, 6.5f, paint); // Northeast
                canvas.drawPoint(45.5f, 6.5f, paint); // Northwest
                canvas.drawPoint(34.5f, 13.5f, paint); // Southeast
                canvas.drawPoint(45.5f, 13.5f, paint); // Southwest
                break;
            default:
                break;
        }
        mImageView.setImageDrawable(new BitmapDrawable(getResources(), canvasBitmap));

        if (stages > 0)
        playJoySound();
    }

    private void washAnimate(int stages)
    {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sym_def_app_icon);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);

        canvas.drawBitmap(bitmap, 0, 0, null);
        switch (stages)
        {
            case 4:
                drawWashLine(5.0f, canvas, paint);
                drawWashLine(10.0f, canvas, paint);
                drawWashLine(15.0f, canvas, paint);
                break;
            case 3:
                drawWashLine(20.0f, canvas, paint);
                drawWashLine(25.0f, canvas, paint);
                drawWashLine(30.0f, canvas, paint);
                break;
            case 2:
                drawWashLine(35.0f, canvas, paint);
                drawWashLine(40.0f, canvas, paint);
                break;
            case 1:
                drawWashLine(40.0f, canvas, paint);
                break;
            default:
                break;
        }
        mImageView.setImageDrawable(new BitmapDrawable(getResources(), canvasBitmap));
    }

    private void deadAnimate()
    {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), sym_def_app_icon);
        Bitmap canvasBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBitmap);
        canvas.drawColor(Color.BLACK);

        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawText("x", 14.48f, 14.0f, paint);
        canvas.drawText("x", 28.48f, 14.0f, paint);
        mImageView.setImageDrawable(new BitmapDrawable(getResources(), canvasBitmap));
    }

    private void feedHandler()
    {
        mTranslateAnimation.cancel();
        mTranslateAnimation.reset();

        mControlsView.setVisibility(View.GONE);

        feedAnimate(4);
        mAnimateHandler.postDelayed(mFeedPart1Runnable, UI_FEED_ANIMATION_DELAY);
    }

    private void joyHandler()
    {
        mTranslateAnimation.cancel();
        mTranslateAnimation.reset();

        mControlsView.setVisibility(View.GONE);

        joyAnimate(3);
        mAnimateHandler.postDelayed(mJoyPart1Runnable, UI_JOY_ANIMATION_DELAY);
    }

    private void washHandler()
    {
        mTranslateAnimation.cancel();
        mTranslateAnimation.reset();

        washAnimate(4);
        mAnimateHandler.postDelayed(mWashPart1Runnable, UI_WASH_ANIMATION_DELAY);
    }

    private void statHandler()
    {
    }

    private void toggleAlive()
    {
        if (mIsAlive) {
            mTranslateAnimation.cancel();
            mTranslateAnimation.reset();

            mStatButton.setText("RESET");
            mFeedButton.setVisibility(View.GONE);
            mJoyButton.setVisibility(View.GONE);
            mWashButton.setVisibility(View.GONE);

            mIsAlive = false;

            deadAnimate();
        }
        else {
            mStatButton.setText("STAT");
            mFeedButton.setVisibility(View.VISIBLE);
            mJoyButton.setVisibility(View.VISIBLE);
            mWashButton.setVisibility(View.VISIBLE);

            mIsAlive = true;

            mImageView.setImageDrawable(mDraw);
            mImageView.startAnimation(mTranslateAnimation);
        }
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

        mJoyButton = (Button)findViewById(R.id.petbutton);
        mJoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                joyHandler();
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

        mIsAlive = true;

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
