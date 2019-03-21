package com.zuche.mq;

import java.io.UnsupportedEncodingException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 09:42
 */
public class ReceiverHandler {

    public static void HandleMessage(byte[] bytes,String routingKey) throws UnsupportedEncodingException {
        if(null == bytes || bytes.length == 0){
            return;
        }
        String msg = new String(bytes,"UTF-8");
        System.out.println("===="+msg+"====");
    }
}
