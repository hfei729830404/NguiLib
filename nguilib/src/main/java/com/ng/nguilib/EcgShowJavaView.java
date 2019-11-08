package com.ng.nguilib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ng.nguilib.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

public class EcgShowJavaView extends View {

    public static int SHOW_MODEL = 0;
    public static final int SHOW_MODEL_ALL = 0;
    public static final int SHOW_MODEL_DYNAMIC_SCROLL = 1;
    public static final int SHOW_MODEL_DYNAMIC_REFRESH = 2;

    private float mWidth = 0f;
    private float mHeight = 0f;

    private Paint mPaint;
    private Path mPath;

    private float INTERVAL_SCROLL_REFRESH = 80f;

    private List<Float> refreshList = new ArrayList<>();
    private int showIndex = 0;


    private float MAX_VALUE = 20f;

    private int intervalNumHeart = 0;
    private float intervalRowHeart = 0f;
    private float intervalColumnHeart = 0f;

    private float HEART_LINE_STROKE_WIDTH = 2.5f;

    private List<Float> mDatas = new ArrayList<>();

    private float mHeartLinestrokeWidth = 0f;

    private float GRID_LINE_STROKE_WIDTH = 0.5f;
    private float GRID_WIDTH_AND_HEIGHT = 5f;

    private int mRow = 0;
    private int mColumn = 0;
    private float intervalRow = 0f;
    private float intervalColumn = 0f;


    private float mGridLinestrokeWidth = 0f;
    private float mGridstrokeWidthAndHeight = 0f;


    private Context mContext;


    public EcgShowJavaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;





