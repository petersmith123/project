package com.project.controller;

import com.project.websocket.MyMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Map;

@RequestMapping("/socket")
@Controller
public class WebSocketController {
    @Autowired
    MyMessageHandler myMessageHandler;

    /**
     * 对单个用户推送消息
     * @return
     * @throws ParseException
     */
    @RequestMapping("/sendMessageByUser")
    @ResponseBody
    public String sendMessageByUser(@RequestParam Map<String, Object> contents) throws ParseException {
        return "success";
    }

    /**
     * 对所有用户推送消息
     * @return
     */
    @RequestMapping("/sendMessageByAll")
    @ResponseBody
    public  String sendMessageByAll(@RequestParam Map<String, Object> contents){
        myMessageHandler.sendMessageToAllUsers(contents.get("msg").toString());
        return "success";
    }
}
