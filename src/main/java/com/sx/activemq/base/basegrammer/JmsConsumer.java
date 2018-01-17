package com.sx.activemq.base.basegrammer;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Created by uwayxs on 2018/1/16.
 */
public class JmsConsumer {
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory
                = new ActiveMQConnectionFactory("tcp://192.168.137.100:61616");
        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("topic-first");
          MessageConsumer consumer = session.createConsumer(destination);
//            TextMessage textMessage = (TextMessage)consumer.receive();
//            System.out.println(textMessage.getText());
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage( Message message) {
                    TextMessage textMessage = (TextMessage)message;
                    try {
                        System.out.println(textMessage.getText());
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread.sleep(Integer.MAX_VALUE);

            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
