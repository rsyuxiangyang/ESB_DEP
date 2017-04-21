package org.fbi.dep.startup;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.spi.CamelContextNameStrategy;
import org.apache.camel.util.jndi.JndiContext;
import org.fbi.dep.component.netty.*;
import org.fbi.dep.route.*;
import org.fbi.dep.util.PropertyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.ConnectionFactory;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 12-2-13
 * Time: ����11:05
 * To change this template use File | Settings | File Templates.
 */
public class Bootstrap {

    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);
    private static String ACTIVE_MQ_USER_NAME = PropertyManager.getProperty("dep.activemq.admin.username");
    private static String ACTIVE_MQ_PASSWORD = PropertyManager.getProperty("dep.activemq.admin.password");
    private static String ACTIVE_MQ_BORKER_URL = PropertyManager.getProperty("dep.activemq.brokerURL");
//    private static String UNIONPAY_SBS_SOCKET_PORT = PropertyManager.getProperty("dep.localhost.unionpay.sbs.port");
//    private static String UNIONPAY_SOCKET_PORT = PropertyManager.getProperty("dep.localhost.unionpay.port");
    private static String SBS_SOCKET_PORT = PropertyManager.getProperty("dep.localhost.sbs.port");
    private static String SMS_SOCKET_PORT = PropertyManager.getProperty("dep.localhost.sms.port");
    private static String SBS_HTTP_PORT = PropertyManager.getProperty("dep.localhost.sbs.http.port");
    private static String SBS_HTTP_NEW_PORT = PropertyManager.getProperty("dep.localhost.sbs.http.new.port");
    private static String FEB_HTTP_PORT = PropertyManager.getProperty("dep.localhost.feb.http.port");

    public static void main(String[] args) throws Exception {

        JndiRegistry registry = new JndiRegistry(new JndiContext());

        MbpSktMessageDecoder nettyMbpSktMessageDecoder = new MbpSktMessageDecoder();
        MbpSktMessageEncoder nettyMbpSktMessageEncoder = new MbpSktMessageEncoder();
        registry.bind("mbp-decoder", nettyMbpSktMessageDecoder);
        registry.bind("mbp-encoder", nettyMbpSktMessageEncoder);
        DepSktMessageDecoder nettyDepSktMessageDecoder = new DepSktMessageDecoder();
        DepSktMessageEncoder nettyDepSktMessageEncoder = new DepSktMessageEncoder();
        registry.bind("skt-decoder", nettyDepSktMessageDecoder);
        registry.bind("skt-encoder", nettyDepSktMessageEncoder);

        DefaultCamelContext context = new DefaultCamelContext(registry);
        context.setName("Linking-1");

        //DefaultCamelContext context2 = new DefaultCamelContext();
        //context2.setName("Linking-2");
        //context2.start();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVE_MQ_USER_NAME, ACTIVE_MQ_PASSWORD,
                ACTIVE_MQ_BORKER_URL);
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        RouteBuilder coreRouteBuilder = new CoreRouteBuilder();
        RouteBuilder appRouteBuilder = new AppRouteBuilder();
        RouteBuilder objectRouteBuilder = new ObjectRouteBuilder();
        RouteBuilder clientRouteBuilder = new ClientRouteBuilder();
        RouteBuilder mbpRouteBuilder = new MbpDztzRouteBuilder();
//        RouteBuilder upSktRouteBuilder = new PayoutDirectSktRouteBuilder(UNIONPAY_SOCKET_PORT);
//        RouteBuilder upSbsRouteBuilder = new PayoutIndirectSktRouteBuilder(UNIONPAY_SBS_SOCKET_PORT);
        RouteBuilder sbsSktRouteBuilder = new SbsSktRouteBuilder(SBS_SOCKET_PORT);
        // ��������û������������
        RouteBuilder smsSktRouteBuilder = new SmsSktRouteBuilder(SMS_SOCKET_PORT);
        RouteBuilder sbsHttpRouteBuilder = new SbsHttpRouteBuilder(SBS_HTTP_PORT);
        RouteBuilder sbsHttpNewRouteBuilder = new SbsHttpNewRouteBuilder(SBS_HTTP_NEW_PORT);
        RouteBuilder febHttpRouteBuilder = new FebHttpRouteBuilder(FEB_HTTP_PORT);
        try {
            logger.info("CamelContext��ʼ���·��...");
            context.addRoutes(coreRouteBuilder);
            context.addRoutes(appRouteBuilder);
            context.addRoutes(objectRouteBuilder);
            context.addRoutes(clientRouteBuilder);
            context.addRoutes(mbpRouteBuilder);
//            context.addRoutes(upSktRouteBuilder);
//            context.addRoutes(upSbsRouteBuilder);
            context.addRoutes(sbsSktRouteBuilder);
            context.addRoutes(smsSktRouteBuilder);
            context.addRoutes(sbsHttpRouteBuilder);
            context.addRoutes(sbsHttpNewRouteBuilder);
            context.addRoutes(febHttpRouteBuilder);
            logger.info("CamelContext��ʼ����...");
            context.start();
            logger.info("CamelContext�ѳɹ�����...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
