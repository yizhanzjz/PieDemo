package com.example.yizhan.piedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 画一个尺子
 * Created by yizhan on 2017/10/15.
 */

public class MyRuler extends View {


    private int mWidth;
    private int mHeight;

    private int padding = 10;
    private Paint mPaint;

    private int innerPadding = 30;

    private int mRulerCount = 10;

    private int mLengthMax = 70;
    private int mLengthMedium = 50;
    private int mLengthMin = 30;

    public MyRuler(Context context) {
        super(context);
        initPaint();
    }

    public MyRuler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOuter(canvas);

        drawLines(canvas);
    }

    private void drawLines(Canvas canvas) {

        //填充模式
        mPaint.setStyle(Paint.Style.FILL);

        //计算出 尺子标线范围的长度
        int range = mWidth - 2 * padding - 2 * innerPadding;
        //尺子的标线个数
        int lineCnt = mRulerCount * 10 + 1;
        Log.i("TAG", "lineCnt == " + lineCnt);
        //标线与标线之间的距离
        float divider = range * 1.0f / (lineCnt - 1);
        Log.i("TAG", "divider == " + divider);
        //其实坐标
        float startX = padding + innerPadding;
        float startY = mHeight - 2 * padding;

        canvas.translate(startX, startY);
        for (int i = 0; i < lineCnt; i++) {

            if (i % 10 == 0) {
                canvas.drawLine(0, 0, 0, -mLengthMax, mPaint);
            } else if (i % 5 == 0) {
                canvas.drawLine(0, 0, 0, -mLengthMedium, mPaint);
            } else {
                canvas.drawLine(0, 0, 0, -mLengthMin, mPaint);
            }

            //水平移动画布
            canvas.translate(divider, 0);
        }

    }

    private void drawOuter(Canvas canvas) {

//        Log.i("TAG", "mWidth == " + mWidth);
//        Log.i("TAG", "mHeight == " + mHeight);

        //画边界
        mPaint.setStyle(Paint.Style.STROKE);

        //画出尺子的矩形框，这里使用padding作为尺子跟此view上下左右的距离
        Rect rect = new Rect(padding, padding, mWidth - 2 * padding, mHeight - 2 * padding);
        canvas.drawRect(rect, mPaint);
    }
}
