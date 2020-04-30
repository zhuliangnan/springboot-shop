package com.springboot.springbootshop.controller;


import com.springboot.springbootshop.model.*;
import com.springboot.springbootshop.service.AddressService;
import com.springboot.springbootshop.service.CartService;
import com.springboot.springbootshop.service.GoodsService;
import com.springboot.springbootshop.service.ShouCangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: zhuliangnan
 * @date :
 * @blog:
 * @version: 1.0.0
 * @description: 商品页面 完成商品显示分页 生成一件订单 收藏 加入购物车 等操作
 **/
@Controller
public class GoodsController {

    // 用全局变量记录颜色及model的变化
    String model = "全网通32G";
    String color = "黑色";
    /*Double price = null;*/
    /*int Cartgid = 0;*/
   /* int Stock = 0;*/

    // 每页显示的条数
    private String PageSize = "8";

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ShouCangService shouCangService;

    @Autowired
    private AddressService addressService;


    @GetMapping(value = "/Showbypage/{page}") //page-从哪一页开始展示 ，goodstate-展示哪种商品
    public String Showbypage(@PathVariable("page") String page, HttpSession session, Model model, HttpServletRequest request) {

        // String goodstate=request.getParameter("goodstate");

        //这很重要主要用来保存当前保存的商品种类
        if(session.getAttribute("goodstate")==null){
            session.setAttribute("goodstate",1);
        }
        System.out.println("当前保存的商品种类是 "+session.getAttribute("goodstate"));
        /*
         * 每页显示的条数 pageSize="8"
         * 总条数 goodsNum
         * 总页数 pageTimes
         * 当前是要显示页 currentPage
         * 从第几个记录开始取记录 startRow
         */

        System.out.println("跳转到showbypage页面");
        // 获取用户
        model.addAttribute("existUser", session.getAttribute("existUser"));

        List<Goods> goodsList = goodsService.getAllGoods((int)session.getAttribute("goodstate"));

        // 总页数
        int pageSize = Integer.parseInt(PageSize);
        //总页数 动态数组
        ArrayList<Integer> pageTimes=new ArrayList<Integer>();
        int pageTimeNumber;//总页数 int
        if (goodsList.size() % pageSize == 0) {
           pageTimeNumber = goodsList.size() / pageSize; //若正好被8整除那就很好
        } else {
            pageTimeNumber = goodsList.size() / pageSize + 1; //若不能被8整除那就得多一页，用来展示多余的那个数据
        }
        /*
        把查询到该商品的总页数存入session 供前台<%  %>标签中调用
        但是实际情况是 <%  %>标签要想使用必须以.jsp结尾
        这里我们把 pageTimes存成一个数组方便th：调用
        */
        for(int i=1;i<=pageTimeNumber;i++){
            pageTimes.add(i);
            System.out.println(i);
         }



        //供前台一般输出调用
        model.addAttribute("pageTimes", pageTimes);
        model.addAttribute("pageTimeNumber", pageTimeNumber);



        // 页面初始的时候page没有值 从第一页开始展示
        if (null == page) {
            page = "1"; //没有设置从哪一页  默认从第一页开始展示
        }
        // 每页开始时应该显示第几条记录 比如第一页从0条开始 0-7 第二页从第8条记录开始
        int startRow = (Integer.parseInt(page) - 1) * pageSize;
        // 查询到分页的商品信息
        List<Goods> GoodsList = goodsService.getAllGoodsByPage(startRow, pageSize,(int)session.getAttribute("goodstate"));
        // 将全部记录数"googsNum"存入model
        model.addAttribute("googsNum", goodsService.getAllGoods((int)session.getAttribute("goodstate")).size());
        // 将分页商品存入"GoodsList"
        model.addAttribute("GoodsList", GoodsList);
        // 将当期页面存入model
        model.addAttribute("currentPage", Integer.parseInt(page));

        //默认搜索的是手机 这里把search 值设置为手机
        if(session.getAttribute("search")==null){
            session.setAttribute("search","手机");
            model.addAttribute("search","手机");
        }


        //找到这个用户的购物车列表
        getCarList(session, model);


        System.out.println("当前页面为currentPage "+Integer.parseInt(page)+"查询到记录总页数" +pageTimeNumber);

        return "showbypage";

    }

