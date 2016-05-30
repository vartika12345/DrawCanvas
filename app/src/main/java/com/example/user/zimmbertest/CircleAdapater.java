package com.example.user.zimmbertest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 30-05-2016.
 */
public class CircleAdapater extends RecyclerView.Adapter<CircleAdapater.ViewHolder> {
    //private int[] colors;
    List<Integer> colors = new ArrayList<Integer>();
    Context context;
    private ItemClickListener clickListener;

    public CircleAdapater(List<Integer> colors,Context context,ItemClickListener clickListener)
    {
        this.colors = colors;
        this.context = context;
        this.clickListener = clickListener;
    }


    @Override
    public CircleAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_circle,parent,false);
        return new ViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(final CircleAdapater.ViewHolder holder, int position) {
        holder.ivCircle.setImageResource(R.drawable.circle);
        final GradientDrawable gd = (GradientDrawable)holder.ivCircle.getDrawable().getCurrent();
        gd.setColor(colors.get(position));
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
       public ImageView ivCircle;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView, ItemClickListener clickListener) {
            super(itemView);
            ivCircle = (ImageView)itemView.findViewById(R.id.ivCircle);

            itemView.setTag(itemView);
            this.clickListener = clickListener;

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }


        @Override
        public void onClick(View v) {
            clickListener.onClick(colors.get(getAdapterPosition()));

        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick( colors.get(getAdapterPosition()));
            return true;
        }
    }
    public interface ItemClickListener {
        void onClick(int color);
    }

}
