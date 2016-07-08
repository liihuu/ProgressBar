package com.geqian.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by woniu on 16/7/7.
 */
public class FloatTextProgressBar extends View {

    private Context context;

    /**整个控件宽度*/
    private float width;

    /**整个控件高度*/
    private float height;

    /**进度条宽度*/
    private float progressWidth;

    /**进度条高度*/
    private float progressHeight;

    /**浮动框宽度*/
    private float floatRectWidth;

    /**浮动框高度*/
    private float floatRectHeight;

    /**三角形宽度*/
    private float triangleWidth;

    /**三角形高度*/
    private float triangleHeight;

    /**浮动框左右边距*/
    private float margin;

    /**文字大小*/
    private float textSize;

    /**三角形颜色*/
    private int triangleColor;

    /**浮动框颜色*/
    private int rectColor;

    /**进度条填充颜色*/
    private int fillColor;

    /**进度条未填充颜色*/
    private int backgroundColor;

    /**文字颜色*/
    private int textColor;

    /**进度条*/
    private int progress;

    public FloatTextProgressBar(Context context) {
        super(context);
    }

    public FloatTextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public FloatTextProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.progressBar);

        progress = a.getInteger(R.styleable.progressBar_progress, 0);
        triangleColor = a.getColor(R.styleable.progressBar_triangleColor, 0xffff0000);
        rectColor = a.getColor(R.styleable.progressBar_rectColor, 0xffff0000);
        fillColor = a.getColor(R.styleable.progressBar_startFillColor, 0xffff0000);
        textColor = a.getColor(R.styleable.progressBar_textColor, 0xffffffff);
        backgroundColor = a.getColor(R.styleable.progressBar_backgroundColor, 0xfff4f4f4);
        a.recycle();

    }

    /**
     * 获取各元素尺寸
     */
    private void getDimension(){
        width = getWidth();
        height = getHeight();
        progressWidth = (float) progress / 100 * width;
        progressHeight = height / 5;
        floatRectWidth = height /  5 * 4;
        floatRectHeight = height / 9 * 4;
        triangleWidth = height / 7 * 2;
        triangleHeight = height / 5 + height / 7 * 3;
        margin = dip2px(3);
        textSize = height / 4;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDimension();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        drawProgress(canvas, paint);
        drawFloatRect(canvas, paint);
        drawText(canvas, paint);
    }

    /**
     * 绘制进度条
     * @param canvas
     * @param paint
     */
    private void drawProgress(Canvas canvas, Paint paint){

        //绘制未填充进度条
        paint.setColor(backgroundColor);
        RectF backgroundRectF = new RectF(0, height - progressHeight, width, height);
        canvas.drawRoundRect(backgroundRectF, progressHeight / 2, progressHeight / 2, paint);

        //绘制填充条
        paint.setColor(fillColor);
        RectF fillRectF = new RectF(0, height - progressHeight, progressWidth, height);
        canvas.drawRoundRect(fillRectF, progressHeight / 2, progressHeight / 2, paint);
    }

    /**
     * 绘制浮动框
     * @param canvas
     * @param paint
     */
    private void drawFloatRect(Canvas canvas, Paint paint){

        if (progressWidth < floatRectWidth + margin){
            //绘制浮动框
            paint.setColor(rectColor);
            RectF floatRectF = new RectF(margin, 0, margin + floatRectWidth, floatRectHeight);
            canvas.drawRoundRect(floatRectF, dip2px(2), dip2px(2), paint);

            //绘制三角形
            paint.setColor(triangleColor);
            Path path = new Path();
            path.moveTo(margin + floatRectWidth / 2 - triangleWidth / 2, height / 7 * 3);
            path.lineTo(margin + floatRectWidth / 2 + triangleWidth / 2, height / 7 * 3);
            path.lineTo(margin + floatRectWidth / 2, floatRectWidth / 4 + height / 7 * 3);
            path.close();
            canvas.drawPath(path, paint);
        } else if (width - progressWidth < floatRectWidth + margin){
            //绘制浮动框
            paint.setColor(rectColor);
            RectF floatRectF = new RectF(width - floatRectWidth - margin ,0, width - margin, floatRectHeight);
            canvas.drawRoundRect(floatRectF, dip2px(2), dip2px(2), paint);

            //绘制三角形
            paint.setColor(triangleColor);
            Path path = new Path();
            path.moveTo(width - margin - floatRectWidth / 2 - triangleWidth / 2, height / 7 * 3);
            path.lineTo(width - margin - floatRectWidth / 2 + triangleWidth / 2, height / 7 * 3);
            path.lineTo(width - margin - floatRectWidth / 2, floatRectWidth / 4 + height / 7 * 3);
            path.close();
            canvas.drawPath(path, paint);
        } else {
            //绘制浮动框
            paint.setColor(rectColor);
            RectF floatRectF = new RectF(progressWidth - floatRectWidth / 2 ,0, progressWidth + floatRectWidth / 2, floatRectHeight);
            canvas.drawRoundRect(floatRectF, dip2px(2), dip2px(2), paint);

            //绘制三角形
            paint.setColor(triangleColor);
            Path path = new Path();
            path.moveTo(progressWidth - triangleWidth / 2, height / 7 * 3);
            path.lineTo(progressWidth + triangleWidth / 2, height / 7 * 3);
            path.lineTo(progressWidth, floatRectWidth / 4 + height / 7 * 3);
            path.close();
            canvas.drawPath(path, paint);
        }
    }

    /**
     * 绘制文字
     * @param canvas
     * @param paint
     */
    private void drawText(Canvas canvas, Paint paint){
        paint.setColor(textColor);
        paint.setTextSize(textSize);
        float textWidth = paint.measureText(progress + "%");
        if (progressWidth < floatRectWidth + margin) {
            canvas.drawText(progress + "%", margin + floatRectWidth / 2 - textWidth / 2, floatRectHeight / 2 + textSize / 4, paint);
        } else if (width - progressWidth < floatRectWidth + margin){
            canvas.drawText(progress + "%", width - margin - floatRectWidth / 2 - textWidth / 2, floatRectHeight / 2 + textSize / 4, paint);
        } else {
            canvas.drawText(progress + "%", progressWidth - textWidth / 2, floatRectHeight / 2 + textSize / 4, paint);
        }
    }


    /**
     * dp转px
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        final float scale = this.context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 设置进度
     * @param progress
     */
    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 设置文字颜色
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * 设置进度条未填充色
     * @param backgroundColor
     */
    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 设置进度条填充色
     * @param fillColor
     */
    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * 设置浮动框颜色
     * @param rectColor
     */
    public void setRectColor(int rectColor) {
        this.rectColor = rectColor;
    }

    /**
     * 设置三角形颜色
     * @param triangleColor
     */
    public void setTriangleColor(int triangleColor) {
        this.triangleColor = triangleColor;
    }
}
