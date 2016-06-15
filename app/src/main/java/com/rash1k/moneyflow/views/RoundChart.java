package com.rash1k.moneyflow.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rash1k.moneyflow.R;
import com.rash1k.moneyflow.util.Prefs;

import java.util.Random;

/**
 * Created by vserp on 6/15/2016.
 */

public class RoundChart extends View {

    int myDiameter;
    RectF rectF;

    int plan = 1;
    int current = 1;

    public RoundChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //setBackgroundColor(Color.RED);

        rectF = new RectF(0,0,myDiameter,myDiameter);
        Random r = new Random();
        int colorPlan = 0;
        int colorCurrent = 0;

        if (current <= plan) {
            colorPlan = getResources().getColor(R.color.lightGreen);
            colorCurrent = getResources().getColor(R.color.darkGreen);
        } else{
            colorCurrent = getResources().getColor(R.color.darkRed);
            colorPlan = getResources().getColor(R.color.lightRed);
        }
                /*Color.rgb(r.nextInt(256),r.nextInt(256),r.nextInt(256));*/

        Paint paint = new Paint();

        paint.setColor(colorPlan);
        canvas.drawArc(rectF,0,360,true,paint);

        paint.setColor(colorCurrent);

/*
        current = 10;
        plan = 100;
*/

        float angle = current*360/plan;

        canvas.drawArc(rectF,270,angle,true,paint);

        RectF innerCircle = new RectF(myDiameter/4,myDiameter*3,myDiameter*3,myDiameter*3);
        paint.setColor(Color.WHITE);
        //canvas.drawArc(innerCircle,0,360,true,paint);
        canvas.drawCircle(myDiameter/2,myDiameter/2,myDiameter/3,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(Prefs.LOG_TAG,"width - " + widthMeasureSpec + " ///// height - " + heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        myDiameter = getMeasuredHeight()/3;

        setMeasuredDimension(myDiameter,myDiameter);
    }

    public void setValues(int plan,int current){

        this.plan = plan;
        this.current = current;

        draw(new Canvas());
    }
}

