package com.example.smflanagan.appproject;


import android.media.Image;

import java.util.ArrayList;

/**
 * Created by smflanagan on 3/29/2017.
 * Added support for adding and remocing items from a bundle
 */

public class BundleData {

    private String bundle_name;
    private double bundle_discount;
    private ArrayList<ItemData> item_list = new ArrayList<ItemData>();
    private String location_data;
    private Image bundle_image;
    private String seller_name;

    public BundleData(String name, double discount, ItemData item, String location, Image image, String seller_name) {

        bundle_name = name;
        bundle_discount = discount;

        item_list = new ArrayList<ItemData>();
        item_list.add(item);

        location_data = location;
        bundle_image = image;


    }

    public BundleData() {

        bundle_name = "";
        bundle_discount = 0;

        item_list = new ArrayList<ItemData>();

        location_data = "";
        // bundle_image = default image;


    }

    public void addItem(ItemData item) {

        item_list.add(item);
    }

    public void removeItem(ItemData item) {

        item_list.remove(item);
    }

    public String getBundleName() {
        return bundle_name;
    }

    public double getBundleCost() {

        double bundle_cost = 0.0;
        for (int i = 0; i < item_list.size(); i++) {
            bundle_cost += item_list.get(i).getItemCost();
        }

        bundle_cost = bundle_cost * bundle_discount;
        return bundle_cost;
    }

    public String getSeller() {
        return seller_name;
    }


    public String getBundleLocation() {
        return location_data;
    }

    public Image getBundleImage() {
        return bundle_image;
    }

    public void setBundleName(String name) {
        bundle_name = name;
    }

    public void setSeller(String seller) {
        seller_name = seller;
    }

    public void setLocation(String location) {
        location_data = location;
    }

    public void setBundleImage(Image image) {
        bundle_image = image;
    }


}

