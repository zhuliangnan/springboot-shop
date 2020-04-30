package com.springboot.springbootshop.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.springbootshop.mapper.AdminmessageMapper;
import com.springboot.springbootshop.model.*;

import com.springboot.springbootshop.service.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//thymleaf <a> 拼接<dd><a th:href="@{/get/{id}(id=${user.id})}">基本资料</a></dd>


@Controller
@Slf4j
public class AdminController {

    //文件上传路径
    public static final String dir = "D:/IDEAProjects/springboot-shop/src/main/resources/static/theme/img/phone/";

    //引入日志
    private static Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private AdminService adminService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private UserMessageService userMessageService;

    @Autowired
    private DetailService detailService;
    @Autowired
    private AdminmessageService adminmessageService;




    @GetMapping("/AdminLogin")
    public ModelAndView getLogin(HttpSession session, Model model) {
        logger.info("进入 admin的登陆界面");
        logger.error("看看演示");
        //判断需不需要自动登录
            //创建一个空的user对象 用来接收form表单的值
            ModelAndView mav = new ModelAndView("Admin_login");
            return mav;
    }

    @PostMapping("/AdminLoginPost")
    @ResponseBody
    public String postLogin(@RequestParam(value = "adminname", required = true) String adminname,
                            @RequestParam(value = "password", required = true) String password, Model model, HttpSession session){

        logger.info("管理员姓名 {}"+"管理员密码 {}",adminname,password);
        JSONObject result = new JSONObject();
        Admin admin = adminService.selectAdminByNameAndPassword(adminname,password);
        System.out.println("用户名 密码 "+admin.getName()+ admin.getPassword());
        if (admin==null){
            result.put("status", "fail");
            return result.toJSONString();
        }
        logger.info("数据库管理员姓名 {}"+"数据库管理员密码 {}",admin.getName(),admin.getPassword());
        result.put("status", "success");
        result.put("shopid", admin.getShopid().toString());

        session.setAttribute("adminDetail",admin);
        logger.info("获取到的adminDetail"+admin.toString());
        return result.toJSONString();
    }

    //admin_main  主菜单
    @GetMapping(value = "/AdminIndex")
    public String AdminOder( HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 Admin_index");

        Admin admin =new Admin();
        admin.setName("Admin1");
        admin.setPassword("123");
        admin.setShopid(1);
        session.setAttribute("adminDetail",admin);
        return "Admin_index";
    }
    //AdminGoodAll  获取所有商品
    @GetMapping(value = "/AdminGoodAll")
    public String AdminOrderAll( HttpSession session, Model model, HttpServletRequest request){

        logger.info("进入 Admin_index");

       //数据集合
        List<AdminShopsAndDetail> adminShopsAndDetailslist = new ArrayList<AdminShopsAndDetail>();
       //获取商品列表
        Admin admin = (Admin) session.getAttribute("adminDetail");
        logger.info("shopid 为 "+admin.getShopid());
        List<Goods> goodsList = new ArrayList<Goods>();
        goodsList = goodsService.getAllGoodsByshopid(admin.getShopid());
        for (Goods goods : goodsList) {
           int sum =0;
            List<Detail> details = detailService.selectDetailsByGid(goods.getGid());
            for (Detail detail : details) {
               sum += detail.getStock();
            }
            adminShopsAndDetailslist.add(new AdminShopsAndDetail(goods,sum));

        }
        model.addAttribute("adminShopsAndDetailslist", adminShopsAndDetailslist);

      /*  //封装数据
        Map map = new HashMap<String,Object>();
        map.put("goodsList", goodsList);
        map.put("stocklist", stocklist);
        model.addAttribute("mapList", map);*/
        logger.info("开始渲染页面");
        return "product_list";
    }
/*

    //AdminGoodDetail  获取商品详情
    @GetMapping(value = "/AdminGoodDetail/{gid}")
    public String AdminGoodDetail( @PathVariable("gid") Integer gid , HttpSession session, Model model, HttpServletRequest request){

        logger.info("进入 AdminGoodDetail");
        //获取商品列表
        Goods goodsdetail = goodsService.getGoodsByGid(gid);
        model.addAttribute("goodsdetail", goodsdetail);

        return "product_detail";
    }
*/

