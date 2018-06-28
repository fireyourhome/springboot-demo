package com.springboot.demo.springbootrabbitmq.contoller;

import com.springboot.demo.springbootrabbitmq.hello.HelloSender;
import com.springboot.demo.springbootrabbitmq.many.NeoSender;
import com.springboot.demo.springbootrabbitmq.many.NeoSender2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private HelloSender helloSender;
    @Autowired
    private NeoSender neoSender1;
    @Autowired
    private NeoSender2 neoSender2;

    @RequestMapping("/sendMq")
    @ResponseBody
    public String sendMq(){
        helloSender.send();
        return "已经发送";
    }

    @RequestMapping("/oneToMany")
    @ResponseBody
    public String oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender1.send(i);
        }
        return "oneToMany";
    }

    @RequestMapping("/manyToMany")
    @ResponseBody
    public String manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender1.send(i);
            neoSender2.send(i);
        }
        return "manyToMany";
    }

}
