package com.example.user.zimmbertest;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 30-05-2016.
 */
public class CircleAdapater extends RecyclerView.Adapter<CircleAdapater.ViewHolder> {
    //private int[] colors;
    List<Integer> colors = new ArrayList<Integer>();

    public CircleAdapater(List<Integer> colors)
    {
        this.colors = colors;
    }
    @Override
    public CircleAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CircleAdapater.ViewHolder holder, int position) {
        holder.ivCircle.setImageResource(R.drawable.circle);
       GradientDrawable gd = (GradientDrawable)holder.ivCircle.getDrawable().getCurrent();
        gd.setColor(colors.get(position));
       // holder.ivCircle.setBackgroundColor(colors[position]);
      // holder.ivCircle.setBackgroundColor(getResources().getColor(R.id.your_color);
       // holder.ivCircle

    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivCircle;
        public ViewHolder(View itemView) {
            super(itemView);
            ivCircle = (ImageView)itemView.findViewById(R.id.ivCircle);
        }
    }
}