    //AdminGoodUpdate  更新商品 Get
    @GetMapping(value = "/AdminGoodUpdate/{gid}")
    public String AdminGoodUpdate(@PathVariable("gid") Integer gid ,HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminGoodUpdate");
        Goods goodsdetail = goodsService.getGoodsByGid(gid);
        List<Detail> details = detailService.selectDetailsByGid(gid);
        Detail detail = details.get(0);
        model.addAttribute("goods", goodsdetail);
        model.addAttribute("detail",detail);
        return "product_detail";
    }
    //AdminGoodUpdate  更新商品 Post
    @PostMapping (value = "/AdminGoodUpdate")
    @ResponseBody
    public String AdminGoodUpdatePost(@RequestParam("file")MultipartFile file,@RequestParam("goodsname")String goodsname,
                                      @RequestParam("stock")String stock,@RequestParam("goodstate")String goodstate,
                                      @RequestParam("secondclassify")String  secondclassify,
                                      @RequestParam("content")String  content, @RequestParam("price")String  price,
                                      @RequestParam("color")String  color,
                                      @RequestParam("model")String  model,@RequestParam("gid")String  gid){

        JSONObject result = new JSONObject();
        result.put("status", "success");

        logger.info("进入 AdminGoodAdd获取到的file"+file.getOriginalFilename()+
                "goodsname:"+goodsname+"stock:"+stock+"secondclassify:"+secondclassify+
                "content:"+content+"price:"+price+"color:"+color+"model:"+model+"gid:"+gid);
        //删除本地图片
        File folder = new File(dir);
        File[] files = folder.listFiles();
        for(File file1:files) {
            if (file1.getName().equals(goodsService.getGoodsByGid(Integer.parseInt(gid)).getPicture1())) {
                file1.delete();
                logger.info(" 删除成功");
            }
        }
        //增加 图片到本地
        String fileName = file.getOriginalFilename();
        File dest = new File(dir + fileName);
        try {
            file.transferTo(dest);
            logger.info(" 上传成功"+fileName);

        } catch (IOException e) {
            logger.info("上传s失败");
        }
        //截取.之前的字符串 作为pcture名字  请保证是jpg格式  没有做图片格式转换
        String picturename = fileName.substring(0,fileName.indexOf("."));
        logger.info("pcturename "+ picturename);





        //封装 商品
        Goods goods = new Goods();

        if (goodstate.equals("未上架")){
            goods.setGoodstate(0);
        }else{
            goods.setGoodstate(1);
        }
        //注意要加上更新的gid
        goods.setGid(Integer.parseInt(gid));
        goods.setPicture1(picturename);
        goods.setGoodsname(goodsname);
        goods.setDescribe(content);
        goods.setSecondclassify(secondclassify);
        goods.setShopid(1);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        goods.setAddTime(dateFormat.format(date));

        logger.info("要更新数据库的数据:"+goods.toString());
        int i = goodsService.updateByPrimaryKeySelective(goods);
        //封装商品详情 需要封装的有 gid
        if(i>0){

            Detail detail = new Detail();
            detail.setGid(Integer.parseInt(gid));
            model = "全网通" + model;
            detail.setModel(model);
            detail.setColor(color);
            detail.setPrice(Double.parseDouble(price));
            detail.setStock(Integer.parseInt(stock));

            try {
                int q = detailService.updateByColorAndModelAndGidSelective(detail);
            } catch (Exception e) {

                logger.info("没有相关款式不进行更新detail");
                return result.toJSONString();
            }

            logger.info("detail更新成功"+detail.toString());

        }
        //至此数据全部更新入goods 和  detail中

        System.out.println("//至此数据全部填入goods 和  detail中");

        return result.toJSONString();
    }

