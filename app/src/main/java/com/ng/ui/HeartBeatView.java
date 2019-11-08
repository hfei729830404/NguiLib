package com.ng.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HeartBeatView extends View {


    private Context mContext;

    private float mWidth;
    private float mHeight;


    private int mGridColor;
    private int mGridZeroColor;

    private float mGridWight;
    private Paint mGridPaint;
    private Paint mHeartPaint;
    private Paint mSliderPaint;


    private float MAX_VALUE = 100;


    private float mOff = 0;
    private float mFlagX = 0;
    private float mGap;

    private List<Float> mDatas = new ArrayList<>();


    private Path mPointPath;

    private boolean mShowGrid = true;
    private boolean mShowZeroLine = true;

    private int mDensity = 1;

    public HeartBeatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.HeartBeatView, 0, 0);
            mGridColor = array.getColor(R.styleable.HeartBeatView_cus_grid_color, Color.GRAY);
            mGridZeroColor = array.getColor(R.styleable.HeartBeatView_cus_grid_zero_color, mGridColor);

            mGridWight = array.getDimension(R.styleable.HeartBeatView_cus_grid_width, dp2px(0.5f));
            MAX_VALUE = array.getInteger(R.styleable.HeartBeatView_cus_max_value, 100);
            mDensity = array.getInteger(R.styleable.HeartBeatView_cus_density, 1);
            mShowGrid = array.getBoolean(R.styleable.HeartBeatView_cus_show_grid, true);
            mShowZeroLine = array.getBoolean(R.styleable.HeartBeatView_cus_show_zero_line, true);

            array.recycle();
        }


        mContext = context;


        init();
    }


    private void init() {
        mGridPaint = new Paint();
        mGridPaint.reset();
        mGridPaint.setStyle(Paint.Style.STROKE);
        mGridPaint.setColor(mGridColor);
        mGridPaint.setStrokeWidth(mGridWight);
        mGridPaint.setAntiAlias(true);


        mHeartPaint = new Paint();
        mHeartPaint.reset();
        mHeartPaint.setStyle(Paint.Style.STROKE);
        mHeartPaint.setColor(Color.RED);
        mHeartPaint.setStrokeWidth(mGridWight * 2);
        mHeartPaint.setAntiAlias(true);
        mHeartPaint.setStrokeJoin(Paint.Join.ROUND);
        mHeartPaint.setStrokeCap(Paint.Cap.ROUND);

        mSliderPaint = new Paint();
        mSliderPaint.reset();
        mSliderPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mSliderPaint.setColor(Color.parseColor("#80ffffff"));
        mSliderPaint.setStrokeWidth(mGridWight);
        mSliderPaint.setAntiAlias(true);
        mSliderPaint.setStrokeJoin(Paint.Join.ROUND);
        mSliderPaint.setStrokeCap(Paint.Cap.ROUND);

        mPointPath = new Path();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mGap = Math.abs(mHeight / 2 - mGridWight) / MAX_VALUE;

        if (mShowGrid) {
            drawGrid(canvas);
        }

        drawGridZeroLine(canvas);


        drawHeartLine(canvas);

//        if (mShowSlider) {
//            drawSlider(canvas);
//        }


    }


    private void drawGridZeroLine(Canvas canvas) {
        mGridPaint.setStrokeWidth(mGridWight * 1.5f);
        mGridPaint.setAlpha(255);
        if (mShowZeroLine) {
            mGridPaint.setColor(mGridZeroColor);
        } else {
            mGridPaint.setColor(mGridColor);
        }
        canvas.drawLine(0, mHeight / 2f, mWidth, mHeight / 2f, mGridPaint);
    }


    private void drawGrid(Canvas canvas) {
        mGridPaint.setColor(mGridColor);

        for (int i = 0; i < MAX_VALUE; i++) {
            if ((i + 1) % 5 == 0) {
                mGridPaint.setStrokeWidth(mGridWight * 1.5f);
                mGridPaint.setAlpha(255);
            } else {
                mGridPaint.setStrokeWidth(mGridWight);
                mGridPaint.setAlpha(255 / 2);
            }
            canvas.drawLine(0, (mHeight / 2f) + mGap * (i + 1), mWidth, (mHeight / 2f) + mGap * (i + 1), mGridPaint);
            canvas.drawLine(0, (mHeight / 2f) - mGap * (i + 1), mWidth, (mHeight / 2f) - mGap * (i + 1), mGridPaint);
        }
        for (int i = -1; mGap * i < mWidth + mGap * 5; i++) {
            if (i % 5 == 0) {
                mGridPaint.setStrokeWidth(mGridWight * 1.5f);
                mGridPaint.setAlpha(255);
            } else {
                mGridPaint.setStrokeWidth(mGridWight);
                mGridPaint.setAlpha(255 / 2);
            }
            canvas.drawLine(mGap * i + mGridWight, 0, mGap * i + mGridWight, mHeight, mGridPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mFlagX = event.getX();

                break;
            case MotionEvent.ACTION_MOVE:
                float mTempOff = event.getX() - mFlagX;
                mFlagX = event.getX();
                mOff = mOff + mTempOff;

                scrollTo((int) -(mOff % (mGap * 5)), 0);

                invalidate();
                break;

            case MotionEvent.ACTION_UP:


                break;
        }
        return true;
    }


    public void appendData(float value) {
        mDatas.add(value);
        invalidate();
    }


    private void drawHeartLine(Canvas canvas) {
        mPointPath.reset();

        int len = (int) Math.min(mDatas.size(), mWidth / (mGap * mDensity));


        for (float i = 0; i < len; i++) {
            float value = mDatas.get((int) (mDatas.size() - len + i));
            if (i == 0) {
                mPointPath.moveTo((mGap * mDensity) * i, (mHeight / 2f) - (mHeight / 2f) * (value / MAX_VALUE));
            } else {
                mPointPath.lineTo((mGap * mDensity) * i, (mHeight / 2f) - (mHeight / 2f) * (value / MAX_VALUE));
            }
        }
        canvas.drawPath(mPointPath, mHeartPaint);
    }


    private void drawSlider(Canvas canvas) {
        RectF rect = new RectF(mOff, mHeight - dp2px(20), mOff + dp2px(100), mHeight);
        canvas.drawRect(rect, mSliderPaint);
    }


//    @Override
//    public void run() {
//        mShowSlider = false;
//        invalidate();
//    }
}
