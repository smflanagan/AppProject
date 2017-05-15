package com.example.smflanagan.appproject;

import android.media.Image;

import java.util.ArrayList;

/**
 * BundleData class: Java class that serves a user-defined data type
 * Bundles are essentially arrays of items, another user-defined data type
 */
public class BundleData {

    /**
     * Class variables for each component of user-defined data type BundleData
     */
    private String bundle_name;
    private double bundle_discount;
    private ArrayList<ItemData> item_list = new ArrayList<ItemData>();
    private String location_data;
    private Image bundle_image;
    private String seller_name;

    /**
     * Constructs a new BundleData according to parameters inputted in creation
     * @param name
     * @param discount
     * @param item
     * @param location
     * @param image
     * @param seller_name
     */
    public BundleData(String name, double discount, ItemData item, String location, Image image, String seller_name) {

        bundle_name = name;
        bundle_discount = discount;

        item_list = new ArrayList<ItemData>();
        item_list.add(item);

        location_data = location;
        bundle_image = image;


    }

    /**
     * Constructs a new (empty) BundleData with no parameters (everything String -> null and double -> zero)
     */
    public BundleData() {

        bundle_name = "";
        bundle_discount = 0;

        item_list = new ArrayList<ItemData>();

        location_data = "";
        // bundle_image = default image;


    }

    /**
     * Method adds a new item to the bundle
     * @param item
     */
    public void addItem(ItemData item) {

        item_list.add(item);
    }

    /**
     * Method removes an item from the bundle
     * @param item
     */
    public void removeItem(ItemData item) {

        item_list.remove(item);
    }

    /**
     * Method returns only the name of the bundle
     * @return
     */
    public String getBundleName() {
        return bundle_name;
    }

    /**
     * Method returns only the cost of the bundle
     * @return
     */
    public double getBundleCost() {

        double bundle_cost = 0.0;
        for (int i = 0; i < item_list.size(); i++) {
            bundle_cost += item_list.get(i).getItemCost();
        }

        bundle_cost = bundle_cost * bundle_discount;
        return bundle_cost;
    }

    /**
     * Method returns only the seller of the bundle
     * @return
     */
    public String getSeller() {
        return seller_name;
    }

    /**
     * Method returns only the location of the bundle
     * @return
     */
    public String getBundleLocation() {
        return location_data;
    }

    /**
     * Method returns only the image of the bundle
     * @return
     */
    public Image getBundleImage() {
        return bundle_image;
    }

    /**
     * Method sets the name of the bundle to the inputted parameter
     * @return
     */
    public void setBundleName(String name) {
        bundle_name = name;
    }

    /**
     * Method sets the seller of the bundle to the inputted parameter
     * @param seller
     */
    public void setSeller(String seller) {
        seller_name = seller;
    }

    /**
     * Method sets the location of the bundle to the inputted parameter
     * @param location
     */
    public void setLocation(String location) {
        location_data = location;
    }

    /**
     * Method sets the image of the bundle to the inputted parameter
     * @param image
     */
    public void setBundleImage(Image image) {
        bundle_image = image;
    }


}

