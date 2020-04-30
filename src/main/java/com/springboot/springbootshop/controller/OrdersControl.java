package com.springboot.springbootshop.controller;

import com.springboot.springbootshop.model.Cart;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.model.User;
import com.springboot.springbootshop.service.CartService;
import com.springboot.springbootshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description:  订单页面 加入订单 和对于订单状态的操作
 **/
@Controller
public class OrdersControl {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartService cartService;



    @RequestMapping(value = "/Addorder")
    public String add(HttpSession session, Model model2, HttpServletRequest request) {
        //这里千万别别写成全局变量
        double sumprice=0.0;

        List<Orders> orderList=(List<Orders>) session.getAttribute("OrderList");

        System.out.println("订单信息为跟踪+:"+orderList.toString());

        for(Orders order:orderList){
            System.out.println(order);
            if(ordersService.selectByPrimaryKey(order.getOid())==null){
                //如果是不存在的订单就需要再去数据库添加数据
                ordersService.insertSelective(order); //逆向工程生成的代码不无脑  有值的就填充 oid又数据库自动生成
                //注意此时session里面的orderlist是缺少 oid信息的 需要更新 oid我们不知道 所有字段只有时间字段+商品名  具有唯一性
                //解释如下: 如果很多个订单一起生成时间会出现一模一样  但是一个商品即使买了多个也只生成一个订单 时间也是惟一的
                Orders orders2=ordersService.selectByupTime(order.getUpTime(),order.getGoodsname());
                order.setOid(orders2.getOid());
            }
            //ordersService.addOrder(order);
            sumprice=sumprice+order.getNumber()*order.getPrice();
        }


        System.out.println("更新完毕订单信息再跟踪+:"+orderList.toString());
        //信息更新
        session.setAttribute("OrderList",orderList);




       /* List<Orders> orderList2=ordersService.getAllOrdersByUid(((User)session.getAttribute("existUser")).getUid());
        session.setAttribute("OrderList",orderList2);
        System.out.println("输出更新后的 OrderList"+orderList2.toString());*/


        Orders order=orderList.get(0);
        String phoneNumber = order.getPhone().substring(0, 3) + "****" + order.getPhone().substring(7, order.getPhone().length());

        model2.addAttribute("phone",phoneNumber);
        model2.addAttribute("address",order.getAddress());
        model2.addAttribute("name",order.getAddname());

        model2.addAttribute("OrderList",orderList);

        model2.addAttribute("sumprice",sumprice+8);

        Random r = new Random();
        int randnum=r.nextInt(99999)+100000;

        model2.addAttribute("ordersid",randnum);

        //生成订单后对应的cart表数据清空
        List<Cart> cartList = cartService.showAllCart(((User) session.getAttribute("existUser")).getUid());

        for(Cart cart:cartList){

            cartService.deleteCart(cart.getCid());

        }



        return "d-success";
    }


    //根据购物车生成的订单

    @RequestMapping(value = "/UpdateMyorderState")
    public void allmyorder(HttpSession session, HttpServletResponse response, Model model2, HttpServletRequest request)throws IOException {

        PayController.updateOrderState(session, ordersService);
        session.setAttribute("cartListSize", (cartService.showAllCart(((User)session.getAttribute("existUser")).getUid())).size());

        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();
        out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Login'</script>");
        //JOptionPane.showMessageDialog(null, "已经成功付款,我们会尽快为您安排发货。");

        //return "index";
    }



}
