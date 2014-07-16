package com.xegg.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class XEggImageView extends ImageView {

    private boolean animatedGifImage = false;
    private Paint p;
    private InputStream is = null;
    private Movie mMovie = null;
    private long mMovieStart = 0;
    private TYPE mType = TYPE.FIT_CENTER;
    private float mScaleH = 1f, mScaleW = 1f;
    private int mMeasuredMovieWidth;
    private int mMeasuredMovieHeight;
    private float mLeft;
    private float mTop;

    public XEggImageView(Context context, AttributeSet attrs,
                         int defStyle) {
        super(context, attrs, defStyle);
    }

    public XEggImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XEggImageView(Context context) {
        super(context);
    }

    private static byte[] streamToBytes(InputStream is) {
        ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = is.read(buffer)) >= 0) {
                os.write(buffer, 0, len);
            }
        } catch (java.io.IOException e) {
        }
        return os.toByteArray();
    }

    public void setAnimatedGif(InputStream is, TYPE streachType) throws FileNotFoundException {
        setImageBitmap(null);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        mType = streachType;
        animatedGifImage = true;

        try {
            mMovie = Movie.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        p = new Paint();
    }

    @Override
    public void setImageResource(int resId) {
        animatedGifImage = false;
        super.setImageResource(resId);
    }

    @Override
    public void setImageURI(Uri uri) {
        animatedGifImage = false;
        super.setImageURI(uri);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        animatedGifImage = false;
        super.setImageDrawable(drawable);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mLeft = (getWidth() - mMeasuredMovieWidth) / 2f;
        mTop = (getHeight() - mMeasuredMovieHeight) / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animatedGifImage) {
            long now = android.os.SystemClock.uptimeMillis();
            if (mMovieStart == 0) { // first time
                mMovieStart = now;
            }
            if (mMovie != null) {
                p.setAntiAlias(true);
                int dur = mMovie.duration();
                if (dur == 0) {
                    dur = 1000;
                }
                int relTime = (int) ((now - mMovieStart) % dur);
                mMovie.setTime(relTime);
                canvas.save(Canvas.MATRIX_SAVE_FLAG);
                canvas.scale(mScaleW, mScaleH);
                mMovie.draw(canvas, mLeft / mScaleW, mTop / mScaleH);
                canvas.restore();
                invalidate();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mMovie != null) {
            int movieWidth = mMovie.width();
            int movieHeight = mMovie.height();
            /*
             * Calculate horizontal scaling
			 */
            int measureModeWidth = MeasureSpec.getMode(widthMeasureSpec);
            float scaleW = 1f, scaleH = 1f;
            if (measureModeWidth != MeasureSpec.UNSPECIFIED) {
                int maximumWidth = MeasureSpec.getSize(widthMeasureSpec);
                if (movieWidth > maximumWidth) {
                    scaleW = (float) movieWidth / (float) maximumWidth;
                } else {
                    scaleW = (float) maximumWidth / (float) movieWidth;
                }
            }

			/*
             * calculate vertical scaling
			 */
            int measureModeHeight = MeasureSpec.getMode(heightMeasureSpec);

            if (measureModeHeight != MeasureSpec.UNSPECIFIED) {
                int maximumHeight = MeasureSpec.getSize(heightMeasureSpec);
                if (movieHeight > maximumHeight) {
                    scaleH = (float) movieHeight / (float) maximumHeight;
                } else {
                    scaleH = (float) maximumHeight / (float) movieHeight;
                }
            }

			/*
             * calculate overall scale
			 */
            switch (mType) {
                case FIT_CENTER:
                    mScaleH = mScaleW = Math.min(scaleH, scaleW);
                    break;
                case AS_IS:
                    mScaleH = mScaleW = 1f;
                    break;
                case STREACH_TO_FIT:
                    mScaleH = scaleH;
                    mScaleW = scaleW;
                    break;
            }

            mMeasuredMovieWidth = (int) (movieWidth * mScaleW);
            mMeasuredMovieHeight = (int) (movieHeight * mScaleH);

            setMeasuredDimension(mMeasuredMovieWidth, mMeasuredMovieHeight);

        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public static enum TYPE {
        FIT_CENTER, STREACH_TO_FIT, AS_IS
    }
}
