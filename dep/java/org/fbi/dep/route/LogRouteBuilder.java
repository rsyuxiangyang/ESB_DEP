package org.fbi.dep.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO ��־��¼
 */
public class LogRouteBuilder extends RouteBuilder {

    private static Logger logger = LoggerFactory.getLogger(LogRouteBuilder.class);

    @Override
    public void configure() throws Exception {

        logger.info("��ʼ�����־����·�� queue.dep.log.in ");
        // ���׶��� ���� ͨ�ýӿ�
        from("jms:queue:queue.dep.log.in")
                .routeId("������־")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

            }
        });
    }
}
