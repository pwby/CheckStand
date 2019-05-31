package com.wantto.shop;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * by:wby
 */
class SimpleGoodsCenter {
    private static Map<String, Goods> goodmap = new HashMap<>();

    /*
     * 添加商品
     * */
    public void add(Goods good) {
        goodmap.put(good.getID(), good);
    }

    /*
     * 移除商品
     * */
    public void remove(String ID) {
        goodmap.remove(ID);
    }

    /*
     * 修改商品
     * */
    public void adjust(String ID, String name, double price) {
        //写的不好!!!
        Goods newgood = goodmap.get(ID);
        newgood.setName(name);
        newgood.setPrice(price);
        goodmap.replace(ID, newgood);

    }

    /*
     * 商品信息列表
     * */
    public String list() {
        StringBuffer sb = new StringBuffer();
        sb.append("   商品清单\n").append("编号\t\t" + "名称\t\t" + "价格" + "\n");
        for (Goods good : goodmap.values()) {
            String[] str = good.toString().split(" ");
            String ID = str[0];
            String name = str[1];
            double price = Double.parseDouble(str[2]);
            sb.append(ID).append("\t\t").append(name).append("\t\t").append(price).append("\n");
        }
        return sb.toString();
    }

    /*
     * 清除商品
     * */
    public void clear() {
//         //想要删除文件中的所有商品信息
//        File file = new File("E:" + File.separator + "GOODList" + File.separator + "Goodlist.txt");
//            if(file.exists()&&file!=null){
//                file.delete();
        goodmap.clear();

    }


    /*
     * 保存商品
     * */
    public void store() throws Exception {
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "Goodlist.txt");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = null;
        for (Goods goods : goodmap.values()) {
            String str = goods.toString();
            // StringBuffer strs=new StringBuffer();
            // strs.append(str+"\r\n");换行
            //换行
            String strs = str + System.getProperty("line.separator");
            //如果没有true,现在的信息会覆盖之前的信息
            out = new FileOutputStream(file, true);
            out.write(strs.getBytes());
        }
        //如果没有商品,out为空,所以加以判断

        out.close();

    }

    /*
     * 加载商品信息
     * */
    public void load() throws Exception {
        //定义文件路径
        File file = new File("E:" + File.separator + "GOODList" + File.separator + "Goodlist.txt");
        //定义输入流:一滴一滴的流
        if (file.exists()) {
            Reader rd = new FileReader(file);
            //定义有缓冲的输入流,可以使用一行一行的读取,相当于一个管道
            BufferedReader buf = new BufferedReader(rd);
            String str = null;
            //一行一行的读取
            while ((str = buf.readLine()) != null) {
                String[] res = str.split(" ");
                Goods good = new Goods(res[0], res[1], Double.parseDouble(res[2]));
                goodmap.put(good.getID(), good);

            }
            buf.close();
        }

    }

    /*
     *判断商品是否存在
     * */
    public boolean exists(String ID) {
        return goodmap.containsKey(ID);

    }

    /*
     * 通过商品编号获取商品信息
     * */
    public Goods getInfo(String ID) {
        return goodmap.get(ID);
    }

    /*
     * 通过商品编号获取单个商品信息
     * */
    public String getInfoList(String ID) {
        StringBuffer sb = new StringBuffer();
        sb.append("编号\t\t" + "名称\t\t" + "价格" + "\n");
        Goods good = goodmap.get(ID);
        String[] str = good.toString().split(" ");
        String iD = str[0];
        String name = str[1];
        double price = Double.parseDouble(str[2]);
        sb.append(iD).append("\t\t").append(name).append("\t\t").append(price).append("\n");
        return sb.toString();
    }

}
