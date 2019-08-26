package com.jk.controller;

import com.jk.model.UserBean;
import com.jk.service.UserService;
import com.jk.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String index(HttpServletRequest request, Model model) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.cookieNamePwd)) {
                    String value = cookie.getValue();//保存的是用户名+/分隔符+密码
                    if (value != null) { //str = 123.456  [123,456]
                        String[] split = value.split(Constant.splitChart);

                        model.addAttribute("username", split[0]);
                        model.addAttribute("password", split[1]);
                    }
                }
            }
        }
        return "login";
    }


    @RequestMapping("login")
    public  String  toLogin(HttpServletResponse response, UserBean userBean, Model model, HttpSession session){
        UserBean findUserById =userService.findUserById(userBean);
        if (findUserById != null) {

            if (userBean.getRemPwd() != null) {
                //是-->  用户名和密码都存到cookie中去

                Cookie cookie = new Cookie(Constant.cookieNamePwd, userBean.getUsername() + Constant.splitChart + userBean.getPassword());

                cookie.setMaxAge(604800);

                response.addCookie(cookie);
            } else {
                //否--> 清除cookie
                Cookie cookie = new Cookie(Constant.cookieNamePwd, "");

                cookie.setMaxAge(0);

                response.addCookie(cookie);
            }
            session.removeAttribute("msg");
        } else {
            //密码输入错误 TODO


            session.setAttribute("msg", "密码输入错误");
            Cookie cookie = new Cookie(Constant.cookieNamePwd, "");

            cookie.setMaxAge(0);

            response.addCookie(cookie);

            return "redirect:toLogin";
        }


        return "logo";
    }

        }


