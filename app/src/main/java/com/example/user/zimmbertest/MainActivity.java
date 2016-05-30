package com.example.user.zimmbertest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.zimmbertest.CircleAdapater.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCircle;
    private List<Color> circle = new ArrayList<Color>();
    List<Integer> colors = new ArrayList<Integer>();
    private Context context;

    private CircleAdapater cAdapter;
   // private int[] colors = new int[] { Color.parseColor("#F0F0F0"), Color.parseColor("#D2E4FC"),Color.parseColor() }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareCircleItem();
        context = this;
        cAdapter  = new CircleAdapater(colors,context);
        rvCircle = (RecyclerView)findViewById(R.id.rvCircle);
        LinearLayoutManager cLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvCircle.setLayoutManager(cLayoutManager);
        rvCircle.setItemAnimator(new DefaultItemAnimator());
        rvCircle.setAdapter(cAdapter);



    }

    private void prepareCircleItem() {
        String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors);
       // List<Integer> colors = new ArrayList<Integer>();
        for (int i = 0; i < colorsTxt.length; i++) {
            int newColor = Color.parseColor(colorsTxt[i]);
            colors.add(newColor);
        }
      /*  circle.add(new Color(Blu);
        circle.add();
        circle.add(R.color.blue);
        circle.add(R.color.brown);
        circle.add(R.color.darkBlue);*/
    }


}
