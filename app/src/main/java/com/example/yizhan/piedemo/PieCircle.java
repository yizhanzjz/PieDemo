package com.example.yizhan.piedemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * 像一个分成几块的披萨
 * Created by yizhan on 2017/10/14.
 */

public class PieCircle extends View {


    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    private float mStartAngle = 0;

    private List<Pie> mDatas;

    public PieCircle(Context context) {
        super(context);
        initPaint();
    }

    public PieCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
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

        if (mDatas == null) {
            return;
        }


        float currentAngle = mStartAngle;

        canvas.translate(mWidth / 2, mHeight / 2);//将canvas的中心移到当前view的中心

        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);//较短边一般的0.8长度，作为半径


        RectF rectF = new RectF(-r, -r, r, r);

        int size = mDatas.size();
        for (int i = 0; i < size; i++) {

            Pie pie = mDatas.get(i);
            mPaint.setColor(pie.color);

            Log.i("TAG", "currentAngle == " + currentAngle);

            canvas.drawArc(rectF, currentAngle, pie.angle, true, mPaint);
            currentAngle += pie.angle;
        }
    }


    public void setStartAngle(float startAngle) {
        this.mStartAngle = startAngle;
        invalidate();
    }

    public void setDatas(List<Pie> datas) {

        if (datas == null) {
            return;
        }

        this.mDatas = datas;
        initDatas();
        invalidate();
    }

    private void initDatas() {

        float sum = 0;

        int size = mDatas.size();
        for (int i = 0; i < size; i++) {
            Pie pie = mDatas.get(i);

            pie.color = mColors[i % mColors.length];

            sum += pie.value;
        }

        for (int i = 0; i < size; i++) {
            Pie pie = mDatas.get(i);

            float percent = pie.value / sum;
            pie.angle = percent * 360;
        }
    }

}
