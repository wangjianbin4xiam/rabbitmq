package com.zuche.mq.fanout;

import com.zuche.mq.ConnectionChannel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:队列中，作为生产者，只关心将数据发送给交换器，其余的不关心
 * @author: wangjb
 * @create: 2019-03-22 11:41
 */
public class Sender extends ConnectionChannel {

    private String queueName;
    public Sender(String routingKey,String exchangerType,String queueName,String exchangeName) throws IOException, TimeoutException {
        super(routingKey,exchangerType,exchangeName);
        this.queueName = queueName;
    }

    public void sendMessage(byte[] bodys) throws IOException {
        //设置消息队列每次只向消费者发送一条
        channel.basicQos(1);
        /**
         * 发送消息到队列中
         * 参数1：交换机exchange名字，若为空则使用默认的exchange[]
         * 参数2：routing key - 路由地址
         * 参数3：其他的属性
         * 参数4：消息体
         */
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,bodys);
    }

}
