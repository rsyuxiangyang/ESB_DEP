package org.fbi.dep.component.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * 发布订阅
 * byte smessage
 */
public class JmsTopicBytesClient {

    private static final Logger logger = LoggerFactory.getLogger(JmsTopicBytesClient.class);
    private static String ACTIVEMQ_URL = PropertyManager.getProperty("dep.activemq.brokerURL");
    private static String ACTIVEMQ_GUEST_USERNAME = PropertyManager.getProperty("dep.activemq.guest.username");
    private static String ACTIVEMQ_GUEST_PASSWORD = PropertyManager.getProperty("dep.activemq.guest.password");

    public void sendTopicMsg(String topicName, String requestMsg) {
        Connection connection = null;
        Session session = null;
        try {
            ConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory(
                    ACTIVEMQ_GUEST_USERNAME, ACTIVEMQ_GUEST_PASSWORD, ACTIVEMQ_URL);
            connection = queueConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Topic topic = session.createTopic(topicName);
            MessageProducer producer = session.createProducer(topic);
            //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);

            TextMessage message = session.createTextMessage();
            message.setText(requestMsg);
            producer.send(message);
            logger.info("Sent topic message: " + message.getText());

            session.close();
            connection.close();
        } catch (JMSException jmse) {
            throw new RuntimeException("MQ连接异常。");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (session != null) {
                    session.close();
                }
            } catch (JMSException jmsex) {
                logger.error("jms close error", jmsex);
            }
        }
    }
}
