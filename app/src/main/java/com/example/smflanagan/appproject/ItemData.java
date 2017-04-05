package com.example.smflanagan.appproject;

/**
 * Created by smflanagan on 4/5/2017.
 */


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class ItemData {
    private String item_name;
    private double item_cost;
    private String seller_name;
    private String location_data;
    private Image item_image;
    String imgString;
    private static int RESULT_LOAD_IMG = 1;


    public ItemData(String name, double cost, String seller, String location, Image image) {

        item_name = name;
        item_cost = cost;
        seller_name = seller;
        location_data = location;
        item_image = image;

    }


    public ItemData() {

        item_name = "";
        item_cost = 0;
        seller_name = "";
        location_data = "";
        //item_image = default image;

    }


    public String getItemName() {
        return item_name;
    }

    public double getItemCost() {
        return item_cost;
    }

    public String getSeller() {
        return seller_name;
    }


    public String getItemLocation() {
        return location_data;
    }

    public Image getItemImage() {
        return item_image;
    }

    public void setItemName(String name) {
        item_name = name;
    }

    public void setItemCost(Double cost) {
        item_cost = cost;
    }

    public void setSeller(String seller) {
        seller_name = seller;
    }

    public void setLocation(String location) {
        location_data = location;
    }

    public void setItemImage(Image image) {
        item_image = image;
    }

}