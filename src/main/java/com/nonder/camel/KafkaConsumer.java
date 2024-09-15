package com.nonder.camel;

import io.axual.client.example.schema.Order;
import io.axual.client.example.schema.OrderKey;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificData;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

@ApplicationScoped
public class KafkaConsumer extends RouteBuilder {

    @Override
    public void configure() {

        from("kafka:{{axual.topic.name}}")
                .process(exchange -> {
                    GenericRecord keyRecord = exchange.getIn().getHeader(KafkaConstants.KEY, GenericRecord.class);
                    GenericRecord bodyRecord = exchange.getIn().getBody(GenericRecord.class);

                    OrderKey key = (OrderKey) SpecificData.get().deepCopy(OrderKey.getClassSchema(), keyRecord);
                    Order value = (Order) SpecificData.get().deepCopy(Order.getClassSchema(), bodyRecord);

                    exchange.setProperty("key", key);
                    exchange.setProperty("value", value);
                })
                .log("\nMessage Consumed:\nTopic: {{axual.topic.name}} \nKey: ${exchangeProperty.key}\nValue: ${exchangeProperty.value}");
    }
}