    // 分页+模糊查询
    @RequestMapping(value = "/Search/{page}/{search}")
    public String search(@PathVariable("page") String page,@PathVariable("search") String search, HttpSession session, Model model,
                         HttpServletRequest request) {
        /*
         * 每页显示的条数 pageSize="8" 总条数 goodsNum 总页数 pageTimes 当前是要显示页 currentPage
         * 从第几个记录开始取记录 startRow
         */

        String Newsearch=request.getParameter("search");

        System.out.println("获取到的Newsearch "+Newsearch+"获取到的search "+search);

        //防止Newsearch出现问题 为Null的字符串是不可以进行equals的会报错
        if(Newsearch==null){
            Newsearch="手机";
        }
        if(Newsearch.equals("手机")||Newsearch.equals("")){
            System.out.println("现在什么都不需要做");
        }else{
            search=Newsearch;
        }

        if(search.equals("")){
            search="手机";
        }

        System.out.println("模糊查询获取到的page "+page+"获取到的search "+search);
        //这里把search保存到model和session 两个都要保存 两个要保持一致
        model.addAttribute("search", search);
        session.setAttribute("search",search);
        System.out.println("此时有获取到的search++++++++++++++++++++++++++"+search);

        model.addAttribute("existUser", session.getAttribute("existUser"));
        // 总条数  如果就是模糊查询手机那么使用方法 getAllGoods 如果模糊查询如查询 华为 那么使用getAllGoodsLike
        List<Goods> goodsList=new ArrayList<Goods>();
        if(search.equals("手机")){
            System.out.println("进入该方法getAllGoods");
            goodsList = goodsService.getAllGoods((int)session.getAttribute("goodstate"));

        }else{
            System.out.println("进入该方法getAllGoodsLike");
            goodsList = goodsService.getAllGoodsLike(search,(int)session.getAttribute("goodstate"));
        }




        // 总页数
        int pageSize = Integer.parseInt(PageSize);
        //总页数 动态数组
        ArrayList<Integer> pageTimes=new ArrayList<Integer>();
        int pageTimeNumber;//总页数 int
        if (goodsList.size() % pageSize != 0) {
            pageTimeNumber = goodsList.size() / pageSize + 1; //若不能被8整除那就得多一页，用来展示多余的那个数据
        } else {
            pageTimeNumber = goodsList.size() / pageSize; //若正好被8整除那就很好
        }
        //将pageTimes值进行填充
        for(int i=1;i<=pageTimeNumber;i++){
            pageTimes.add(i);
            System.out.println(i);
        }

        //供前台一般输出调用
        model.addAttribute("pageTimeNumber", pageTimeNumber);
        model.addAttribute("pageTimes", pageTimes);



        // 页面初始的时候page没有值
        if (null == page) {
            page = "1";
        }
        // 每页开始的第几条记录
        int startRow = (Integer.parseInt(page) - 1) * pageSize;


        List<Goods> GoodsList=new ArrayList<Goods>();
        if(search.equals("手机")){
            // 查询到分页的商品信息
            GoodsList = goodsService.getAllGoodsByPage(startRow, pageSize,(int)session.getAttribute("goodstate"));
        }else{
            // 查询到分页的商品信息
            GoodsList = goodsService.getAllGoodsByPageLike(search, startRow, pageSize,(int)session.getAttribute("goodstate"));

        }

        // 将全部记录数"googsNum"存入model
        model.addAttribute("googsNum", goodsService.getAllGoods((int)session.getAttribute("goodstate")).size());
        // 将分页商品存入"GoodsList"
        model.addAttribute("GoodsList", GoodsList);
        // 将当期页面存入model
        model.addAttribute("currentPage", Integer.parseInt(page));

        getCarList(session, model);

        return "showbypage";
    }

