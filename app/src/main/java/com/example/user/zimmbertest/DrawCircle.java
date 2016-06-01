package com.example.user.zimmbertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 31-05-2016.
 */
public class DrawCircle extends View {
// set Initial Color

    private Paint drawPaint;
    private int color = Color.BLACK;
    // Store circles to draw each time the user touches down
    private List<Item> circlePoints;
    private Context context;
    private List<Item> position = new ArrayList<>();
    public DrawCircle(Context context, List<Item> color) {
        super(context);
        this.context = context;
        position = color;
        circlePoints = new ArrayList<Item>();
        setUpPaint();
    }

    private void setUpPaint() {

        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.FILL);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    @Override
    protected void onDraw(Canvas canvas){

        for(Item c : position )
        {
            drawPaint.setColor(c.getColor());
        }

        for (Item p : circlePoints) {
            canvas.drawCircle(p.x, p.y, 50, drawPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        circlePoints.add(new Item(Math.round(touchX), Math.round(touchY)));
        // indicate view should be redrawn
        postInvalidate();
        return true;
    }
}
