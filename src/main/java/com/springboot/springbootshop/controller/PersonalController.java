package com.springboot.springbootshop.controller;

import com.springboot.springbootshop.model.*;
import com.springboot.springbootshop.service.*;
import com.springboot.springbootshop.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 描述该类为个人中心的controller 结合 UserController
 *     /*
 *         * 备注 state
 *         * 0:未付款 1:已付款未发货 2:已付款 已发货 3:已付款已签收 4:售后
 *         * 4001 换货 4002 退货退款 4003 订单已经完成
 *
 *         由订单状态1 到 2 应当由后台进行确认 并更改
 *         * */

@Controller
public class PersonalController {
    @Autowired
    private PersonalService personalService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private UserMessageService userMessageService;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CommentService commentService;



    //测试demo页面
    @GetMapping("/DivTest")
    public String DivTest(HttpSession session, Model model, HttpServletRequest request){

        return "Div";
    }

    //进入个人中心
    @GetMapping("/PersonalCenter")
    public String main(HttpSession session, Model model, HttpServletRequest request){

        //获取当前用户
        model.addAttribute("existUser", session.getAttribute("existUser"));
        //获取购物车
        getCarList(session, model);
        return "my-user";
    }

   //一个私有的随时获取购物车的方法
    private void getCarList(HttpSession session, Model model) {

        List<Cart> cartList=new ArrayList<Cart>();
        if(session.getAttribute("existUser")==null){
            System.out.println("用户没有登录");
        }else{
            cartList = cartService.showAllCart(((User) session.getAttribute("existUser")).getUid());

        }
        model.addAttribute("cartList", cartList);

        model.addAttribute("cartListSize", cartList.size());
    }

    //修改密码Get
    @GetMapping(value = "/Uppsd/{uid}")
    public String updatePassword(@PathVariable("uid") Integer uid,HttpSession session, Model model, HttpServletRequest request) {
        User u = personalService.getUserByUid(uid);
        System.out.println(u.toString());
        model.addAttribute("uid",uid);
        model.addAttribute("user",u);
      /*  view.addObject("u", u);*/
        return "uppsd";
    }
    //修改密码Post
    @PostMapping(value = "/Uppsd/{uid}")
    public String updatePassword2(@PathVariable("uid") Integer uid,@ModelAttribute User user,HttpSession session, Model model) {
        /*ModelAndView view = new ModelAndView("uppsd");*/
        System.out.println("进入PostMappingUppsd");
        System.out.println("获取到的uid    "+uid);
        System.out.println("获取到的用户 "+user.toString());
        user.setPassword(MD5.string2MD5(user.getPassword()));
        userService.updateByPrimaryKeySelective(user);
        System.out.println("用户添加成功了");
        //获取购物车
        getCarList(session, model);
        return "my-user";
    }
    //验证旧密码
    @PostMapping("/VerificationOldPassword")
    @ResponseBody
    public String sendMail(String oldpassword, String action, HttpSession session, Model model, HttpServletRequest request) {

        System.out.println("获取到的旧密码"+oldpassword);
        String oldpassword1= MD5.string2MD5(oldpassword);
        System.out.println("输出一下加密好的旧密码"+oldpassword1);
        User u=userService.selectUserByNameAndPassword(((User)session.getAttribute("existUser")).getNickname(),oldpassword1);
        if(u!=null){
            return "1111";
        }
        return  "2222";

    }



    //对于用户密保验证
    @PostMapping("/VerificationPasswordProtect")
    @ResponseBody
    public String sendMail(String action, HttpSession session, Model model, HttpServletRequest request) {

        User u=personalService.getUserByUid( ((User)session.getAttribute("existUser")).getUid());
        if((u.getQuestion().equals(""))&&(u.getAnswer().equals(""))){

            return "1111";
         }
        System.out.println("用户的密保找到了"+u.getAnswer()+u.getQuestion());
        return "2222";

    }
    //添加密保问题get
    @GetMapping(value = "/UpQuestion/{uid}")
    public String upQuestion(@PathVariable("uid") Integer uid, HttpServletRequest request,Model model) {
        System.out.println("进入GetUpQuestion");
        System.out.println("获取到的uid    "+uid);
        User u = personalService.getUserByUid(uid);
        System.out.println(u.toString());
        model.addAttribute("uid",uid);
        model.addAttribute("user",u);
        return "setquestion";
    }
    //添加密保问题post
    @PostMapping(value = "/UpQuestion/{uid}")
    public String upQuestion2(@PathVariable("uid") Integer uid,@ModelAttribute User user,HttpSession session, Model model) {
        /*ModelAndView view = new ModelAndView("uppsd");*/
        System.out.println("进入PostUpQuestion");
        System.out.println("获取到的用户 "+user.toString()); //这个用户只封装了user所有的信息 因为在上一个Get里面已经把user填充完毕
        System.out.println("获取到的uid    "+uid);
        userService.updateByPrimaryKeySelective(user);
        System.out.println("用户的密保添加成功了");
        //获取购物车
        getCarList(session, model);
        return "my-user";
    }

