package com.example.myapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MyTextView extends AppCompatTextView {
    private float threshold;
    private float value;
    private int coldColor;
    private int warmColor;
    private String description_Template;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);

        setThreshold(ta.getFloat(R.styleable.MyTextView_threshold, 0));
        setColdColor(ta.getColor(R.styleable.MyTextView_cold_color, Color.BLUE));
        setWarmColor(ta.getColor(R.styleable.MyTextView_warm_color, Color.RED));
        setDescription_Template(ta.getString(R.styleable.MyTextView_description_template));

        ta.recycle();
    }

    public void setThreshold(float value) {
        threshold = value;
    }

    public void setColdColor(int color) {
        coldColor = color;
    }

    public void setWarmColor(int color) {
        warmColor = color;
    }

    public void setDescription_Template(String description_Template) {
        this.description_Template = description_Template;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (value >= threshold) {
            setTextColor(warmColor);
        } else {
            setTextColor(coldColor);
        }

    }

    public void setValue(float value) {
        this.value = value;

        if (description_Template != null) {
            if (description_Template.contains("%s")) {
                setText(String.format(description_Template, String.valueOf(value)));
            } else {
                setText(description_Template.concat(" ").concat(String.valueOf(value)));
            }
        } else {
            setText(String.valueOf(value));
        }

        postInvalidate();
    }
}