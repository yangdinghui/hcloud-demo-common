package hcloud.demo.utils;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

@Component
@ConditionalOnClass({JmsTemplate.class})
public class ActiveMQProducer {
    private static final Logger log = LoggerFactory.getLogger(ActiveMQProducer.class);
    private static final String ACTIVE_MQ_QUEUE = "QUEUE";
    private static final String ACTIVE_MQ_TOPIC = "TOPIC";
    private static JmsTemplate jmsTemplate;

    @Autowired
    public ActiveMQProducer(JmsTemplate jmsTemplate) {
        jmsTemplate = jmsTemplate;
    }

    public static void sendQueue(String topic, Object message) {
        send("QUEUE", topic, message);
    }

    public static void sendTopic(String topic, Object message) {
        send("TOPIC", topic, message);
    }

    public static void send(String type, String topic, Object message) {
        Destination destination = chooseDestination(type, topic);
        send(destination, message);
    }

    public static void send(Destination destination, Object message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    private static Destination chooseDestination(String type, String topic) {
        byte var3 = -1;
        switch (type.hashCode()) {
            case 77406449:
                if (type.equals("QUEUE")) {
                    var3 = 0;
                }
                break;
            case 80008463:
                if (type.equals("TOPIC")) {
                    var3 = 1;
                }
        }

        switch (var3) {
            case 0:
                return new ActiveMQQueue(topic);
            case 1:
                return new ActiveMQTopic(topic);
            default:
                throw new RuntimeException("不支持该类型的消息模式");
        }
    }
}
