import org.apache.activemq.ActiveMQConnectionFactory;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-6-14
 * Time: ����9:27
 * To change this template use File | Settings | File Templates.
 */
public class JmsCcbvipsClientTest {

    private static final Logger logger = LoggerFactory.getLogger(JmsCcbvipsClientTest.class);
    private static String ACTIVEMQ_URL = PropertyManager.getProperty("dep.activemq.url");
    private static String ACTIVEMQ_GUEST_USERNAME = PropertyManager.getProperty("dep.activemq.guest.username");
    private static String ACTIVEMQ_GUEST_PASSWORD = PropertyManager.getProperty("dep.activemq.guest.password");
    private static int ACTIVEMQ_TIMEOUT = PropertyManager.getIntProperty("dep.activemq.request.timeout");

    public byte[] sendRecivMsg(byte[] tiaBuf) throws Exception {

        Connection connection = null;
        Session session = null;
        try {
            ConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory(
                    ACTIVEMQ_GUEST_USERNAME, ACTIVEMQ_GUEST_PASSWORD, ACTIVEMQ_URL);
            connection = queueConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination requestDestination = session.createQueue("queue.dep.core.ccbvips.fcdep");
            MessageProducer sender = session.createProducer(requestDestination);
            sender.setDeliveryMode(DeliveryMode.PERSISTENT);
            BytesMessage message = session.createBytesMessage();
            message.writeBytes(tiaBuf);
            message.setStringProperty("JMSX_CHANNELID", "900");
            message.setStringProperty("JMSX_APPID", "ccbvips");
            message.setStringProperty("JMSX_BIZID", "ccbvips");
            message.setStringProperty("JMSX_TXCODE", "ccbvips");
            message.setStringProperty("JMSX_USERID", "ccbvips");
            message.setStringProperty("JMSX_PASSWORD", "ccbvips");
            sender.send(message);
            String sentMsgID = message.getJMSMessageID();
            logger.info("MessageID : " + sentMsgID);
            String filter = "JMSCorrelationID = '" + sentMsgID + "'";
            Destination responseDestination = session.createQueue("queue.dep.core.fcdep.ccbvips");
            MessageConsumer receiver = session.createConsumer(responseDestination, filter);
            BytesMessage rtnMessage = (BytesMessage) receiver.receive(ACTIVEMQ_TIMEOUT);
            if (rtnMessage == null) {
                session.close();
                connection.close();
                throw new RuntimeException("��Ϣ���ճ�ʱ��");
            } else {
                session.close();
                connection.close();
                byte[] rtnBytes = new byte[(int) rtnMessage.getBodyLength()];
                rtnMessage.readBytes(rtnBytes);
                return rtnBytes;
            }
        } catch (JMSException jmse) {
            throw new RuntimeException("MQ�����쳣��");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
                if (session != null) {
                    session.close();
                    session = null;
                }
            } catch (JMSException jmsex) {
                throw new RuntimeException("MQ���ӹر��쳣��");
            }
        }
    }
}
