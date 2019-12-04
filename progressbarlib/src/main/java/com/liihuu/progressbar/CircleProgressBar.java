package com.liihuu.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * Created by woniu on 16/7/8.
 */
public class CircleProgressBar extends FloatTextProgressBar {

    /**文字大小*/
    private float textSize;

    /**进度条宽度*/
    private float progressWidth;

    /**内圈颜色*/
    private int smallCircleColor;

    public CircleProgressBar(Context context) {
        super(context);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.circleProgressBar);
        textSize = a.getDimension(R.styleable.circleProgressBar_textSize, dip2px(15));
        progressWidth = a.getDimension(R.styleable.circleProgressBar_progressWidth, dip2px(3));
        smallCircleColor = a.getColor(R.styleable.circleProgressBar_smallCircleColor, 0xffffffff);
        a.recycle();
    }

    @Override
    public void drawProgress(Canvas canvas) {
        //绘制外环
        paint.setColor(backgroundColor);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);

        //绘制进度
        paint.setColor(fillColor);
        float angle = (progress / 1f /this.maxProgress * 360);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawArc(rectF, -90, angle, true, paint);
        //绘制内环
        paint.setColor(smallCircleColor);
        canvas.drawCircle(width / 2, height / 2, width / 2 - progressWidth, paint);
    }

    @Override
    public void drawText(Canvas canvas) {
        initPaint();
        float progressRate = progress * 100f /this.maxProgress;
        String progressText = progressRate + "%";
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        float textWidth = paint.measureText(progressText);
        canvas.drawText(progressText, width / 2 - textWidth / 2, height / 2 + textSize / 3, paint);
    }

    /**
     * 设置进度条宽度
     * @param progressWidth
     */
    public void setProgressWidth(float progressWidth) {
        this.progressWidth = progressWidth;
    }

    /**
     * 设置文字大小
     * @param textSize
     */
    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    /**
     * 设置内圈颜色
     * @param smallCircleColor
     */
    public void setSmallCircleColor(int smallCircleColor) {
        this.smallCircleColor = smallCircleColor;
    }
}
