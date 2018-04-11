package com.yiren.example.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wanghao
 * create 2018-04-11 18:23
 **/
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping("/test")
    @ResponseBody
    public Long test() {
        return RequestHolder.getId();
    }

}
