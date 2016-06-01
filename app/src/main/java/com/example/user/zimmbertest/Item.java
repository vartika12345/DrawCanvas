package com.example.user.zimmbertest;

/**
 * Created by USER on 31-05-2016.
 */
public class Item {

    float x;
    float y;

    int color;

    public Item(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Item(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
