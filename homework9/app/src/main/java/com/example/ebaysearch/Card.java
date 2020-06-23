package com.example.ebaysearch;

public class Card {
    private String itemImgURL;
    private String itemTitle;
    private String itemCondition;
    private String isTopRated;
    private String shippingCost;
    private String price;

    public Card() { }

    public Card(String itemImgURL, String itemTitle, String itemCondition, String isTopRated, String shippingCost, String price) {
        this.itemImgURL = itemImgURL;
        this.itemTitle = itemTitle;
        this.itemCondition = itemCondition;
        this.isTopRated = isTopRated;
        this.shippingCost = shippingCost;
        this.price = price;
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
