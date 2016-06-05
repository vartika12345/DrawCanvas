package com.example.user.zimmbertest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by USER on 31-05-2016.
 */
public class DrawCircle extends View {
// set Initial Color

    private Paint drawPaint;
    // Store circles to draw each time the user touches down
    private List<Item> circlePoints = new ArrayList<Item>();
    private ItemClickListener itemClickListener;
    private Context context;
    private int colorValue;
    private int counter;
    private float touchX;
    private float touchY;
    private List<Integer> position= new ArrayList<Integer>();

    public DrawCircle(ItemClickListener itemClickListener, Context context, int colorValue, List<Item> circlePoints, List<Integer> position) {
        super(context);
        this.context = context;
        this.itemClickListener=itemClickListener;
        this.colorValue = colorValue;
        this.position = position;
        this.circlePoints = circlePoints;
        counter=position.size();
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

    public void clearView() {

        position.clear();
        circlePoints.clear();
        colorValue = 0;
        counter = 0;

        postInvalidate();
    }

    public void removeLastElement() {

        if (counter != 0) {
            counter = counter - 1;
            position.remove(counter);
            circlePoints.remove(counter);
        }

        itemClickListener.onTouch(circlePoints, position);
        postInvalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < counter; i++) {
                drawPaint.setColor(position.get(i));
                canvas.drawCircle(circlePoints.get(i).x, circlePoints.get(i).y, 30, drawPaint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();

        if (colorValue != 0) {
            counter = counter + 1;
            position.add(colorValue);
            circlePoints.add(new Item(Math.round(touchX), Math.round(touchY)));
        }
        itemClickListener.onTouch(circlePoints, position);
        // indicate view should be redrawn
        postInvalidate();
        return true;
    }

    public interface ItemClickListener {
        void onTouch(List<Item> circlePoints, List<Integer> position);
    }
}
