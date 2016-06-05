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
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CircleAdapater.ItemClickListener, DrawCircle.ItemClickListener {

    private RecyclerView rvCircle;
    private LinearLayout llCanvas;
    public static final String MyPREFERENCES = "MyPrefs";
    List<Integer> colors = new ArrayList<Integer>();
    private Context context;
    private Button btnReset;
    private Button btnUndo;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    public int colorPosition = 0;
    private CircleAdapater.ItemClickListener itemClickListner;
    private DrawCircle.ItemClickListener circleClickListner;
    private CircleAdapater cAdapter;
    Gson gson;
    private DrawCircle drawCircle;
    private List<Item> circlePoints= new ArrayList<Item>();;
    private List<Integer> position =new ArrayList<Integer>();;
    private CircleObject circleObject = new CircleObject();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gson = new Gson();
        prepareCircleItem();
        context = this;
        llCanvas = (LinearLayout) findViewById(R.id.llCanvas);
        btnReset = (Button)findViewById(R.id.btnReset);
        btnUndo = (Button)findViewById(R.id.btnUndo);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        sharedpreferences.getInt("Color",colorPosition);
        gson = new Gson();
        String circleObjectString = sharedpreferences.getString("circleObject", "");
        if(circleObjectString!=null && !circleObjectString.equals("")) {
            circleObject = gson.fromJson(circleObjectString, CircleObject.class);
            position = circleObject.getColorList();
            circlePoints = circleObject.getItemList();
        }
        itemClickListner = this;
        circleClickListner=this;
        drawCircle = new DrawCircle(circleClickListner, context,0, circlePoints, position);
        llCanvas.addView(drawCircle);
        cAdapter = new CircleAdapater(colors, context, itemClickListner);
        rvCircle = (RecyclerView) findViewById(R.id.rvCircle);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvCircle.setLayoutManager(cLayoutManager);
        rvCircle.setItemAnimator(new DefaultItemAnimator());
        rvCircle.setAdapter(cAdapter);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCircle.clearView();
            }
        });

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawCircle.removeLastElement();
            }
        });

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
        super.onStop();
    }

    @Override
    public void onPause() {
        circleObject.setColorList(position);
        circleObject.setItemList(circlePoints);
        gson = new Gson();
        String circleObjectString = gson.toJson(circleObject);
        editor.putString("circleObject", circleObjectString);
        editor.commit();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(int colorValue) {

        colorPosition = colorValue;
        Toast.makeText(context, "This is the image", Toast.LENGTH_SHORT).show();
        drawCircle.setColorValue(colorValue);


    }


    @Override
    public void onTouch(List<Item> circlePoints, List<Integer> position) {
        this.circlePoints=circlePoints;
        this.position=position;
    }
}
