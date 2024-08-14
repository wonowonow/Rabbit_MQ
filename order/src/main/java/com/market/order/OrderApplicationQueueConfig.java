package com.market.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationQueueConfig {

    @Value("${message.exchange}")
    private String exchange;

    @Value("${message.queue.product}")
    private String queueProduct;

    @Value("${message.queue.payment}")
    private String queuePayment;

    // Admin UI 가 아닌 Spring 으로 Exchange, Queue, Binding 설정
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queueProduct() {
        return new Queue(queueProduct);
    }

    @Bean
    public Queue queuePayment() {
        return new Queue(queuePayment);
    }

    @Bean
    public Binding bindingProduct() {
        return BindingBuilder.bind(queueProduct())
                             .to(exchange())
                             .with(queueProduct);
    }

    @Bean
    public Binding bindingPayment() {
        return BindingBuilder.bind(queuePayment())
                             .to(exchange())
                             .with(queuePayment);
    }
}
