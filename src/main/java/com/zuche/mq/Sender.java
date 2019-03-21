package com.zuche.mq;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-20 19:58
 */
public class Sender extends ConnectionChannel {
    /**
     * 持久化 队列 名称
     */
    private String queueName;

    public Sender(String routingKey) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = "queue_topic";
    }

    public Sender(String routingKey,String queueName) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName=queueName;
    }


    public void sendMessage(byte[] bodys) throws IOException {
        //声明一个队列 -- 持久化
        channel.queueDeclare(queueName,true,false,false,null);

        //设置通道预取计数
        channel.basicQos(1);

        //将消息队列绑定到Exchange
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);

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
