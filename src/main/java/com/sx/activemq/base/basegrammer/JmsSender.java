package com.sx.activemq.base.basegrammer;


import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by uwayxs on 2018/1/15.
 */
public class JmsSender {
    public static void main(String[] args) {
        //创建连接工厂
        ActiveMQConnectionFactory connectionFactory
                = new ActiveMQConnectionFactory("tcp://192.168.137.100:61616");
        Connection connection = null;
        try {
            //创建连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            /**
             * 创建会话
             * 第一个参数 boolen--是否为事务性会话
             * 第二个参数 消息签收方式
             */
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            //创建Destination(queue或者topic)
            Destination destination = session.createTopic("topic-first");
            //创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            //创建消息
            TextMessage sx = session.createTextMessage("daisishab");
            //发送消息
            producer.send(sx);
            //事务提交(事务性会话)
            session.commit();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
