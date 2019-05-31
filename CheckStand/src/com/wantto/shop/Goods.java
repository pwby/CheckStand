package com.wantto.shop;

import java.util.Arrays;

/**
 * by:wby
 */
public class Goods {
    private String ID = null;
    private String name = null;
    private double price = 0.0;

    public Goods(String ID, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public Goods() {

    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /*
     * 通过商品编号获取商品信息
     * */
    public Goods getGoodInfo(String ID) {
        return new Goods(ID, this.name, this.price);
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", this.ID, this.name, this.price);
    }

}
