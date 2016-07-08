package com.geqian.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by woniu on 16/7/5.
 */
public class ProgressBar extends AbsProgressBar {

    /**进度条宽度*/
    protected float progressWidth;

    /**指示圆点颜色*/
    private int arrowPointColor;

    public ProgressBar(Context context) {
        super(context);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initAttr(TypedArray a) {
        super.initAttr(a);
        arrowPointColor = a.getColor(R.styleable.progressBar_arrowPointColor, 0xffffffff);
    }

    @Override
    protected void getDimension() {
        super.getDimension();
        progressWidth = (float) (progress / 100.0 * width);
    }

    @Override
    public void drawProgress(Canvas canvas) {
        if (progressWidth < height / 2 - dip2px(2)){
            //绘制无填充背景
            paint.setColor(backgroundColor);
            RectF rectF = new RectF(0, 0, width, height);
            canvas.drawRoundRect(rectF, height / 2, height / 2, paint);

            //绘制中间圆心
            paint.setColor(arrowPointColor);
            canvas.drawCircle(height/2, height/2, height / 2 - dip2px(2), paint);
        } else if (progressWidth > height / 2 - dip2px(2)){
            //绘制无填充背景
            paint.setColor(backgroundColor);
            RectF backgroundRectF = new RectF(0, 0, width, height);
            canvas.drawRoundRect(backgroundRectF, height / 2, height / 2, paint);

            //绘制填充背景
            paint.setShader(getShader(progressWidth));
            RectF fillRectF = new RectF(0, 0, progressWidth, height);
            canvas.drawRoundRect(fillRectF, height / 2, height / 2, paint);

            //绘制中间圆心
            initPaint();
            paint.setColor(arrowPointColor);
            canvas.drawCircle(progressWidth - height/2, height/2, height / 2 - dip2px(2), paint);
        } else {
            //绘制填充背景
            paint.setShader(getShader(progressWidth));
            RectF fillRectF = new RectF(0, 0, progressWidth, height);
            canvas.drawRoundRect(fillRectF, height / 2, height / 2, paint);

            //绘制中间圆心
            initPaint();
            paint.setColor(arrowPointColor);
            canvas.drawCircle(progressWidth - height/2, height/2, height / 2 - dip2px(2), paint);
        }
    }

    /**
     * 设置指示点颜色
     * @param arrowPointColor
     */
    public void setArrowPointColor(int arrowPointColor) {
        this.arrowPointColor = arrowPointColor;
    }

}
