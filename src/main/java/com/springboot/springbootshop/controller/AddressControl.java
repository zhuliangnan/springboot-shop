package com.springboot.springbootshop.controller;

import com.springboot.springbootshop.model.Address;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.model.User;
import com.springboot.springbootshop.service.AddressService;
import com.springboot.springbootshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 地址页面 对地址进行更新
 **/
@Controller
public class AddressControl {

    @Autowired
    private AddressService addressService;
    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "/Addaddress")
    public String add(HttpSession session, Model model2, HttpServletRequest request) {

        model2.addAttribute("existUser", session.getAttribute("existUser"));

        String name = (String) request.getParameter("name");
        String address = (String) request.getParameter("address");
        String phone = (String) request.getParameter("phone");

        /*
         * 手机号码验证

         */
        //手机号进行隐藏186****4832
        String phoneNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());


        /*
         * 向数据库添加
         */
        User user=(User) session.getAttribute("existUser");
        addressService.updateAddress(user.getUid()); //把这个人所有的地址改成非默认的 相当于格式化操作
        addressService.addAddress(new Address(user.getUid(), address, phone, name, "默认")); //然后单独把这个人新增加的地址设置为默认
        //获取到orderlist
        List<Orders> OrderList =(List<Orders>) session.getAttribute("OrderList");
       // Orders order=OrderList.get(0);
        /*
         * 将新的地址信息填入订单中,
         */
        double sumPrice = 0.0;
        for(Orders order1:OrderList){
            order1.setAddress(address);
            order1.setAddname(name);
            order1.setPhone(phone);//这里电话不要填加密的
            //ordersService.updateByPrimaryKeySelective(order1);//更新订单 只把order1当中有值的属性更新 根据oid更新 s
            double sum = order1.getPrice() * order1.getNumber();
            sumPrice = sum + sumPrice;

        }
        int goodssize=OrderList.size();

        /*
         * 页面显示数据
         */
        model2.addAttribute("goodssize",goodssize);
        // model2.addAttribute("sum",sum);
        model2.addAttribute("sumPrice",sumPrice+8);
        //订单数量 保存到 goodsize
        model2.addAttribute("goodssize", OrderList.size());
        /*
         * 包含所有订单信息的OrderList 及 address
         */
        model2.addAttribute("OrderList",OrderList);
        //保存要显示的信息
        model2.addAttribute("address",new Address(address,phoneNumber,name));
        return "order";
    }




}