    @RequestMapping("/ThymeleafRefresh") //用于局部数据刷新 参照 ajax
    public  Object  ajaxInput(HttpSession session, Model model2, HttpServletRequest request){

        System.out.println("+++++++++++++ThymeleafRefreshB++++v+++++++++++++");
        //获取ajax传过来的值
        String model=request.getParameter("model");
        String color=request.getParameter("color");
        String gid=request.getParameter("gid");
        System.out.println("获取到了model3 " +model+"获取到了color3"+ color+"获取到了gid  "+gid);

        Goods goods = goodsService.getGoodsByGid(Integer.parseInt(gid));
        Detail detail = new Detail();
        detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
        if(detail == null){
            //实际上
            List<Detail> detailList = detailService.selectDetailsByGid(Integer.parseInt(gid));
            detail = detailList.get(0);
            model2.addAttribute("detail", detail);
        }else{
            System.out.println("----------");
            model2.addAttribute("detail", detail);
        }

        model2.addAttribute("goods", goods);

        return "product_detail::detail_refresh";

    }

    //AdminGoodAdd  添加商品 Get
    @GetMapping(value = "/AdminGoodAdd")
    public String AdminGoodAdd(HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminGoodAdd");
        return "product_add";
    }
    //AdminGoodAdde  添加商品 Post
    @PostMapping (value = "/AdminGoodAdd")
    @ResponseBody
    public String AdminGoodAdd(@RequestParam("file")MultipartFile file,@RequestParam("goodsname")String goodsname,
                               @RequestParam("stock")String stock,@RequestParam("goodstate")String goodstate,
                               @RequestParam("secondclassify")String  secondclassify,
                               @RequestParam("content")String  content, @RequestParam("price")String  price,
                               @RequestParam("color")String  color,
                               @RequestParam("model")String  model){
        logger.info("进入 AdminGoodAdd获取到的file"+file.getOriginalFilename()+
                "goodsname:"+goodsname+"stock:"+stock+"secondclassify:"+secondclassify+
                "content:"+content+"price:"+price+"color:"+color+"model:"+model);
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(dir + fileName);
        try {
            file.transferTo(dest);
            logger.info("上传成功"+fileName);

        } catch (IOException e) {
            logger.info("上传s失败");
        }

        //截取.之前的字符串 作为pcture名字  请保证是jpg格式  没有做图片格式转换
        String picturename = fileName.substring(0,fileName.indexOf("."));
        logger.info("pcturename "+ picturename);

        //封装 商品
        Goods goods = new Goods();
        goods.setGoodsname(goodsname);
        goods.setDescribe(content);
        goods.setPicture1(picturename);
        goods.setShopid(1);
        goods.setSecondclassify(secondclassify);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        goods.setAddTime(dateFormat.format(date));

        if (goodstate.equals("未上架")){
            goods.setGoodstate(0);
        }else{
            goods.setGoodstate(1);
        }
        logger.info("要加入数据库的数据:"+goods.toString());
        int i = goodsService.insertSelective(goods);
        //封装商品详情 需要封装的有 gid
        if(i>0){


           Detail detail = new Detail();
           detail.setGid(goodsService.getGoodsByGoodsname(goodsname).getGid());
           detail.setColor(color);
           model = "全网通" + model;
           detail.setModel(model);
           detail.setPrice(Double.parseDouble(price));
           detail.setStock(Integer.parseInt(stock));

          int q = detailService.insertSelective(detail);

        }
        //至此数据全部填入goods 和  detail中

        System.out.println("//至此数据全部填入goods 和  detail中");
        JSONObject result = new JSONObject();
        result.put("status", "success");
        return result.toJSONString();

    }


    //AdminGoodDelete  删除商品
    @GetMapping(value = "/AdminGoodDelete/{gid}")
    public String AdminOrderAll(  @PathVariable("gid") Integer gid ,HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminGoodDelete"+gid);
        //获删除品列表
        int i = goodsService.deleteByPrimaryKey(gid);
        //便利删除 detail表
        detailService.deleteBygid(gid);
        return "redirect:/AdminGoodAll";
    }


