package com.quanxiaoha.weblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 犬小哈
 * @url: www.quanxiaoha.com
 * @date: 2023-04-18 8:14
 * @description:
 **/
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

}
