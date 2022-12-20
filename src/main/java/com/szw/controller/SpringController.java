package com.szw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author szw
 * @Auther: szw
 * @Date: 2022/10/28 - 10 - 28 - 17:11
 * @Description: com.szw.controller
 * @version: 1.0
 */
@Controller
public class SpringController {
    @RequestMapping("/")
    public String index() {

        return "index";
    }
    @RequestMapping("/success")
    public  String test(HttpServletRequest request){
//     String id=request.getParameter("id");
//        String name=request.getParameter("name");
//        System.out.println(id);
//
//        System.out.println(name);
        request.setAttribute("szw","szzz");
        return  "param";
//        return  "success";
    }
//    @RequestMapping("/param")
//    public  String test2(HttpServletRequest request){
//        return  "param";
//    }
}
