package com.sly.hybrid.rabbitmq.exchange;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 交换机
 *
 * @author SLY
 * @date 2022/1/5
 */
@Configuration
public class ExchangeConfig {

    public static final String LAZY_EXCHANGE = "Ex.LazyExchange";

    @Bean
    public TopicExchange delayExchange() {
        //设置交换机支持延迟消息推送
        TopicExchange exchange = new TopicExchange(LAZY_EXCHANGE, true, false);
        exchange.setDelayed(true);
        return exchange;
    }
}
