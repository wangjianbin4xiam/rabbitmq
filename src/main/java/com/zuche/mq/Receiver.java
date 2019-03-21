package com.zuche.mq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @title:
 * @Description:
 * @author: wangjb
 * @create: 2019-03-21 09:27
 */
public class Receiver extends ConnectionChannel{
    private String queueName;

    public Receiver(String routingKey) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName = "queue_topic";
    }

    public Receiver(String routingKey,String queueName) throws IOException, TimeoutException {
        super(routingKey);
        this.queueName=queueName;
    }

    public void getMessage() throws IOException, InterruptedException {
        //声明一个临时队列，该队列会在使用完比后自动销毁 - 非必需
        //临时队列提供了一种机制：当我们每次连接到Rabbit server的时候，我们都需要一个新的空的名字随机生成的队列，当我们断开连接的时候，队列能自动被删除
        //queueName = channel.queueDeclare().getQueue();

        //声明要关注的队列 - 非必需
        //channel.queueDeclare(queueName,true,false,false,null);

        //server push消息时的队列长度 - 同一时刻服务器只会发一条消息给消费者  - 非必需
        //channel.basicQos(1);

        //将消息队列绑定到Exchange - 将队列绑定到交换机 - 绑定一个routing key
        channel.queueBind(queueName,EXCHANGE_NAME,routingKey);

        //声明消费者 - 用来缓存服务器推送过来的消息
        //QueueingConsumer consumer = new QueueingConsumer(channel);
        //DefaultConsumer consumer = new DefaultConsumer(channel);

        /**
         * 监听队列，手动返回完成  - 为channel声明一个consumer，服务器会推送消息
         * 参数1：持久化队列名称
         * 参数2：是否发送ack包，不发送ack消息会持续在服务端保存，直到收到ack。  可以通过channel.basicAck手动回复ack
         * 参数3：消费者
         */
//        channel.basicConsume(queueName,false,consumer);
//
//        while (true){
//            ///获取消息，如果没有消息，这一步将会一直阻塞
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//
//            //获取消息主体数据
//            byte[] bytes = delivery.getBody();
//
//            //路由地址
//            String routingKey = delivery.getEnvelope().getRoutingKey();
//
//            //对消息主体进行处理
//            ReceiverHandler.HandleMessage(bytes,routingKey);
//
//            //确认消息已经收到 - 回复ack包，如果不回复，消息不会在服务器删除
//            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
//        }

        Consumer c = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                String message = new String(body,"UTF-8");
                System.out.println("==>新型DefaultConsumer 消费者获取到的信息<==="+message);
            }
        };
        channel.basicConsume(queueName,true,c);
    }
}
