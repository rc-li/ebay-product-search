package com.example.ebaysearch;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;

public class Card implements Serializable {
    private String itemImgURL;
    private String itemTitle;
    private String itemCondition;
    private String isTopRated;
    private String shippingCost;
    private String price;
    private String itemID;
    private String item;
    private String viewItemURL;
    public boolean isEmpty;

    public Card(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Card() { }

    public Card(String itemImgURL, String itemTitle, String itemCondition, String isTopRated, String shippingCost, String price, String itemID) {
        this.itemImgURL = itemImgURL;
        this.itemTitle = itemTitle;
        this.itemCondition = itemCondition;
        this.isTopRated = isTopRated;
        this.shippingCost = shippingCost;
        this.price = price;
        this.itemID = itemID;
    }

    public String getViewItemURL() {
        return viewItemURL;
    }

    public void setViewItemURL(String viewItemURL) {
        this.viewItemURL = viewItemURL;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemImgURL() {
        return itemImgURL;
    }

    public void setItemImgURL(String itemImgURL) {
        this.itemImgURL = itemImgURL;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getIsTopRated() {
        return isTopRated;
    }

    public void setIsTopRated(String isTopRated) {
        this.isTopRated = isTopRated;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
