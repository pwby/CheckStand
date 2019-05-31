package com.wantto.shop;

import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * by:wby
 */
class SimpleOrderCenter {

    private static Map<String, Order> ordermap = new HashMap<>();
    static Integer orderId = 0;

    public SimpleOrderCenter(int orderId) {
        this.orderId = orderId;
    }

    /*
     * 保存订单编号
     * */
    public void idstore() throws Exception {
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "orderid.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        String str = String.valueOf(orderId);
        StringBuffer strs = new StringBuffer();
        //strs=strs.append(str+System.getProperty("line.separator"));
        strs.append(str).append("\r\n");
        out.write(strs.toString().getBytes());
        out.close();
    }

    /*
     * 加载订单编号
     * */
    public void idload() throws Exception {
        //定义文件路径
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "orderid.txt");
        if (file.exists()) {
            InputStream in = new FileInputStream(file);
            byte[] data = new byte[10];
            int len = in.read(data);
            orderId = Integer.parseInt(new String(data, 0, len).trim());
            in.close();
        }
    }

    /*
     * 添加订单
     * */
    public void add(String ID, Order order) {

        ordermap.put(ID, order);
    }

    /*
     * 取消订单
     * */
    public void cancel(String ID) {
        ordermap.remove(ID);
    }

    /*
     * 订单信息列表
     * */
    public String list() throws Exception {
        double totalPrice = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("订单信息              " + new SimpleDateFormat("yyyy-MM-dd hh:ss:mm").format(new Date()) + "\n");
        sb.append("==========================================\n");
        sb.append("商品名称").append("\t\t").append("商品数量").append("\t\t").append("商品单价").append("\t\t").
                append("商品消费").append("\n").append("==========================================").append("\n");
        Set<Map.Entry<String, Order>> set = ordermap.entrySet();
        Iterator<Map.Entry<String, Order>> iterator = set.iterator();
        totalPrice = getOrderInfo(totalPrice, sb, iterator);
        sb.append("总计消费：" + totalPrice + "\n");
        sb.append("==========================================\n");
        //订单编号有问题
        sb.append("打印编号:" + (++orderId) + "\n");

        return sb.toString();
    }

    //重构,因为list和findList只有orderID不同,所以把其他相同的重构
    public double getOrderInfo(double totalPrice, StringBuffer sb, Iterator<Map.Entry<String, Order>> iterator) {
        while (iterator.hasNext()) {
            Map.Entry<String, Order> entry = iterator.next();
            String ID = entry.getKey();
            Order order = entry.getValue();

            String info = order.listInfo(ID);
            double singlePrice = order.getPrice(ID);
            totalPrice += singlePrice;
            sb.append(info);
        }
        return totalPrice;
    }


    public String Findlist() throws Exception {
        double totalPrice = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("订单信息              " + new SimpleDateFormat("yyyy-MM-dd hh:ss:mm").format(new Date()) + "\n");
        sb.append("==========================================\n");
        sb.append("商品名称").append("\t\t").append("商品数量").append("\t\t").append("商品单价").append("\t\t").
                append("商品消费").append("\n").append("==========================================").append("\n");
        Set<Map.Entry<String, Order>> set = ordermap.entrySet();
        Iterator<Map.Entry<String, Order>> iterator = set.iterator();
        totalPrice = getOrderInfo(totalPrice, sb, iterator);
        sb.append("总计消费：" + totalPrice + "\n");
        sb.append("==========================================\n");
        sb.append("打印编号:" + (orderId) + "\n");

        return sb.toString();
    }
    /*
     * 加载以往订单查看功能
     * */
    public String OrdersLoad() throws Exception {
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "OrderMemory.txt");
        //   String str= null;
        String strs = null;

        if (file.exists()) {
            InputStream in = new FileInputStream(file);
//            Reader rd=new FileReader(file);
//            BufferedReader buf=new BufferedReader(rd);
//           while((str=buf.readLine())!=null){
//               strs=str+"\n";
//           }
            byte[] data = new byte[1024];
            int len = in.read(data);
            strs = new String(data, 0, len);
            in.close();
        }

        return strs.toString();

    }

    /*
     * 保存以往订单信息
     * */
    public void Ordersstore() throws Exception {
        //记录订单信息
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "OrderMemory.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        OutputStream out = new FileOutputStream(file, true);
        String str = this.Findlist() + "\r\n";
        out.write(str.getBytes());
        out.close();
    }
}
