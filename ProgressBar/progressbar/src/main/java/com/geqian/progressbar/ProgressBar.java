package com.geqian.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by woniu on 16/7/5.
 */
public class ProgressBar extends View {

    /**进度条填充起始色*/
    private int startFillColor;

    /**进度条填充中间色*/
    private int middleFillColor;

    /**进度条填充结束颜色*/
    private int endFillColor;

    /**指示圆点颜色*/
    private int arrowPointColor;

    /**无填充颜色*/
    private int backgroundColor;

    /**进度*/
    private int progress;

    /**指示圆点半径*/
    private int arrowPointRadius;


    public ProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ProgressBar(Context context) {
        super(context);
//        init(context);
    }

    public ProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.progressBar);

        progress = a.getInteger(R.styleable.progressBar_progress, 0);
        arrowPointRadius = (int)a.getDimension(R.styleable.progressBar_arrowPointRadius, 0);
        startFillColor = a.getColor(R.styleable.progressBar_startFillColor, 0xffff0000);
        middleFillColor = a.getColor(R.styleable.progressBar_middleFillColor, 0xffff0000);
        endFillColor = a.getColor(R.styleable.progressBar_endFillColor, 0xffff0000);
        arrowPointColor = a.getColor(R.styleable.progressBar_arrowPointColor, 0xffffffff);
        backgroundColor = a.getColor(R.styleable.progressBar_backgroundColor, 0xfff4f4f4);
        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        drawRect(canvas, paint);
        drawCircle(canvas, paint);
    }

    /**
     * 绘制圆
     * @param canvas
     * @param paint
     */
    private void drawCircle(Canvas canvas, Paint paint){
        float width = getWidth();
        float height = getHeight();
        float progressWidth = (float) (progress / 100.0 * width);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(startFillColor);

        //绘制左边圆角
        canvas.drawCircle(height/2, height/2, height / 2, paint);

        if (progressWidth == 0) {
            //绘制中间圆角
            paint.setColor(endFillColor);
            canvas.drawCircle(height/2 , height/2, height / 2, paint);

            //绘制中心圆点
            paint.setColor(arrowPointColor);
            canvas.drawCircle(height/2, height/2, arrowPointRadius == 0 ? height / 2 - 6 : arrowPointRadius / 2, paint);

            //绘制右边圆角
            paint.setColor(backgroundColor);
            canvas.drawCircle(width - height/2 , height/2, height / 2, paint);
        } else if (width - progressWidth > 0){
            //绘制右边圆角
            paint.setColor(backgroundColor);
            canvas.drawCircle(width - height/2 , height/2, height / 2, paint);

            //绘制中间圆角
            paint.setColor(endFillColor);
            canvas.drawCircle(progressWidth , height/2, height / 2, paint);

            //绘制中心圆点
            paint.setColor(arrowPointColor);
            canvas.drawCircle(progressWidth, height/2, arrowPointRadius == 0 ? height / 2 - 6 : arrowPointRadius / 2, paint);
        } else {
            //绘制中间圆角
            paint.setColor(endFillColor);
            canvas.drawCircle(width - height/2 , height/2, height / 2, paint);

            //绘制中心圆点
            paint.setColor(arrowPointColor);
            canvas.drawCircle(width - height/2, height/2, arrowPointRadius == 0 ? height / 2 - 6 : arrowPointRadius / 2, paint);
        }


    }

    /**
     * 绘制长方形
     * @param canvas
     * @param paint
     */
    private void drawRect(Canvas canvas, Paint paint){
        float width = getWidth();
        float height = getHeight();
        float progressWidth = (float) (progress / 100.0 * width);

        if (progressWidth == 0){
            //绘制无填充背景
            paint.setColor(backgroundColor);
            canvas.drawRect(height / 2, 0, width - height / 2, height, paint);
        } else if (width - progressWidth > 0){
            //绘制无填充背景
            paint.setColor(backgroundColor);
            canvas.drawRect(height / 2, 0, width - height / 2, height, paint);

            //绘制填充背景
            paint.setShader(getShader(progressWidth));
            canvas.drawRect(height / 2, 0, progressWidth, height, paint);
        } else {
            //绘制填充背景
            paint.setShader(getShader(width));
            canvas.drawRect(height / 2, 0, width - height / 2, height, paint);
        }

    }

    /**
     * 获取Shader
     * @return
     */
    private Shader getShader(float width){
        int colors[] = new int[3];
        float positions[] = new float[3];

        // 第1个点
        colors[0] = startFillColor;
        positions[0] = 0;

        // 第2个点
        colors[1] = middleFillColor;
        positions[1] = 0.5f;

        // 第3个点
        colors[2] = endFillColor;
        positions[2] = 1;


        LinearGradient shader = new LinearGradient(
                0, 0,
                width, 0,
                colors,
                positions,
                Shader.TileMode.MIRROR);

        return shader;
    }


    /**
     * 设置progress
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 设置开始填充色
     * @param startFillColor
     */
    public void setStartFillColor(int startFillColor) {
        this.startFillColor = startFillColor;
    }

    /**
     * 设置填充中间色
     * @param middleFillColor
     */
    public void setMiddleFillColor(int middleFillColor) {
        this.middleFillColor = middleFillColor;
    }

    /**
     * 设置填充结束色
     * @param endFillColor
     */
    public void setEndFillColor(int endFillColor) {
        this.endFillColor = endFillColor;
    }

    /**
     * 设置指示点颜色
     * @param arrowPointColor
     */
    public void setArrowPointColor(int arrowPointColor) {
        this.arrowPointColor = arrowPointColor;
    }

    /**
     * 设置指示点半径
     * @param arrowPointRadius
     */
    public void setArrowPointRadius(int arrowPointRadius) {
        this.arrowPointRadius = arrowPointRadius;
    }

    /**
     * 设置底色
     * @param backgroundColor
     */
    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