    //获取订单Get
    @GetMapping(value = "/OrdersManagement")
    public String ShowAllOrders(HttpSession session, Model model, HttpServletRequest request) {
        User u=(User)session.getAttribute("existUser");
        int uid=u.getUid();

        List<Orders> OrderList= new ArrayList<Orders>();
        OrderList = ordersService.getAllOrdersByUid(uid);
        System.out.println("没有问题");
        model.addAttribute("OrderList",OrderList);
        //获取购物车
        getCarList(session, model);
        return "my-d";
    }


    //未支付订单去付款
    @GetMapping  (value = "/PayOrder/{oid}")
    public String payorder(@PathVariable("oid") Integer oid, HttpSession session, Model model) {
        System.out.println("PayOrder");
        System.out.println("获取到的oid    "+oid);
        //获取到当前需要去重新支付的订单
        List<Orders> orderList=new ArrayList<Orders>();
        orderList.add(ordersService.selectByPrimaryKey(oid));
        //一定要加啊
        session.setAttribute("OrderList",orderList);


        //虽然这个订单只有一个但是我还是把它处理成list
        //下面开始准备d-success的数据
        Orders order=orderList.get(0);
        double sumprice=0.0;
        sumprice=sumprice+order.getNumber()*order.getPrice();

        Random r = new Random();
        int randnum=r.nextInt(99999)+100000;
        model.addAttribute("ordersid",randnum+order.getOid());

        String phoneNumber = order.getPhone().substring(0, 3) + "****" + order.getPhone().substring(7, order.getPhone().length());
        model.addAttribute("phone",phoneNumber);
        model.addAttribute("OrderList",orderList);
        model.addAttribute("name",order.getAddname());
        model.addAttribute("sumprice",sumprice+8);
        model.addAttribute("address",order.getAddress());

        return "d-success";

    }

