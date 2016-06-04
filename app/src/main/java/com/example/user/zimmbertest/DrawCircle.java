package com.example.user.zimmbertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 31-05-2016.
 */
public class DrawCircle extends View {
// set Initial Color

    private Paint drawPaint;
    // Store circles to draw each time the user touches down
    private List<Item> circlePoints;
    private Context context;
    private int colorValue;
    private int counter;
    private List<Integer> position;

    // HashMap<Integer,Item> map = new HashMap<>();
    public DrawCircle(Context context, int colorValue) {
        super(context);
        this.context = context;
        this.colorValue = colorValue;
        position = new ArrayList<Integer>();
        circlePoints = new ArrayList<Item>();
        setUpPaint();
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }

    private void setUpPaint() {

        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    public void clearView(){

        position.clear();
        colorValue = 0;
        postInvalidate();
    }

    public void callLastElement(){

    }
    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < counter; i++) {

                if(position.size() != 0)
                {
                drawPaint.setColor(position.get(i));
                canvas.drawCircle(circlePoints.get(i).x, circlePoints.get(i).y, 30, drawPaint);
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (colorValue != 0) {
            counter = counter + 1;
            position.add(colorValue);
            circlePoints.add(new Item(Math.round(touchX), Math.round(touchY)));
        }
        // indicate view should be redrawn
        postInvalidate();
        return true;
    }
}
