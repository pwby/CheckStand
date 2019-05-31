# CheckStand
基于字符界面的收银台思路详解：

①把商品抽象为一个类商品，属性为ID，名称，价格

②开一个仓库，即商品中心SimpleGoodsCenter。向商品中心添加商品add（），移除商品remove（），修改商品adjust（），清空商品clear（），获取商品信息getInfo（String id），打印商品中心所有商品的信息列表（），打印某个ID号的商品信息getInfoList（ID）保存所有商品信息存储（），调取（加载）商品信息负载（），判断商品中心中该商品是否存
存在（） ，商品中心要新一个HashMap，用钥匙储存商品编号，用值储存商品信息

③把订单抽象为一个类订购，商品中心作为商店，从商店中获取商品编号和数量。添加某商品的数量add（），移除某商品的数量rem（），订单列表信息ListInfo（），获取商品信息的getInfo（），获取每个订单的价格用getPrice（），

④开一个仓库，管理所有订单，即订单管理中心SimpleOrderCenter（），添加订单add（），移除订单cancel（），保存订单信息Ordersstore（），调取订单信息Ordersload（），打印订单信息列表（），打印以往订单信息列表Findlist（），（list（）和FindList（）的不同之处在于变量订单编号是否自增），订单编号的保存idstore（），订单变好的加载idload（），订单中心要新HashMap的一个，用钥匙储存商品编号，用价值储存订单信息

⑤在主类中使用各种条件语句完成收银台功能
