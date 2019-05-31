package com.wantto.shop;

import java.util.HashMap;
import java.util.Map;

/**
 * by:wby
 */
class Order {

    private static Map<String, Integer> order = new HashMap<>();
    private static SimpleGoodsCenter goodsmap = null;

    Order(SimpleGoodsCenter goodmap) {
        goodsmap = goodmap;
    }

    /*
     * 添加商品数量
     * */
    void add(String ID, Integer count) {
        Integer num = order.get(ID);
        if (num != null) {
            num += count;
        } else {
            num = count;
        }
        order.put(ID, num);
    }

    /*
     * 减少商品数量
     * */
    void rem(String ID, Integer count) {
        Integer num = order.get(ID);
        if (num != null) {
            num -= count;
            num = num > 0 ? num : 0;
        } else {
            num = 0;
        }
        order.replace(ID, num);
    }

    /*
     * 通过商品编号获取商品信息
     * */
    Goods getInfo(String ID) {
        return goodsmap.getInfo(ID);
    }

    /*
     * 订单信息
     * */
    String listInfo(String ID) {
        //商品名称 商品数量  商品单价  商品花费
        //商品信息
        Goods goodInfo = goodsmap.getInfo(ID);
        StringBuffer sb = new StringBuffer();
        sb.append(goodInfo.getName()).append("\t\t\t\t").append(order.get(ID)).append("\t\t\t").
                append(goodInfo.getPrice()).append("\t\t\t").append(order.get(ID) * goodInfo.getPrice()).append("\n");
        return sb.toString();
    }

    /*
     * 订单花费
     * */
    double getPrice(String ID) {
        Goods goodInfo = goodsmap.getInfo(ID);
        return order.get(ID) * goodInfo.getPrice();
    }


}
