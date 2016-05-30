package com.example.user.zimmbertest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.zimmbertest.CircleAdapater.*;

public class MainActivity extends AppCompatActivity implements CircleAdapater.ItemClickListener {

    private static final String TAG = "ch";
    private RecyclerView rvCircle;
    private LinearLayout llCanvas;
    public static final String MyPREFERENCES = "MyPrefs";

    // private List<Color> circle = new ArrayList<Color>();
    List<Integer> colors = new ArrayList<Integer>();
    private Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private CircleAdapater.ItemClickListener itemClickListner;


    private ImageView ivCanvasCircle;

    private CircleAdapater cAdapter;
    private Boolean flag;
    private float x, y;
    private int color =1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareCircleItem();
        context = this;
        llCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        ivCanvasCircle = (ImageView) findViewById(R.id.ivCanvasCircle);

        itemClickListner = this;
        cAdapter = new CircleAdapater(colors, context, itemClickListner);
        rvCircle = (RecyclerView) findViewById(R.id.rvCircle);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCircle.setLayoutManager(cLayoutManager);
        rvCircle.setItemAnimator(new DefaultItemAnimator());
        rvCircle.setAdapter(cAdapter);
        llCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        llCanvas.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    x = event.getX();
                    y = event.getY();
                    placeImage();
                    flag = true;
                    return true;
                }
                return false;
            }
        });

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        flag = sharedpreferences.getBoolean("State",false);
        if(flag)
        {
            x= sharedpreferences.getFloat("X", 1);
            y= sharedpreferences.getFloat("Y", 1);
            color = sharedpreferences.getInt("Color", 1);
            placeImage();
        }
    }

    private void placeImage() {

        int touchX = (int) x;
        int touchY = (int) y;
        ivCanvasCircle.setX(touchX);
        ivCanvasCircle.setY(touchY);
        ivCanvasCircle.setVisibility(View.VISIBLE);
        ivCanvasCircle.setImageResource(R.drawable.circle);

        final GradientDrawable gd = (GradientDrawable) ivCanvasCircle.getDrawable().getCurrent();
        gd.setColor(color);


    }

    private void prepareCircleItem() {
        String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors);
        // List<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < colorsTxt.length; i++) {
            int newColor = Color.parseColor(colorsTxt[i]);
            colors.add(newColor);
        }
          }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onStop() {
        Log.w(TAG, "App stopped");
        super.onStop();
    }

    @Override
    public void onPause() {
        Log.w(TAG, "App Paused");
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat("X", x);
        editor.putFloat("Y", y);
        editor.putBoolean("State",flag);
        editor.putInt("Color",color);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.w(TAG, "App destroyed");
        super.onDestroy();
    }

    @Override
    public void onClick(int color) {
        this.color= color;
        placeImage();
    }
}
