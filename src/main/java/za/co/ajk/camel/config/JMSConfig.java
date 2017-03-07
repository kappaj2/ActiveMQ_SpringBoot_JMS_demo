package za.co.ajk.camel.config;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.JmsTransactionManager;

import javax.jms.ConnectionFactory;

@Configuration
public class JMSConfig {

    @Bean
    public JmsTransactionManager createJmsTransactionManager(final ConnectionFactory connectionFactory) {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }

    @Bean
    public JmsComponent createJmsCcomponent(final ConnectionFactory connectionFactory, final JmsTransactionManager jmsTransactionManager, @Value("${max.concurrent.consumers}") final int maxConcurrentConsumers) {
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory);
        jmsComponent.setMaxConcurrentConsumers(maxConcurrentConsumers);
        return jmsComponent;
    }
}
