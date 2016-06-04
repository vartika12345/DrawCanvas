package com.example.user.zimmbertest;

import android.content.Context;
import android.content.SharedPreferences;

import android.graphics.Color;

import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import java.util.List;


import javax.security.auth.login.LoginException;

import static com.example.user.zimmbertest.CircleAdapater.*;

public class MainActivity extends AppCompatActivity implements CircleAdapater.ItemClickListener {

    private static final String TAG = "ch";
    private RecyclerView rvCircle;
    private LinearLayout llCanvas;
    public static final String MyPREFERENCES = "MyPrefs";
    List<Integer> colors = new ArrayList<Integer>();
    private Context context;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    private CircleAdapater.ItemClickListener itemClickListner;
    List<Item> item = new ArrayList<>();
    private CircleAdapater cAdapter;

    private DrawCircle drawCircle;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareCircleItem();
        context = this;
        llCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        drawCircle = new DrawCircle(context,0);

        llCanvas.addView(drawCircle);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        itemClickListner = this;
        cAdapter = new CircleAdapater(colors, context, itemClickListner);
        rvCircle = (RecyclerView) findViewById(R.id.rvCircle);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCircle.setLayoutManager(cLayoutManager);
        rvCircle.setItemAnimator(new DefaultItemAnimator());
        rvCircle.setAdapter(cAdapter);


    }


    private void prepareCircleItem() {
        String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors);
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
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.w(TAG, "App destroyed");
        super.onDestroy();
    }

    @Override
    public void onClick(int colorValue) {

        // item.add(new Item(colorValue));
        Toast.makeText(context, "This is the image", Toast.LENGTH_SHORT).show();
//        llCanvas.addView(new DrawCircle(context, colorValue));
        drawCircle.setColorValue(colorValue);


    }


}
