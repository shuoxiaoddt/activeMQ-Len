package com.sx.activemq.base.spring.jms.sender;

import org.apache.activemq.broker.BrokerService;

public class DefineBrokerServer {

    public static void main(String[] args) {
        //自定义Actice broke
        BrokerService brokerService=new BrokerService();
        try {
            brokerService.setUseJmx(true);
            brokerService.addConnector("tcp://localhost:61616");
            brokerService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
