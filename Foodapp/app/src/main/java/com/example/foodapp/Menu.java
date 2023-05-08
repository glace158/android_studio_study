package com.example.foodapp;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Menu {
    private String price;
    private String name;
    private String description;
    private int count;

    public Menu(String name ,String price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
        this.count = 0;
    }
    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getCount() {return count;}

    public void addCount() {this.count++;}
    public void removeCount() {this.count = this.count <= 0 ? 0 : this.count-1;}
}
