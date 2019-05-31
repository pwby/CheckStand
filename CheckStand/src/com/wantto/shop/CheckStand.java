package com.wantto.shop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * by:wby
 */
public class CheckStand {
    private static Scanner scanner = new Scanner(System.in);
    //打印编号
    private static Integer orderId = 0;
    //开辟清单的空间,否则没有内存运行,加载会出现NPE
    private static SimpleGoodsCenter goods = new SimpleGoodsCenter();
    private static Goods good = new Goods();
    private static Order order = new Order(goods);
    private static SimpleOrderCenter orders = new SimpleOrderCenter(orderId);

    public static void menu() {
        System.out.println("      基于字符串界面的收银台    ");
        System.out.println("==============================");
        System.out.println("       [A]关于  [S]设置" +
                "\n" + "       [T]交互  [Q]退出");
        System.out.println("      使用 A S T Q进行操作");
        System.out.println("==============================");
    }

    public static void settingInfo() {

        System.out.println("       设置功能");
        System.out.println("==============================");
        System.out.println("   [U]上架  [D]下架  [F]修改" +
                "\n" + "   [L]浏览商品  [C]清除商品\n" + "   [R]返回上一级  " + "   [S]保存商品");
        System.out.println("  使用用U D F L C R S进行操作");
        System.out.println("==============================");
    }

    public static void useInfo() {
        System.out.println("        交互功能");
        System.out.println("==============================");
        System.out.println("[A]添加订单  [D]移除订单  [E]浏览订单" +
                "\n" + "[G]浏览商品  [S]保存订单\n" + "[L]查看所有订单信息   " + "[R]返回上一级  ");
        System.out.println("     使用A D E G S L R进行操作");
        System.out.println("==============================");
    }


    public static void about() {
        System.out.println("         关于功能");
        System.out.println("==============================");
        System.out.println("这是基于字符台界面的收银台系统V1.0 ");
        System.out.println("==============================");
        System.out.println("    使用任意键进行返回操作");

    }


    public static void quiz() {

        System.out.println("      退出功能");
        System.out.println("==============================");
        System.out.println("    离开本系统");
        System.out.println("     ByeBye");
        System.out.println("==============================");
        System.exit(0);
    }

    public static void use() {
        while (true) {
            String in = scanner.nextLine();
            switch (in.trim().toUpperCase()) {
                case "A": {
                    while (true) {
                        System.out.println("请输入下单商品编号 数量");
                        String[] strs = scanner.nextLine().split(" ");
                        if (goods.getInfo(strs[0]) != null) {
                            System.out.println("下单成功");
                            order.add(strs[0], Integer.parseInt(strs[1]));
                            orders.add(strs[0], order);
                            useInfo();
                            break;
                        } else {
                            System.out.println("输入有误,请正确输入!!!");

                        }
                    }
                    break;
                }
                case "D": {
                    System.out.println("请输入取消订单的商品编号");
                    String str = scanner.nextLine();
                    if (goods.getInfo(str) != null) {
                        orders.cancel(str);
                        System.out.println("订单取消成功");
                        useInfo();

                    } else {
                        System.out.println("订单不存在!!!");
                    }
                    break;
                }
                case "E": {

                    try {
                        orders.idload();
                        System.out.println(orders.list());
                        orders.idstore();
                        useInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "L": {

                    try {
                        System.out.println(orders.OrdersLoad());
                        useInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "G": {
                    System.out.println(goods.list());
                    useInfo();
                    break;
                }
                case "R":
                    break;
                case "S":
                    try {
                        orders.Ordersstore();
                        System.out.println("订单保存成功");
                        useInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    useInfo();
            }
            if (in.toUpperCase().trim().equals("R")) {
                menu();
                break;
            }
        }

    }

    public static void setting() {

        while (true) {
            String in = scanner.nextLine();
            switch (in.trim().toUpperCase()) {
                case "U": {
                    while (true) {
                        System.out.println("请按照格式输入上架商品信息(商品编号 商品名称 商品价格)");
                        String[] str = scanner.nextLine().split(" ");
                        if (str.length == 3) {
                            if (goods.exists(str[0])) {
                                System.out.println("商品已存在,不能重复上架!!!");
                            } else {
                                if (isHaveChinese(str[2])) {
                                    System.out.println("输入有误,价格应该为数值!!!");
                                } else {
                                    good = new Goods(str[0], str[1], Double.parseDouble(str[2]));
                                    goods.add(good);
                                    System.out.println("上架成功");
                                    settingInfo();
                                    break;
                                }
                            }
                        } else {
                            System.out.println("请重新输入上架商品信息");
                        }
                    }
                    break;
                }
                case "D": {
                    System.out.println("请输入下架商品商品编号");
                    String str = scanner.nextLine();
                    if (goods.getInfo(str) != null) {
                        goods.remove(str);
                        System.out.println("商品下架成功");
                        settingInfo();

                    } else {
                        System.out.println("下架商品编号不存在,无法下架!!!");
                    }
                    break;
                }
                case "F": {

                    System.out.println("请按照格式修改商品信息(商品编号 商品名称 商品价格)");
                    String[] str = scanner.nextLine().split(" ");
                    System.out.println("修改前的商品信息");
                    if (goods.getInfo(str[0]) != null) {
                        System.out.println(goods.getInfoList(str[0]));
                        System.out.println("修改后的商品信息");
                        goods.adjust(str[0], str[1], Double.parseDouble(str[2]));
                        System.out.println(goods.getInfoList(str[0]));
                        settingInfo();
                        break;
                    } else {
                        System.out.println("商品不存在,无法修改!!!");
                    }
                }
                case "L": {
                    System.out.println(goods.list());
                    settingInfo();
                    break;
                }
                case "C": {
                    goods.clear();
                    settingInfo();
                    break;
                }
                case "R":
                    break;
                case "S":
                    try {
                        goods.store();
                        settingInfo();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    settingInfo();


            }
            if (in.trim().toUpperCase().equals("R")) {
                menu();
                break;
            }


        }
    }

//   public  static void StoreAll() {
//        try {
//            orders.idstore();
//            orders.Ordersstore();
//            goods.store();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public static void main(String[] args) throws Exception {
        menu();
        goods.load();
        while (true) {
            String input = scanner.nextLine();
            switch (input.trim().toUpperCase()) {
                case "A":
                    about();
                    break;
                case "Q":
                    quiz();
                    break;
                case "T":
                    useInfo();
                    use();
                    break;
                case "S":
                    settingInfo();
                    setting();
                    break;
                default:
                    menu();

            }

        }

    }


    /*
     * 判断是否包含中文
     * */
    public static boolean isHaveChinese(String str) {
        boolean flag = true;
        int count = 0;
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count = count + 1;
            }
        }

        if (count == 0) {
            flag = false;
        }
        return flag;
    }

}