    //---------------------------------------------------------------订单操作 -------------------------------------------------

    //AdminOrderAll  获取所有订单
    @GetMapping(value = "/AdminOrderAll")
    public String AdminGoodAll( HttpSession session, Model model, HttpServletRequest request){

        logger.info("进入 AdminOrderAll");
        //获取管理员
        Admin admin = (Admin) session.getAttribute("adminDetail");
        List<Orders> ordersList = new ArrayList<Orders>();
        ordersList = ordersService.getAllOrdersByShopid(admin.getShopid());

        //遍历
        for (Orders orders : ordersList) {
            System.out.println("4444444***"+orders.toString());
        }
        model.addAttribute("ordersList", ordersList);
        return "order_list";
    }

    //AdminOrderUpdate  更新订单 Get
    @GetMapping(value = "/AdminOrderUpdate/{oid}")
    public String AdminOrderUpdate(@PathVariable("oid") Integer oid ,HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminOrderUpdateGet");
        Orders orders = ordersService.selectByPrimaryKey(oid);

        //实例化
        Usermessage usermessage = new Usermessage();
        usermessage.setDetails("帮我检查好呀~谢谢~");
        if(userMessageService.selectByOid(oid) == null){

        }else{
            usermessage =  userMessageService.selectByOid(oid);
        }

        model.addAttribute("orders", orders);
        model.addAttribute("usermessage", usermessage);
        return "order_detail";
    }
    //AdminOrderUpdate  更新订单 Post
    @PostMapping (value = "/AdminOrderUpdate")
    public String AdminOrderUpdate(@RequestBody Orders orders){
        logger.info("进入 AdminOrderUpdatePost");

        int i = ordersService.updateByPrimaryKeySelective(orders);

        return "redirect:/AdminOrderAll";
    }

    //AdminOrderDelete  删除订单
    @GetMapping(value = "/AdminOrderDelete/{oid}")
    public String AdminOrderDelete(  @PathVariable("oid") Integer oid ,HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminOrderDelete");
        int i = ordersService.deleteByPrimaryKey(oid);
        return "redirect:/AdminOrderAll";
    }

    /*
     * 备注 state
     * 0:未付款 1:已付款未发货 2:已付款已发货 3:已付款已签收
     * 售后
     * 4001 换货 4002 退货退款 4003订单完成
     * */
    //AdminOrderSelect  查询订单  组合条件查询
    @GetMapping(value = "/AdminOrderSelect/{state}/{addname}")
    public String AdminOrderSelect(@PathVariable(value = "state",required = false) String state ,
                                   @PathVariable(value = "addname",required = false) String addname ,
                                   HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminOrderSelect+ "+state+addname);
     /*   if((state.equals("")&&state==null)&&(addname.equals("")&&addname==null)){
            return "redirect:/AdminOrderAll";
        }*/
        int sate1=-1;
        switch (state){
            case "未付款": sate1=0;
                break;
            case "已付款未发货": sate1=1;
                break;
            case "已付款已发货": sate1=2;
                break;
            case "已付款已签收": sate1=3;
                break;
            case "换货": sate1=4001;
                break;
            case "退货退款": sate1=4002;
                break;
            case "订单完成": sate1=4003;
                break;
            case "订单状态": sate1=-1;
                break;
        }

        if (sate1 == -1 && addname!=null && !addname.equals("全查")){
            //按照名字查找
            logger.info("按照名字查找");
            List<Orders> ordersList = ordersService.selectByUsername(addname);
            model.addAttribute("ordersList", ordersList);

            return "order_list";
        }
        if(sate1 != -1 && addname.equals("全查")){
            //按照状态查找
            logger.info("按照状态查找");
            List<Orders> ordersList = ordersService.selectByState(sate1);
            model.addAttribute("ordersList", ordersList);
            return "order_list";
        }
        if(sate1 == -1 && addname.equals("全查")){
            //全查找
            //获取管理员
            logger.info("全查找");
            Admin admin = (Admin) session.getAttribute("adminDetail");
            List<Orders> ordersList = new ArrayList<Orders>();
            ordersList = ordersService.getAllOrdersByShopid(admin.getShopid());

            model.addAttribute("ordersList", ordersList);

            return "order_list";
        }else {
            //按照状态和姓名查找
            logger.info("按照状态和姓名查找");

            List<Orders> ordersList = ordersService.selectByStateAndUsername(sate1, addname);

            model.addAttribute("ordersList", ordersList);

            return "order_list";
        }

    }



