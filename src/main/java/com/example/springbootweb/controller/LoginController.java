package com.example.springbootweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登录成功
            //return "dashboard";
            //为防止表单重复提交，将上面改为页面重定向
            session.setAttribute("loginUser",username);
            //保存已登录的用户信息
            return "redirect:/main.html";
            //重定向
        }else{
            //登录失败
            map.put("msg","用户名密码错误");
            return "index";
        }
    }

}
