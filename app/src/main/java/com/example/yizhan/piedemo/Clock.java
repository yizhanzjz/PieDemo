package com.example.yizhan.piedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yizhan on 2017/10/15.
 */

public class Clock extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public Clock(Context context) {
        super(context);
        initPaint();
    }

    public Clock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
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

        canvas.translate(mWidth / 2, mHeight / 2);

        float r = Math.min(mWidth, mHeight) * 1.0f / 2 * 0.8f;
        canvas.drawCircle(0, 0, r, mPaint);

        for (int i = 0; i < 60; i++) {

            if (i % 5 == 0) {
                canvas.drawLine(0, -r + 15, 0, -r, mPaint);
            } else {
                canvas.drawLine(0, -r + 8, 0, -r, mPaint);
            }

            canvas.rotate(6);
        }

    }
}
