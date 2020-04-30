package com.springboot.springbootshop.controller;

import com.springboot.springbootshop.model.*;
import com.springboot.springbootshop.service.AddressService;
import com.springboot.springbootshop.service.CartService;
import com.springboot.springbootshop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 购物车页面 完成 购物车的显示 修改 和购物车订单生成的操作
 **/
@Controller
public class CartController {


    @Autowired
    private AddressService addressService;

    @Autowired
    private CartService cartService;

    @Autowired
    private GoodsService goodsService;


    /*
    * Showallcart主要完成两个操作
    * 1.显示购物车 这个操作很简单
    * 2.为购物车生成订单 当pay=付款的时候开始生成订单
    *
    * */
    @RequestMapping(value = "/Showallcart")
    public String showcart(@RequestParam(defaultValue = "默认") String pay, HttpSession session, Model model,
                           HttpServletRequest request) {

        List<Goods> goodsList = new ArrayList<Goods>();

        //购物车的总价
        double sumprice = 0.0;

        double sum=0.0;

        //找到这个用户的购物车列表
        List<Cart> cartList = cartService.showAllCart(((User) session.getAttribute("existUser")).getUid());

        //计算总价
        for (Cart cart : cartList) {
            sum = cart.getPrice() * cart.getNumber();
            sumprice = sum + sumprice;
            System.out.println("购物车列表cid为：" + cart.getCid());
            //通过商品名字找到这个商品并且加入到goodlist列表中
            goodsList.add(goodsService.getGoodsByGoodsname(cart.getGoodsname()));

        }

        model.addAttribute("cartList", cartList);

        model.addAttribute("sumprice", sumprice);
        //实际上goodlist是干什么用的呢?
        model.addAttribute("goodsList", goodsList);

        model.addAttribute("cartListSize", cartList.size());

        /*
        * 以上显示购物车 这个操作就已经完成了 项目进行订单生成以及地址生成
        * */


        //这个是订单提交页面用的 操作  点击了付款就是进行生成订单
        if (pay.equals("付款")) {
            /*
             * 下面开始进行订单生成操作
             */
            List<Orders> OrderList = new ArrayList<Orders>();
            //对于
            for (Cart cart : cartList) {

                System.out.println("每个cart的值 :" + cart.toString());
                //找到用户的id
                int uid = ((User) session.getAttribute("existUser")).getUid();
                //如果地址不为空
                if (addressService.ShowAllAddressByState(uid, "默认")!=null) {
                    Address address=addressService.ShowAllAddressByState(uid, "默认");
                    model.addAttribute("address", address);
                    String phone = address.getPhone();
                    //对phone进行加密处理
                    String phoneNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
                    address.setPhone(phoneNumber);
                    // 找到每个cart对应的商品信息 用于生成订单信息
                    Goods goods = goodsService.getGoodsByGoodsname(cart.getGoodsname());

                    System.out.println("看看goods：" + goods.toString());

                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                    //生成订单信息
                    Orders orders=new Orders(cart.getUid(),address.getName(),cart.getGoodsname(),
                            cart.getNumber(),dateFormat.format(date),0,address.getAddress(),phone,cart.getModel(),
                            cart.getColor(),cart.getPrice(),cart.getPicture());

                    System.out.println("Order-----------:" + orders.toString());

                    OrderList.add(orders);

                } else {
                    //如果没有找到该用户的地址  说明该用户是第一次使用这个商场在数据库里面没有这个用户的地址信息

                    //再做一次和前面可以找到地址里面的操作 找到每个cart对应的商品信息 用于生成订单
                    // 空对象，系统没有为它分配内存，换句话说，它没有任何指向，那么set的值也没有具体内存可以存放
                    Address address1=new Address(); //这个对象就有存储空间了
                    address1.setAddress("请新增用户地址信息");
                    address1.setName("新用户");
                    address1.setPhone("默认手机号");
                    //保存没有数据的地址信息
                    /*
                     * 这里需要说明一下 前台显示的address是显示model里面的address 我不需要显示orderlist 里面的值
                     * 因为这样很麻烦
                     * */
                    model.addAttribute("address", address1);


                    Date date = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");

                    //生成订单信息 注意这里面的addressname （收件人） 和 phone   address 都是没有的 因为没有找到默认的地址
                    Orders orders = new Orders(cart.getUid(), null, cart.getGoodsname(), cart.getNumber(),
                            dateFormat.format(date), 0, null, null, cart.getModel(), cart.getColor(),
                            cart.getPrice(), cart.getPicture());

                    System.out.println("Order-----------:" + orders.toString());

                    OrderList.add(orders);

                }

            }
            //以上就完成了 找到地址和没有找到地址两种情况的 订单信息生成
            //现在把订单信息进行保存 注意这里的信息 有有地址的和没有地址的 两种

            model.addAttribute("OrderList", OrderList);
            // double sum=number*price;
            //订单数量 保存到 goodsize
            model.addAttribute("goodssize", OrderList.size());
            // model.addAttribute("sum",sum);
            model.addAttribute("sumPrice", sumprice + 8);

            // 用来保存当前订单数据 以list保存 这个很重要 以后保存订单信息就用这个
            session.setAttribute("OrderList", OrderList);

            return "order";
        }

        return "my-car";
    }

    // 购物车改变数量
    @GetMapping("/Changenumber/{cid}/{action}")
    public String art(@PathVariable("cid")Integer cid, @PathVariable("action")String action, HttpServletResponse response, HttpSession session, Model model, HttpServletRequest request) throws IOException {



        System.out.println("action:" + action + "  cid :" + cid);

        System.out.println("获取到要修改的购物车:" + (cartService.selectCartByCid(cid)).toString());

        if (action.equals("remove")) {

            if (((cartService.selectCartByCid(cid)).getNumber() - 1) <= 0) {

               // JOptionPane.showMessageDialog(null, "数量不可以小于1哦，建议删除", "提示！", JOptionPane.ERROR_MESSAGE);
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write( "<script>alert('数量不可以小于1哦，建议删除'); window.location='/springboot-web/Showallcart';</script>");
                response.getWriter().flush();

              /*  response.setContentType("text/html; charset=UTF-8"); //转码
                PrintWriter out = response.getWriter();
                out.print("<script language=\"javascript\">alert('数量不可以小于1哦，建议删除');history.back();</script>");
*/
                //return "redirect:/Showallcart";

            }else{

                cartService.updateNumber((cartService.selectCartByCid(cid)).getNumber() - 1, cid);

                return "redirect:/Showallcart";
            }

        }
        if (action.equals("add")) {

            cartService.updateNumber((cartService.selectCartByCid(cid)).getNumber() + 1, cid);

            return "redirect:/Showallcart";
        }

        return "redirect:/Showallcart";
    }

    @GetMapping(value = "/Delete/{cid}")
    //首先明确一点 删除操作之后哪些数据会发生改变呢  sumprice
    public String deletecart(@PathVariable("cid")Integer cid,HttpSession session, Model model, HttpServletRequest request) {

        cartService.deleteCart(cid);

        return "redirect:/Showallcart";

    }

}
