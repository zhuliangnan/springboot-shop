package com.springboot.springbootshop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.springboot.springbootshop.config.AlipayConfig;
import com.springboot.springbootshop.model.Orders;
import com.springboot.springbootshop.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 支付宝支付接口
 **/
@Controller
public class PayController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/paytest")
    public String toTest(){
        return "AliPayTest";
    }

    @GetMapping("/Alipay/{WIDout_trade_no}/{WIDtotal_amount}/{WIDsubject}/{WIDbody}")
    public void payController(@PathVariable("WIDout_trade_no") String WIDout_trade_no, @PathVariable("WIDtotal_amount") String WIDtotal_amount, @PathVariable("WIDsubject") String WIDsubject, @PathVariable("WIDbody") String WIDbody, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {

        /* 默认付款成功 把订单状态改为1 */
        updateOrderState(session, ordersService);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String out_trade_no=new String(WIDout_trade_no);
        //付款金额，必填
       // String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
        String total_amount=new String(WIDtotal_amount);
        //订单名称，必填
        //String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
        //.getBytes("ISO-8859-1"), "UTF-8"
        String subject=new String(WIDsubject);
        //商品描述，可空
       // String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
        String body=new String(WIDbody);

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);//直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();

    }

    static void updateOrderState(HttpSession session, OrdersService ordersService) {
        List<Orders> orderList=(List<Orders>) session.getAttribute("OrderList");
        System.out.println("输出获取的orders+"+orderList.toString());
        for(Orders order:orderList){
            System.out.println(order);
            //把所有订单状态改为已付款,未发货
            ordersService.updateOrder(order.getOid(),1);

        }
    }

}
