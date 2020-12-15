package com.yibo.parking.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/web/V1/Count")
public class CountV1ApiController {

    @ResponseBody
    @RequestMapping(value = "/welcome")
    public String index(){
        return null;
    }

}
