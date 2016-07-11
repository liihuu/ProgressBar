package com.geqian.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by woniu on 16/7/8.
 */
public class CircleProgressBar extends AbsProgressBar {

    /**文字大小*/
    private float textSize;

    /**进度条宽度*/
    private float progressWidth;

    /**进度条填充色*/
    private int fillColor;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initAttr(TypedArray a) {
        super.initAttr(a);
        textSize = a.getDimension(R.styleable.progressBar_textSize, dip2px(15));
        fillColor = a.getColor(R.styleable.progressBar_startFillColor, 0xffff0000);
        progressWidth = a.getDimension(R.styleable.progressBar_progressWidth, dip2px(3));
    }

    @Override
    public void drawProgress(Canvas canvas) {
        //绘制外环
        paint.setColor(backgroundColor);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);

        //绘制进度
        paint.setColor(fillColor);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawArc(rectF, -90, (float)(progress / 100.0 * 360), true, paint);

        //绘制内环
        paint.setColor(0xffffffff);
        canvas.drawCircle(width / 2, height / 2, width / 2 - progressWidth, paint);
    }

    @Override
    public void drawText(Canvas canvas) {
        float textWidth = paint.measureText(progress + "%");
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        canvas.drawText(progress + "%", width / 2 - textWidth / 2, height / 2 + textSize / 3, paint);
    }

    /**
     * 设置进度条宽度
     * @param progressWidth
     */
    public void setProgressWidth(float progressWidth) {
        this.progressWidth = progressWidth;
    }

    /**
     * 设置进度条填充色
     * @param fillColor
     */
    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * 设置文字大小
     * @param textSize
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }
}