    // Admin  的操作  只对于 0  1  3  4001 4002  有处理
    @GetMapping(value = "/AdminOperation/{oid}/{state}/{detail}/{address}")
    public String AdminOperation(@PathVariable(value = "oid",required = false) String oid ,
                                 @PathVariable(value = "state",required = false) String state ,
                                 @PathVariable(value = "detail",required = false) String detail,
                                 @PathVariable(value = "address",required = false) String address,
                                   HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminOperation+ "+state+oid+detail+address);

        Orders r = new Orders();
        r.setOid(Integer.parseInt(oid));
        r.setState(Integer.parseInt(state));
        if (!address.equals("默认地址")){
            r.setAddress(address);
        }
        //要改成已发货需要添加发货时间
        if(state.equals("2")){
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            r.setSendTime1(dateFormat.format(date));

        }
        ordersService.updateByPrimaryKeySelective(r);
        //ordersService.updateOrder(Integer.parseInt(oid),Integer.parseInt(state));

        //detail 存入adminmessage
        Orders orders = ordersService.selectByPrimaryKey(Integer.parseInt(oid));

        Adminmessage adminmessage = new Adminmessage();

        adminmessage.setDetails(detail);
        adminmessage.setOid(Integer.parseInt(oid));
        adminmessage.setAddress(orders.getAddress());
        adminmessage.setcTime(orders.getUpTime());
        adminmessage.setGoodsname(orders.getGoodsname());
        adminmessage.setPhone(orders.getPhone());
        adminmessage.setNickname(orders.getAddname());
        adminmessage.setState(orders.getState());

       int i =  adminmessageService.insertSelective(adminmessage);

        return "redirect:/AdminOrderAll";
    }

    //---------------------------------------------------------------用户信息操作 -------------------------------------------------

    //AdminUserMessageAll  用户反馈消息
    @GetMapping(value = "/AdminUserMessageAll")
    public String AdminUserMessageAll( HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminUserMessageAll");
        List<Usermessage> allUsermessagen = userMessageService.getAllUsermessagen();



        model.addAttribute("allUsermessagen", allUsermessagen);
        System.out.println(allUsermessagen.toString());
        return "user_list";
    }

    @GetMapping(value = "/AdminUserMessageDelete/{mid}")
    public String AdminUserMessageDelete(@PathVariable(value = "mid",required = false) Integer mid ,HttpSession session, Model model, HttpServletRequest request){
        logger.info("进入 AdminUserMessageDelete");
        userMessageService.deleteByPrimaryKey(mid);
        return "redirect:/AdminUserMessageAll";
    }

    //---------------------------------------------------------------信息统计操作 -------------------------------------------------