    //对于已经付了钱却没有发货的的提醒管理员发货
    @GetMapping(value = "/RemainOrder/{oid}")
    public String RemainOrder(@PathVariable("oid") Integer oid, HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException{

        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();

        User u= (User) session.getAttribute("existUser");
        Orders order=ordersService.selectByPrimaryKey(oid);
        Usermessage usermessage=new Usermessage();
        usermessage.setOid(oid);
        usermessage.setNickname(u.getNickname());
        usermessage.setGoodsname(order.getGoodsname());
        usermessage.setAddress(order.getAddress());
        usermessage.setPhone(order.getPhone());
        usermessage.setDetails("我已经付款了,请尽快安排发货,谢谢!");
        usermessage.setState(order.getState());
        //消息生成时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        usermessage.setSendTime(dateFormat.format(date));
        userMessageService.insertSelective(usermessage);


        out.flush();
        out.println("<script>");
        out.println("alert('您的消息已经送达,管理员会尽快安排发货.');");
        out.println("history.back();");
        out.println("</script>");

        return "my-d";
    }


    //订单详情信息
    @GetMapping(value = "/OrdersDetail/{oid}")
    public String OrdersDetail(@PathVariable("oid") Integer oid, HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException{

        //生成订单编号
        Orders order=ordersService.selectByPrimaryKey(oid);
        Random r = new Random();
        int randnum=r.nextInt(99999)+100000+order.getOid();
        model.addAttribute("ordersid",randnum+order.getOid());
        //session.setAttribute("Order",order); 如果使用了session 前台取值就得加上session
        model.addAttribute("Order",order);
        System.out.println(order.toString());
        model.addAttribute("SumPrice",order.getPrice()*order.getNumber()+8);

        return "my-d-info";
    }


    //物流信息显示完毕 现在默认已经签收开始更改订单状态为3:已付款已签收
    @GetMapping(value = "/OrdersStateChange/{oid}")
    public String OrdersStateChange(@PathVariable("oid") Integer oid, HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException{


        //如果 3已经签收 4001 换货 4002  退货退款 4003订单完成 就不进行改动
        int state=ordersService.selectByPrimaryKey(oid).getState();
        if((state==4001)||(state==4002)||(state==3)||(state==4003)){
            return "redirect:/OrdersDetail/"+oid;
        }else {
            //如果是2就修改签收时间
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            Orders orders=new Orders();
            orders.setOid(oid); //请注意 updateByPrimaryKeySelective是根据oid进行更新的没有把oid写入就一定更新不来
            orders.setState(3);
            orders.setSendTime2(dateFormat.format(date));

            //更改订单状态和签收时间  注意发货时间由后台确认所有此时sendtime1是没有数据的
            ordersService.updateByPrimaryKeySelective(orders);
            //页面重定向
            return "redirect:/OrdersDetail/"+oid;
        }

    }

    //封装订单信息进入评价页面
    @GetMapping(value = "/CommentOrder/{oid}")
    public String CommentOrder(@PathVariable("oid") Integer oid, HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException{
        //生成订单编号
        Orders order=ordersService.selectByPrimaryKey(oid);
        model.addAttribute("Order",order);

        return "my-p";

    }
    //封装订单信息进入评价页面
    @PostMapping(value = "/CommentOrder/{comment}/{oid}")
    @ResponseBody
    public String CommentOrder(@PathVariable("comment") String comment,@PathVariable("oid") Integer oid, HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException, ParseException {
        //生成订单编号
       String score= request.getParameter("score");
       System.out.println("获取到的oid "+oid+"评价信息 "+comment+"分数 "+score);
       //开始获取商品号
        Goods goods=goodsService.getGoodsByGoodsname(ordersService.selectByPrimaryKey(oid).getGoodsname());
        System.out.println("goods商品 "+goods.toString());
       //开始封装对象 comment1 是对象别和comment混淆
        Comment comment1=new Comment();
        comment1.setComment(comment);
        comment1.setGid(goods.getGid());
        comment1.setPicture(goods.getPicture1());
        comment1.setState(Integer.parseInt(score));//这个是分数啊...我写数据库命名写错了
        //注意Date没有线程安全性
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        comment1.setUpDate(dateFormat.format(date));
        System.out.println("comment1 "+comment1.toString());
        //插入数据库
        int insert = commentService.insert(comment1);
        if(insert>0){
            //插入成功 开始修改订单状态
            Orders orders=new Orders();
            orders.setOid(oid);
            orders.setState(4003);
            ordersService.updateByPrimaryKeySelective(orders);
            return "1111";

        }else{
            //插入失败
            return "2222";
        }

    }

    //售后信息更改
    @GetMapping(value = "/AfterSale")
    @ResponseBody
    public String AfterSale(HttpSession session, HttpServletResponse response,Model model, HttpServletRequest request) throws IOException{

        String oid=(String)request.getParameter("oid");
        //获取售后类型  4001 换货 4002 退货退款
        String opt=(String)request.getParameter("opt");
        String textareaproblem1=(String)request.getParameter("textareaproblem1");
        System.out.println("获取到的"+oid+opt+textareaproblem1);
        //生成订单
        Orders order=ordersService.selectByPrimaryKey(Integer.parseInt(oid));
        model.addAttribute("Order",order);
        //修改信息
        if(opt.equals("退货申请")){
            ordersService.updateOrder(Integer.parseInt(oid),4002);
        }else if(opt.equals("换货申请")){
            ordersService.updateOrder(Integer.parseInt(oid),4001);
        }
        //封装用户信息
        User u= (User) session.getAttribute("existUser");
        Orders order1=ordersService.selectByPrimaryKey(Integer.parseInt(oid));
        Usermessage usermessage=new Usermessage();
        usermessage.setOid(Integer.parseInt(oid));
        usermessage.setPhone(order1.getPhone());
        usermessage.setDetails(textareaproblem1);
        usermessage.setState(order1.getState());
        usermessage.setNickname(u.getNickname());
        usermessage.setGoodsname(order1.getGoodsname());
        usermessage.setAddress(order1.getAddress());
        //消息生成时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        usermessage.setSendTime(dateFormat.format(date));
        userMessageService.insertSelective(usermessage);

        return "1111";

        //return "redirect:/OrdersManagement/";

    }




















    //用户头像更换有问题?
    @PostMapping(value = "/ChangeImage")
    @ResponseBody
    public String ChangeImage2328(@RequestParam(value = "file", required = false) MultipartFile file, HttpContext context, HttpSession session, Model model, HttpServletRequest request) throws IOException {
        System.out.println("------*******---------");
        //路径
        String fileName = UUID.randomUUID()+ file.getOriginalFilename();
        if(!file.isEmpty()){
            byte [] bytes = file.getBytes();
            BufferedOutputStream bufferedOutputStream = new
                    BufferedOutputStream(new FileOutputStream(new File("D:\\upload\\"+fileName)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        }
      /*  Product product = new Product( null,  pName,  pStock,  pCost,  pPrice,  pDescription,  fileName);
        return productService.add(product);*/
        return "1111";

    }











}