    // 根据所需要查询的页码进行跳转
    @GetMapping(value = "/SearchByPage")
    public String search1(HttpSession session, Model model, HttpServletRequest request) {
        System.out.println("跳转到SearchByPage页面");
        String searchpage = (String) request.getParameter("searchpage");
        System.out.println("获取到的searchpage为" + searchpage);
        //这里就是为什么search还要保存到session当中
        String search=(String) session.getAttribute("search");
        System.out.println("获取到search"+search);
        model.addAttribute("existUser", session.getAttribute("existUser"));
        return getString(searchpage, search);
    }


    @GetMapping(value = "/Shoucang/{gid}/{page}")
    public void shoucang(@PathVariable("gid") String gid, @PathVariable("page") String page, HttpServletResponse response,HttpSession session, Model model, HttpServletRequest request) throws IOException, ServletException {
        model.addAttribute("existUser", session.getAttribute("existUser"));

        //int gid = Integer.parseInt(((String) request.getParameter("gid")));
        int uid = ((User) session.getAttribute("existUser")).getUid();

        //String page = (String) request.getParameter("page");

        shouCangService.addShouCang(new Shoucang(uid,Integer.parseInt(gid)));

        System.out.println("gid+uid====" + uid + " " + gid);
        // 消息框
        model.addAttribute("existUser", session.getAttribute("existUser"));

        String search=(String) session.getAttribute("search");

        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();
        out.flush();
        out.println("<script>");
        out.println("alert('收藏成功');");
        out.println("history.back();");
        out.println("</script>");

    }

