package net.self.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class AlarmController {

    @RequestMapping("/v1/vnf/{listenerId}")
    public String assignAlarm(@PathVariable("listenerId") String listenerId,@RequestBody String json){
        System.out.println("listenerId:"+listenerId);
        System.out.println("test idear");
        return  "index";
    }
}