        init();
    }


    private void init() {
        mPaint = new Paint();
        mPath = new Path();
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mGridLinestrokeWidth = DensityUtil.INSTANCE.dip2px(mContext, GRID_LINE_STROKE_WIDTH);
        mGridstrokeWidthAndHeight = DensityUtil.INSTANCE.dip2px(mContext, GRID_WIDTH_AND_HEIGHT);

        mColumn = (int) (mWidth / mGridstrokeWidthAndHeight);
        mRow = (int) (mHeight / mGridstrokeWidthAndHeight);

        intervalColumn = mWidth / mColumn;
        intervalRow = mHeight / mRow;

        mHeartLinestrokeWidth = DensityUtil.INSTANCE.dip2px(mContext, HEART_LINE_STROKE_WIDTH);
        initData();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
//        switch (SHOW_MODEL) {
//            case SHOW_MODEL_ALL:
//                drawHeartAll(canvas);
//                break;
//            case SHOW_MODEL_DYNAMIC_SCROLL:
//                drawHeartScroll(canvas);
//                break;
//            case SHOW_MODEL_DYNAMIC_REFRESH:
//                drawHeartRefresh(canvas);
//                break;
//        }
    }


    public void showLine(Float point) {
        if (refreshList == null) {
            refreshList = new ArrayList<>();
//            data = new Float[intervalNumHeart];
        }
        refreshList.add(point);
        postInvalidate();
    }


    private void drawHeartRefresh(Canvas canvas) {
        mPaint.reset();
        mPath.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#31CE32"));
        mPaint.setStrokeWidth(mGridLinestrokeWidth);
        mPaint.setAntiAlias(true);

        mPath.moveTo(0f, mHeight / 2);


        int nowIndex = refreshList == null ? 0 : refreshList.size();


        if (nowIndex == 0) {
            return;
        }

        showIndex = (nowIndex < intervalNumHeart) ? (nowIndex - 1) : ((nowIndex - 1) % intervalNumHeart);


        for (int i = 0; i < intervalNumHeart; i++) {

            if (i > refreshList.size() - 1) {
                break;
            }


            if (nowIndex <= intervalNumHeart) {

                mDatas.remove(i);
                mDatas.add(i, refreshList.get(i));

            } else {
                int times = (nowIndex - 1) / intervalNumHeart;

                int temp = times * intervalNumHeart + i;

                if (temp < nowIndex) {
                    mDatas.remove(i);
                    mDatas.add(i, refreshList.get(temp));
                }
            }
        }


        Float nowX = 0f;
        Float nowY = 0f;

        for (int i = 0; i < mDatas.size(); i++) {
            nowX = i * intervalRowHeart;


            Float dataValue = mDatas.get(i);
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight / 2 - dataValue * intervalColumnHeart;

            if (i - 1 == showIndex) {
                mPath.moveTo(nowX, nowY);

            } else {
                mPath.lineTo(nowX, nowY);
            }
        }
        canvas.drawPath(mPath, mPaint);
    }


    private void drawHeartScroll(Canvas canvas) {
        if (mDatas.isEmpty()) {
            return;
        }
        mPaint.reset();
        mPath.reset();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(mGridLinestrokeWidth);
        mPaint.setAntiAlias(true);


        mPath.moveTo(0f, mHeight / 2);


        Float nowX = 0f;
        Float nowY = 0f;


        for (int i = 0; i < (Math.min(mDatas.size(), 200)); i++) {
            nowX = i * intervalRowHeart;
            float dataValue = mDatas.get(i);

            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight / 2 - dataValue * intervalColumnHeart;


            System.out.println(String.format("%s %s", nowX, nowY));

            mPath.lineTo(nowX, nowY);
        }

        canvas.drawPath(mPath, mPaint);
        postInvalidate();
    }


    private void drawHeartAll(Canvas canvas) {
        if (mDatas.isEmpty()) {
            return;
        }
        mPaint.reset();
        mPath.reset();

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#31CE32"));
        mPaint.setStrokeWidth(mGridLinestrokeWidth);
        mPaint.setAntiAlias(true);

        mPath.moveTo(0f, mHeight / 2);
        Float nowX = 0f;
        Float nowY = 0f;

        for (int i = 0; i < mDatas.size(); i++) {
            nowX = i * intervalRowHeart;
            float dataValue = mDatas.get(i);
            if (dataValue > 0) {
                if (dataValue > MAX_VALUE * 0.8f) {
                    dataValue = MAX_VALUE * 0.8f;
                }
            } else {
                if (dataValue < -MAX_VALUE * 0.8f) {
                    dataValue = -(MAX_VALUE * 0.8f);
                }
            }
            nowY = mHeight / 2 - dataValue * intervalColumnHeart;
            mPath.lineTo(nowX, nowY);
        }
        canvas.drawPath(mPath, mPaint);
    }


    private void drawGrid(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.parseColor("#d8d8d8"));
        mPaint.setStrokeWidth(mGridLinestrokeWidth);
        mPaint.setAntiAlias(true);

        for (int i = 0; i < mColumn; i++) {
            if (i % 5 == 0) {
                mPaint.setStrokeWidth(DensityUtil.INSTANCE.dip2px(mContext, 1f));
                mPaint.setColor(Color.parseColor("#999999"));
            } else {
                mPaint.setStrokeWidth(DensityUtil.INSTANCE.dip2px(mContext, 0.5f));
                mPaint.setColor(Color.parseColor("#d8d8d8"));
            }
            canvas.drawLine(i * intervalColumn, 0f, i * intervalColumn, mHeight, mPaint);
        }

        for (int i = 0; i < mRow; i++) {
//            mPath.moveTo(0f, i * intervalRow);

            if (i % 5 == 0) {

                mPaint.setStrokeWidth(DensityUtil.INSTANCE.dip2px(mContext, 1f));
                mPaint.setColor(Color.parseColor("#999999"));
            } else {
                mPaint.setStrokeWidth(DensityUtil.INSTANCE.dip2px(mContext, 0.5f));
                mPaint.setColor(Color.parseColor("#d8d8d8"));
            }
            canvas.drawLine(0f, i * intervalRow, mWidth, i * intervalRow, mPaint);

//            mPath.lineTo(mWidth, i * intervalRow);
//            System.out.println(String.format("%s %s", mRow, i * intervalRow));
        }


        canvas.drawPath(mPath, mPaint);
    }


    public void setModel(int model) {
        this.SHOW_MODEL = model;
        initData();
    }


    private void initData() {
        switch (SHOW_MODEL) {
            case SHOW_MODEL_ALL:
                intervalNumHeart = mDatas.size();
                intervalRowHeart = mWidth / intervalNumHeart;
                intervalColumnHeart = mHeight / (MAX_VALUE * 2);
                break;

            case SHOW_MODEL_DYNAMIC_SCROLL:

                intervalRowHeart = mWidth / DensityUtil.INSTANCE.dip2px(mContext, INTERVAL_SCROLL_REFRESH);
                intervalNumHeart = (int) (mWidth / intervalRowHeart);
                intervalColumnHeart = mHeight / (MAX_VALUE * 2);

                break;
            case SHOW_MODEL_DYNAMIC_REFRESH:
                intervalRowHeart = mWidth / DensityUtil.INSTANCE.dip2px(mContext, INTERVAL_SCROLL_REFRESH);
                intervalNumHeart = (int) (mWidth / intervalRowHeart);
                intervalColumnHeart = mHeight / (MAX_VALUE * 2);
                break;
        }


    }


    public void appendData(Float data) {


        mDatas.add(data);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        return super.onTouchEvent(event);

    }
}