    //AdminSumSales  总销售额统计 ---- 柱形图
    @GetMapping(value = "/AdminSumSales")
    public String AdminSumSales( HttpSession session, Model model, HttpServletRequest request){

        return "sales_volume";
    }
    //AdminSumFlow  总流量统计 ---折线图
    @GetMapping(value = "/AdminSumFlow")
    public String AdminSumFlow( HttpSession session, Model model, HttpServletRequest request){

        List<Map<String, String>> list = new ArrayList<>();
        //模拟数据
        //生成28个随机数 代表人流量
        List<Integer> list1 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {

            Map<String, String> inputParams = new HashMap<String, String>();
            inputParams.put("name", ""+(i+1));
            inputParams.put("value", String.valueOf(random.nextInt(10000)));
            list.add(inputParams);
        }

        String resultJson = JSON.toJSONString(list);

        System.out.println(resultJson);

        model.addAttribute("ResultJson",resultJson);
        /**
         * list集合封装json数组格式
         *
         * 返回格式: list[{"mchBizType":"DE","mchNo":"1001772750"}]
         */
        //list[{"mchBizType":"DE","mchNo":"1001772750"}]
     /*   JSONObject json = new JSONObject(list);
        System.out.println(json.toString());*/
        return "discharge_statistic";
    }

    //AdminSumBrand  品牌销售额统计 --饼图
    @GetMapping(value = "/AdminSumBrand")
    public String AdminSumBrand( HttpSession session, Model model, HttpServletRequest request){

        List<Goods> GoodsList = goodsService.getAllGoodsBySecondClassify("华为");
        int sum_huawei = 0;
        for (Goods goods : GoodsList) {
            sum_huawei += goods.getSales();
        }

        List<Goods> GoodsList1 = goodsService.getAllGoodsBySecondClassify("小米");
        int sum_xiaomi = 0;
        for (Goods goods : GoodsList1) {
            sum_xiaomi += goods.getSales();
        }
        List<Goods> GoodsList2 = goodsService.getAllGoodsBySecondClassify("魅族");
        int sum_meizu = 0;
        for (Goods goods : GoodsList2) {
            sum_meizu += goods.getSales();
        }
        List<Goods> GoodsList3 = goodsService.getAllGoodsBySecondClassify("三星");
        int sum_sanxing = 0;
        for (Goods goods : GoodsList3) {
            sum_sanxing += goods.getSales();
        }

        model.addAttribute("huawei",sum_huawei);
        model.addAttribute("xiaomi",sum_xiaomi);
        model.addAttribute("meizu",sum_meizu);
        model.addAttribute("sanxing",sum_sanxing);

        return "sales_volume_ByBrand";
    }



    @GetMapping("/Admin/getadmin")

    public List<Admin> admin(){
        //我们用多线程的方法来测试一下这个方法应付高并发的能力

        //new一个线程 查询所有admin
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                adminService.getAllAdmin();

            }
        };

        //这里我们new一个线程池 25个线程
        ExecutorService executorService=Executors.newFixedThreadPool(25);
        for (int i=0;i<10000;i++){//有10000个人进来
            executorService.submit(runnable); //执行10000次线程
        }


     return adminService.getAllAdmin();
    }

    /*
    //用来测试springboot的事物机制
    运行报错 by zero 说明出现了分母为0的异常 触发事物回滚  update的操作回滚不对数据库进行更改
     @Transactional 添加在对数据库进行操作的 AdminServiceImpl 中
    */
    @GetMapping("/Admin/updateadmin")
    @ResponseBody
    public void uopdateAdmin(){
        adminService.update();

    }

     /*
    用来测试 RESTFULL 一种理念 叫Rest风格
    使用@PathVariable 注解就可以实现
    他可以获取到url中数据 他作为RESTFULL最主要的注解 
    比如要访问一个http接口 http://localhost:8080/boot/api/order?id=15&status=2
    采用RESTFULL的http地址为  http://localhost:8080/boot/api/order/15/2

    比如要访问 http://localhost:8080/springboot-web/Admin/RESTFULL/10
    @PathVariable("id")Integer id 就可以把10拿到id上面来

    当然你也可以多加几个
    @GetMapping("/Admin/RESTFULL/{id}/{name}")
    @PathVariable("id")Integer id,@PathVariable("name")String name

     */
     @GetMapping("/Admin/RESTFULL/{id}")
     @ResponseBody
     public Admin testRESTFULL(@PathVariable("id")Integer id){
         Admin a=new Admin();
         a.setAid(id);
         a.setName("admin1");
         a.setPassword("123456");
         return  a;

     }





}
