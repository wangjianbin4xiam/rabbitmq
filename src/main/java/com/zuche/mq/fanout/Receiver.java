package com.zuche.mq.fanout;

import com.rabbitmq.client.*;
import com.zuche.mq.ConnectionChannel;
import com.zuche.mq.ReceiverHandler;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-22 11:49
 */
public class Receiver extends ConnectionChannel {

    private String queueName;

    public Receiver(String routingKey,String exchangeType,String queueName,String exchangeName) throws IOException, TimeoutException {
        super(routingKey,exchangeType,exchangeName);
        this.queueName = queueName;
    }

    public void getMesssage() throws IOException, InterruptedException {
        //queueName = channel.queueDeclare().getQueue();
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);

        //定义消费者
//        final Consumer c = new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
//                                       byte[] body) throws IOException {
//
//                String message = new String(body,"UTF-8");
//                System.out.println("消费者获取到的消息 == "+message);
//            }
//        };
//        channel.basicConsume(queueName,false,c);


        //声明消费者 - 用来缓存服务器推送过来的消息
        QueueingConsumer consumer = new QueueingConsumer(channel);

        /**
         * 监听队列，手动返回完成  - 为channel声明一个consumer，服务器会推送消息
         * 参数1：持久化队列名称
         * 参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。  可以通过channel.basicAck手动回复ack
         * 参数3：消费者
         */
        channel.basicConsume(queueName,false,consumer);

        while (true){
            ///获取消息，如果没有消息，这一步将会一直阻塞
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            //获取消息主体数据
            byte[] bytes = delivery.getBody();

            //路由地址
            String routingKey = delivery.getEnvelope().getRoutingKey();

            //对消息主体进行处理
            ReceiverHandler.HandleMessage(bytes,routingKey);

            //确认消息已经收到 - 回复ack包，如果不回复，消息不会在服务器删除
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
