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
 * Created by woniu on 16/7/8.
 */
public class AbsProgressBar extends View {

    protected Context context;

    /**画笔*/
    protected Paint paint;

    /**整个控件宽度*/
    protected float width;

    /**整个控件高度*/
    protected float height;

    /**进度条填充起始色*/
    protected int startFillColor;

    /**进度条填充中间色*/
    protected int middleFillColor;

    /**进度条填充结束颜色*/
    protected int endFillColor;

    /**进度条未填充颜色*/
    protected int backgroundColor;

    /**文字颜色*/
    protected int textColor;

    /**进度条*/
    protected int progress;

    public AbsProgressBar(Context context) {
        super(context);
        this.context = context;
        initPaint();
    }

    public AbsProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
        initPaint();
    }

    public AbsProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
        initPaint();
    }

    /**
     * 初始化
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.progressBar);
        initAttr(a);
        a.recycle();
    }

    /**
     * 初始化属性
     * @param a
     */
    protected void initAttr(TypedArray a){
        progress = a.getInteger(R.styleable.progressBar_progress, 0);
        startFillColor = a.getColor(R.styleable.progressBar_startFillColor, 0xffff0000);
        middleFillColor = a.getColor(R.styleable.progressBar_middleFillColor, 0xffff0000);
        endFillColor = a.getColor(R.styleable.progressBar_endFillColor, 0xffff0000);
        backgroundColor = a.getColor(R.styleable.progressBar_backgroundColor, 0xfff4f4f4);
        textColor = a.getColor(R.styleable.progressBar_textColor, 0xffffffff);
    }

    /**
     * 初始化画笔
     */
    protected void initPaint(){
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    /**
     * 获取各元素尺寸
     */
    protected void getDimension(){
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getDimension();
        drawProgress(canvas);
        drawText(canvas);
    }

    /**
     * 绘制进度
     * @param canvas
     */
    public void drawProgress(Canvas canvas){

    }

    /**
     * 绘制文字
     * @param canvas
     */
    public void drawText(Canvas canvas){

    }

    /**
     * 获取Shader
     * @return
     */
    protected Shader getShader(float width){
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
     * 设置无填充色
     * @param backgroundColor
     */
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 设置文字颜色
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
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
}
