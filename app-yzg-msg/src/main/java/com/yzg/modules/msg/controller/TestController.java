package com.yzg.modules.msg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @description:描述
 * @author: 空想的闲暇时光  wall·e
 * @createDate: 2022/10/29 9:00
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        map.put("name", "chenf24k");
        return new ModelAndView("not-msg", map);
    }
}
