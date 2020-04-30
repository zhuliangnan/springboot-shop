package com.springboot.springbootshop.controller;


import com.springboot.springbootshop.model.User;
import com.springboot.springbootshop.service.CartService;
import com.springboot.springbootshop.service.UserService;
import com.springboot.springbootshop.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author: zhuliangnan
 * @date :7.29
 * @blog:
 * @version: 1.0.0
 * @description: 登录页面 进行登陆操作以及用户的其他操作
 **/
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;


    /*
     *用来跳转到登录页面并且判断该用户是否近期登陆时选择了记住密码，
     * session保存时间为1天（服务器不关闭情况）
     * 自动登录
     * */

    @GetMapping("/Login")
    public ModelAndView getLogin(HttpSession session, Model model) {
        System.out.println("跳转到getlogin页面");
        //判断需不需要自动登录
        if(session.getAttribute("existUser")!=null){
           int uid= ((User)session.getAttribute("existUser")).getUid();
            ModelAndView mav = new ModelAndView("index");
            // 保存购物车信息
            session.setAttribute("cartList", cartService.showAllCart(uid));
            session.setAttribute("cartListSize", (cartService.showAllCart(uid).size()));
            return mav;
        }else{
            //创建一个空的user对象 用来接收form表单的值
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", new User());
            return mav;
        }
    }
    @GetMapping("/relogin")
    public ModelAndView toLogin(HttpSession session, Model model) {
        System.out.println("跳转到getlogin页面");
        //判断需不需要自动登录

            //创建一个空的user对象 用来接收form表单的值
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", new User());
            return mav;

    }
    /*
     *用于获取登录页面传过来的用户信息 并把用户的购物车信息保存
     * */
    @PostMapping("/Login")    //@ModelAttribute("user") 获取前台封装好的user对象
    public String postLogin(@ModelAttribute("user") User user, Model model, HttpSession session, HttpServletResponse response,
                                  HttpServletRequest request)throws IOException
    {
        System.out.println("跳转到postlogin页面");
        //对用户进行判断 用户名不为空和密码不为空
        if (user.getNickname().equals("")) {


            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write( "<script>alert('用户名不能为空,请重新输入！'); window.location='../springboot-web/relogin';window.close();</script>");
            response.getWriter().flush();


            //JOptionPane.showMessageDialog(null, "用户名不能为空,请重新输入", "错误提示", JOptionPane.ERROR_MESSAGE);
            return "login";
        }
        if (user.getPassword().equals("")) {
            //JOptionPane.showMessageDialog(null, "密码不能为空,请重新输入", "错误提示", JOptionPane.ERROR_MESSAGE);

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write( "<script>alert('密码不能为空,请重新输入'); window.location='../springboot-web/relogin';window.close();</script>");
            response.getWriter().flush();

            return "login";
        }
        System.out.println("user为:++++++++++++" + user.toString());//输出看一下下
        /*
         * loginService.login()对密码加密并在数据库的密码 进行对比
         */
        String passwordByEncrypt = MD5.string2MD5(user.getPassword());
        System.out.println("加密过后的密码为:"+passwordByEncrypt);
        user.setPassword(passwordByEncrypt);
        // 通过登录名和密码查找用户
        User user1=userService.selectUserByNameAndPassword(user.getNickname(),user.getPassword());
        //如果查找到的用户不为空 判断是否需要保存登录信息  需要就session保存
        if (user1 != null) {
            System.out.println("找到的用户为:"+user1.toString());
            //保存当前用户的值 很重要
            session.setAttribute("existUser", user1);
            // 保存购物车信息
            session.setAttribute("cartList", cartService.showAllCart(user1.getUid()));
            session.setAttribute("cartListSize", (cartService.showAllCart(user1.getUid()).size()));
            System.out.println("得到的购物车列表"+cartService.showAllCart(user1.getUid()).toString());
            //获取自动登录标签的值 判断是否用户信息需要保存
            String action = (String) request.getParameter("autologin");
            if ("autologin".equals(action)) {
                System.out.println("自动登录被点击,");
                session.setMaxInactiveInterval(1 * 24 * 60 * 60); //保存一天
                //创建一个Json对象用来保存登录数据 注意：这里的密码是加密过后的密码
                session.setAttribute("existUser", user1);
                System.out.println("其中session中保存用户登录数据的格式为： "+ user1.toString());
            }
            return "index";

        }else{
            System.out.println("用户没有查到");
            //JOptionPane.showMessageDialog(null, "用户不存在,请重新输入", "错误提示", JOptionPane.ERROR_MESSAGE);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write( "<script>alert('用户不存在,请重新输入'); window.location='../springboot-web/relogin';window.close();</script>");
            response.getWriter().flush();
            return "login";

        }

    }

    @GetMapping("/Register")
    public ModelAndView registerGet(HttpSession session, Model model) {
        System.out.println("跳转到register-get页面");

        ModelAndView mav = new ModelAndView("register");
        //创建一个空的user对象 用来接收form表单的值
        mav.addObject("user", new User());
        return mav;
    }

    /*
     *用于接收注册页面的用户信息
     * */
    @PostMapping("/Register")
    public String registerPost(HttpSession session, Model model, HttpServletRequest request) {
        System.out.println("跳转到register-post页面");

        String nicename = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        String email = (String) request.getParameter("email");

        System.out.println("password为" + password);
        User user = new User();
        user.setNickname(nicename);
        user.setPassword(password);
        user.setEmail(email);
        user.setImage("user02");//默认存储user02 的图片 这个图片好看
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        user.setResTime(dateFormat.format(date));

        System.out.println("user输出:" + user.toString());

        user.setPassword(MD5.string2MD5(password));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

        userService.insert(user);

        System.out.println("添加成功");

        user.setUid(userService.selectUserByName(user.getNickname()).getUid());

        System.out.println("user为" + user.toString());

        session.setAttribute("existUser", user);

        session.setAttribute("cartListSize", 0);


        return "index";
    }

    /*
     *J用来判断注册页面所注册的nicename是否已经被注册过来
     * */
    @PostMapping("/JudgeNickname")
    @ResponseBody
    public String panduan(String nickname, HttpSession session, Model model, HttpServletRequest request) {
        System.out.println("JudgeNickname");
        // String email=(String)request.getParameter("email");
        System.out.println("获取的nickname是:" + nickname);

        if ((userService.selectUserByName(nickname)) != null) {
            System.out.println("该用户已存在");
            return "4444";

        }else{
            System.out.println("该用户不存在");
            return "5555";
        }


    }


    /*
     * 跳转到忘记密码页面
     * */

    @GetMapping("/ForgetPassword")
    public ModelAndView forgetPassword(HttpSession session, Model model) {
        System.out.println("跳转到忘记密码页面");

        ModelAndView mav = new ModelAndView("renewPassword");

        return mav;
    }
    /*
     * 验证身份通过 进行修改密码
     * */

    @PostMapping("/UpdatePassword")
    public String updatePassword(HttpServletRequest request) {
        System.out.println("进入updatePassword");
        String email = (String) request.getParameter("email2");
        String password = (String) request.getParameter("password");

        System.out.println("要修改的email为： password  "+email+"  "+password);

        userService.updateUserByEmailAndPassword(email,MD5.string2MD5(password));


        //页面重定向 到登录页面
        return "redirect:/Login";
    }

    @GetMapping("/RemoveLogin")
    public String removeLogin(HttpSession session, Model model, HttpServletRequest request) {
        System.out.println("跳转到removeLogin页面");

        //String action = (String) request.getParameter("action");

        session.setAttribute("existUser", null);

        session.setAttribute("cartList", null);
        session.setAttribute("cartListSize", 0);

        return "index";
    }



}
