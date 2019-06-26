package com.springbasetest;

import javax.jms.JMSException;
import javax.jms.Message;

public class MessageListener implements javax.jms.MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            Object obj=  message.getObjectProperty("product");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
