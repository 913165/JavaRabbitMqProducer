package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JavaMessageProducer {
    private final static String QUEUE_NAME = "queue001";

    private final static String HOST_ADDR="20.228.126.239";
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST_ADDR);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            Map<String, Object> args = new HashMap<String, Object>();
            channel.queueDeclare(QUEUE_NAME, false, false, false, args);
            String message = "Hello World!";
            for (int i = 0; i < 1000; i++) {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            }
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}