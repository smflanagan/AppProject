package com.example.smflanagan.appproject;

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

/**
 * ItemData class: Java class that serves a user-defined data type
 // Items include 5 parameters and act as a single data type
 */
public class ItemData {

    /**
     * Class variables for each component of user-defined data type ItemData
     */
    private String item_name;
    private double item_cost;
    private String seller_name;
    private String location_data;
    private Image item_image;

    /**
     * Class variables required to set item_image to desired image
     */
    String imgString;
    private static int RESULT_LOAD_IMG = 1;

    /**
     * Constructs a new ItemData according to parameters inputted in creation
     * @param name
     * @param cost
     * @param seller
     * @param location
     */
    public ItemData(String name, double cost, String seller, String location /*, Image image */) {
        item_name = name;
        item_cost = cost;
        seller_name = seller;
        location_data = location;
        //item_image = image;
    }

    /**
     * Constructs a new (empty) ItemData with no parameters (everything String -> null and double -> zero)
     */
    public ItemData() {
        item_name = "";
        item_cost = 0;
        seller_name = "";
        location_data = "";
        //item_image = default image;
    }

    /**
     * Method returns only the name of the item
     * @return
     */
    public String getItemName() {
        return item_name;
    }

    /**
     * Method returns only the cost of the item
     * @return
     */
    public double getItemCost() {
        return item_cost;
    }

    /**
     * Method returns only the seller of the item
     * @return
     */
    public String getSeller() {
        return seller_name;
    }

    /**
     * Method returns only the location of the item
     * @return
     */
    public String getItemLocation() {
        return location_data;
    }

    /**
     * Method returns only the image of the item
     * @return
     */
    public Image getItemImage() {
        return item_image;
    }

    /**
     * Method sets the name of the item to the inputted parameter
     * @param name
     */
    public void setItemName(String name) {
        item_name = name;
    }

    /**
     * Method sets the cost of the item to the inputted parameter
     * @param cost
     */
    public void setItemCost(Double cost) {
        item_cost = cost;
    }

    /**
     * Method sets the seller of the item to the inputted parameter
     * @param seller
     */
    public void setSeller(String seller) {
        seller_name = seller;
    }

    /**
     * Method sets the location of the item to the inputted parameter
     * @param location
     */
    public void setItemLocation(String location) {
        location_data = location;
    }

    /**
     * Method sets the image of the item to the inputted parameter
     * @param image
     */
    public void setItemImage(Image image) {
        item_image = image;
    }

}