   /*
   * 加入购物车 方法
   * 注意这里面的加入购物车 又分页的加入购物车和 详情页面的加入购物车
   * 这里是分页加入购物车
   * 默认值设置
   *  String model = "全网通32G";
      String color = "黑色";
      Double price = null;
   * */
    @GetMapping(value = "/ToAddCart/{gid}/{page}")
    public void addCart(@PathVariable("gid") String gid,@PathVariable("page") String page, HttpServletResponse response,HttpSession session, Model model2, HttpServletRequest request) throws IOException {

        int uid = ((User) session.getAttribute("existUser")).getUid();

        int number = 1;
        Cart cart = new Cart();
        String goodsname=goodsService.getGoodsByGid(Integer.parseInt(gid)).getGoodsname();
        cart.setUid(uid);
        cart.setGoodsname(goodsname);
        cart.setColor(color);
        cart.setModel(model); //这里需要注意 因为Model model2 名字和 model常量名冲突所以改一下 Model model 变为Model model2
        System.out.println("model"+model+"color"+color+"gid"+Integer.parseInt(gid)+"@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Detail detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
        cart.setPrice( detail.getPrice());
        cart.setNumber(number);
        cart.setPicture((goodsService.getGoodsByGid(Integer.parseInt(gid))).getPicture1());
        cart.setShopid(1);
        cartService.addCart(cart);

        System.out.println("cart+++++" + cart.toString());

        String search=(String) session.getAttribute("search");
        response.setContentType("text/html; charset=UTF-8"); //转码
       PrintWriter out = response.getWriter();
       switch (search){
             case "手机":
             out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Search/1/手机'</script>");
             break;
             case "华为":
                 out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Search/1/华为'</script>");
                 break;
             case "小米":
               out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Search/1/小米'</script>");
               break;
             case "魅族":
               out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Search/1/魅族'</script>");
               break;
           default:
               out.print("<script language=\"javascript\">alert('加入成功！');window.location.href='/springboot-web/Search/1/手机'</script>");
               break;
       }


        //return getString(page, search);
    }

    /*
     * 因为在跳转到Search方法时 每次都需要进行转码处理所以这里我们统一用一个方法进行处理
     * @getString()
     * */

    private String getString(@PathVariable("page") String page, String search) {
        try {
            search = URLEncoder.encode(search,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("进入getString");
        return "redirect:/Search/"+page+"/"+search;
    }


    /*
    * 显示good商品的详情
    * */
    @GetMapping(value = "/Detail/{gid}")
    public String getGoodsDetail(@PathVariable("gid") String gid,HttpSession session, Model model2, HttpServletRequest request) {

        String actionBy = (String) request.getParameter("actionBy");

        System.out.println("此时的actionBy为:+++++++++++++++++++++++----------:" + actionBy);
        //这里记录一下gid的值 记录在Cartgid中  供生成这个商品的订单使用
       /* Cartgid = Integer.parseInt(gid);*/
        System.out.println("跳转到detail页面此时gid为:" + gid);
        model2.addAttribute("existUser", session.getAttribute("existUser"));
        /*
         * 商品详细 Following
         */
        // detail页面初始参数
        /*
        * 获取商品信息 值得注意的是
        *
        * 详细信息 是由 good表和detail 表拼凑而来
        *
        * detail 表 保存了存储的的基本初始化信息   model color 和 stock（库存）
        * 因为不同的 model 价格不同 所以 detail 表中有很多同一个商品但价格不同的 案例
        * 所以默认我们使用 我们定义好的 全局变量 model color
        * goods 表保存了商品姓名  图片信息
        *
        * */
  /*      String model3 = (String) request.getParameter("model");
        String color3 = (String) request.getParameter("color");
        if (model3 != null) {
            model = model3;
        }
        if (color3 != null) {
            color = color3;
        }*/

        Goods goods = goodsService.getGoodsByGid(Integer.parseInt(gid));
        Detail detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
        //这里更改一下默认库存信息
       // Stock = detail.getStock();
        System.out.println("goods" + goods.toString() + "  model" + model + "   color" + color);
        System.out.println("detail" + detail.toString());
       // price = detail.getPrice();

        //这里把查询好的good是和detail信息保存 同样因为model 属性和 Model model 相同所以使用model2
        model2.addAttribute("goods", goods);
        model2.addAttribute("detail", detail);
        //用来判断 选择的是哪一个 32 64 128 同时 他就处于选择状态
        model2.addAttribute("selectModel", model);
        model2.addAttribute("selectColor", color);
        /*
         * 商品评价部分
         * 下面加载 商品的评价信息
         *
         */
        // 全部商品评价
        List<Comment> commentList = goodsService.getCommentByGid(Integer.parseInt(gid));
        model2.addAttribute("commentList", commentList);
        model2.addAttribute("commentListNum", commentList.size());
        System.out.println("commentList+++++++++gid  " + gid + "        " + commentList);
        // 好评 state>=4     4 5
        List<Comment> GcommentList = goodsService.getCommentByGidandState(Integer.parseInt(gid), 4,5);
        model2.addAttribute("GcommentList", GcommentList);
        model2.addAttribute("GcommentListNum", GcommentList.size());
        System.out.println("GcommentList+++++++++gid  " + gid + "        " + GcommentList);
        // 中评   4>state>=2  2 3
        List<Comment> ZcommentList = goodsService.getCommentByGidandState(Integer.parseInt(gid), 2,3);
        model2.addAttribute("ZcommentList", ZcommentList);
        model2.addAttribute("ZcommentListNum", ZcommentList.size());

        System.out.println("ZcommentList+++++++++gid  " + gid + "        " + ZcommentList);
        // 差评 3>state>=1    1
        List<Comment> CcommentList = goodsService.getCommentByGidandState(Integer.parseInt(gid), 1,0);
        model2.addAttribute("CcommentList", CcommentList);
        model2.addAttribute("CcommentListNum", CcommentList.size());

        System.out.println("CcommentList+++++++++gid  " + gid + "        " + CcommentList);


        //找到这个用户的购物车列表
        getCarList(session, model2);


        return "detail";
    }

    private void getCarList(HttpSession session, Model model2) {

        List<Cart> cartList=new ArrayList<Cart>();
        if(session.getAttribute("existUser")!=null){
            cartList = cartService.showAllCart(((User) session.getAttribute("existUser")).getUid());
        }else{
            System.out.println("用户没有登录");
        }
        model2.addAttribute("cartList", cartList);

        model2.addAttribute("cartListSize", cartList.size());
    }


    @RequestMapping("/ThymeleafRefreshByLocal") //用于局部数据刷新 参照 ajax
    public  Object  ajaxInput(HttpSession session, Model model2, HttpServletRequest request){

        System.out.println("+++++++++++++ThymeleafRefreshByLocal++++v+++++++++++++");
        //获取ajax传过来的值
        String model3=request.getParameter("model");
        String color3=request.getParameter("color");
        String gid=request.getParameter("gid");

        System.out.println("获取到了model3"+model3+"获取到了color3"+color3+"获取到了gid  "+gid);

        if(model3==null||model3.equals("默认")||model3.equals("")){
            System.out.println("注意 model 此时为"+model3+"什么都不做");
        }else{
            model=model3;
        }
        if(color3==null||color3.equals("默认")||color3.equals("")){
            System.out.println("注意 color3 此时为"+color3+"什么都不做");
        }else{
            color = color3;
        }

      Goods goods = goodsService.getGoodsByGid(Integer.parseInt(gid));
      Detail detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
      //这里更改一下默认库存信息
     // Stock = detail.getStock();
     // price = detail.getPrice();

      //这里把查询好的good是和detail信息保存 同样因为model 属性和 Model model 相同所以使用model2
        model2.addAttribute("detail", detail);
        model2.addAttribute("goods", goods);

      /*  //JSON对象序列化 对象转化为Json字符串
        String jsonString = JSON.toJSONString(detail);
        //Json字符串转化为相应的对象
        JSONObject parseObject = JSONObject.parseObject(jsonString);
        System.out.println("这里输出一下序列化的detail"+parseObject);*/

        //返回到 detail 页面的 detail_refresh 的div 进行局部刷新
        return "detail::detail_refresh";

    }

    // 商品的生成订单
    @RequestMapping(value = "/Myorder/{gid}")
    public String order1(@PathVariable("gid") String gid,HttpSession session, Model model2,HttpServletResponse response, HttpServletRequest request) throws IOException {
        model2.addAttribute("existUser", session.getAttribute("existUser"));
        //获取商品信息
        Goods goods = goodsService.getGoodsByGid(Integer.parseInt(gid));
        System.out.println("商品为:==========" + goods.toString());
        String number = (String) request.getParameter("number2");
        System.out.println("number "+number);
        //获取商品详情信息
        Detail detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
        int number2 = Integer.parseInt(number);

        if (number2 > detail.getStock()) {

            response.setContentType("text/html; charset=UTF-8"); //转码
            PrintWriter out = response.getWriter();
            out.flush();
            out.println("<script>");
            out.println("alert('您购买的商品数量已超过库存,请谅解');");
            out.println("history.back();");
            out.println("</script>");

            //JOptionPane.showMessageDialog(null, "您购买的商品数量已超过库存,请谅解", "标题【错误】", JOptionPane.ERROR_MESSAGE);
            //return "redirect:/Detail/" + gid;

        }
        Orders order = new Orders();
        order.setUid(((User) session.getAttribute("existUser")).getUid());
        order.setColor(color);
        order.setGoodsname(goods.getGoodsname());
        order.setModel(model);
        order.setNumber(number2);
        order.setPicture(goods.getPicture1());
        order.setPrice( detail.getPrice());
        /*
        * 备注 state
        * 0:未付款 1:已付款未发货 2:已付款 已发货 3:已付款已签收
        * 售后
        * 4001 换货 4002 退货退款 4003订单完成
        * */
        order.setState(0);
        //输出一下uid
        int uid = ((User) session.getAttribute("existUser")).getUid();
        System.out.println("uid为++++++++:" + uid);
        //订单生成时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        order.setUpTime(dateFormat.format(date));

        // 获取地址List<Address> 默认地址 因为一个人会有很多地址应该用list保存 而实际上 默认的地址只有一个

        if(addressService.ShowAllAddressByState(uid, "默认")==null){
            System.out.println("地址为空的");
           // 空对象，系统没有为它分配内存，换句话说，它没有任何指向，那么set的值也没有具体内存可以存放
            Address address=new Address(); //这个对象就有存储空间了
            address.setName("新用户");
            address.setPhone("默认手机号");
            address.setAddress("请新增用户地址信息");
            //保存没有数据的地址信息
            model2.addAttribute("address", address);

        }else{
            Address address=addressService.ShowAllAddressByState(uid, "默认");
            order.setAddname(address.getName());

            System.out.println("地址为++++:" + address.toString());

            // 手机号进行隐藏186****4832
            String phone = address.getPhone();
            String phoneNumber = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
            address.setPhone(phoneNumber);

            order.setPhone(phoneNumber);
            order.setAddress(address.getAddress());
            //保存有数据的地址信息
            model2.addAttribute("address", address);
        }
        //到此为止订单信息才算是封装完毕

        List<Orders> OrderList = new ArrayList<Orders>();
        OrderList.add(order);

        // 用来保存当前订单数据 以list保存 这个很重要 以后保存订单信息就用这个
        session.setAttribute("OrderList", OrderList);

        model2.addAttribute("OrderList", OrderList);


        System.out.println("Order的基本信息是+++++" + order.toString());

        int goodssize = OrderList.size();
        double sum = number2 * detail.getPrice();
        double sumPrice = number2 * detail.getPrice() + 8;
        model2.addAttribute("goodssize", goodssize);
        model2.addAttribute("sum", sum);
        model2.addAttribute("sumPrice", sumPrice);

        return "order";


    }

    @GetMapping(value = "/AddCartByDetail/{gid}")
    public void toGetCart(@PathVariable("gid") String gid,HttpSession session, HttpServletResponse response, Model model2, HttpServletRequest request) throws IOException {

        response.setContentType("text/html; charset=UTF-8"); //转码
        PrintWriter out = response.getWriter();

        model2.addAttribute("existUser", session.getAttribute("existUser"));
        String number = (String) request.getParameter("number2");
        int number2 = Integer.parseInt(number);
        Detail detail = goodsService.getDetailByModelandColor(model, color, Integer.parseInt(gid));
        Cart cart = new Cart();

        cart.setColor(color);
        cart.setModel(model);
        cart.setPrice(detail.getPrice());
        cart.setNumber(number2);
        cart.setPicture((goodsService.getGoodsByGid(Integer.parseInt(gid))).getPicture1());
        if (number2 > detail.getStock()) {
           // JOptionPane.showMessageDialog(null, "您购买的商品数量已超过库存,请谅解", "标题【错误】", JOptionPane.ERROR_MESSAGE);
            out.flush();
            out.println("<script>");
            out.println("alert('您购买的商品数量已超过库存,请谅解');");
            out.println("history.back();");
            out.println("</script>");
           // return "redirect:/Detail/" + gid;

        }else{
            cart.setShopid(1);
            String goodsname=goodsService.getGoodsByGid(Integer.parseInt(gid)).getGoodsname();
            cart.setGoodsname(goodsname);
            int uid = ((User) session.getAttribute("existUser")).getUid();
            cart.setUid(uid);
            cartService.addCart(cart);
            System.out.println("cart+++++" + cart.toString());

            out.flush();
            out.println("<script>");
            out.println("alert('加入成功');");
            out.println("history.back();");
            out.println("</script>");

            // JOptionPane.showMessageDialog(null, "加入成功");

            //return "redirect:/Detail/" + gid;

        }

    }




}
