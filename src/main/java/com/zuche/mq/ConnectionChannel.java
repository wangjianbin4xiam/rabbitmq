package com.zuche.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Exchange 类型：Exchange分发消息时根据类型的不同分发策略有区别，目前共四种类型：direct、fanout、topic、headers
 * @author: wangjb
 * @create: 2019-03-20 19:39
 */
public abstract class ConnectionChannel {
    /**
     * RabbitMQ 的主机信息
     */
    protected static final String HOST = "127.0.0.1";
    protected static final String USER = "guest";
    protected static final String PASSWORD = "guest";

    /**
     * 连接
     */
    protected Connection connection;
    /**
     * 连接通道
     */
    protected Channel channel;
    /**
     * 连接通道路由地址
     */
    protected String routingKey;
    /**
     * 交换机名称
     */
    protected static final String EXCHANGE_NAME = "cms";

    public ConnectionChannel() {
    }

    public ConnectionChannel(String routingKey) throws IOException, TimeoutException {
        this.routingKey = routingKey;

        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWORD);
        //默认端口
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 声明一个连接
        this.connection = factory.newConnection();
        // 声明消息通道
        this.channel = this.connection.createChannel();

        /*
         * 声明转发器 - 定义一个交换机
         * 参数1：交换机名称
         * 参数2：交换机类型
         * 参数3：交换机持久性，如果为true则服务器重启时不会丢失
         * 参数4：交换机在不被使用时是否删除 参数5：交换机的其他属性
         */
        this.channel.exchangeDeclare(EXCHANGE_NAME,"topic",false,false,null);
    }

    public void close() throws IOException, TimeoutException {
        this.channel.close();
        this.connection.close();
    }
}
