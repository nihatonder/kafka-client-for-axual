package com.nonder.camel;

import com.nonder.model.AvroBuilder;
import io.axual.client.example.schema.Order;
import io.axual.client.example.schema.OrderKey;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;

@ApplicationScoped
public class KafkaProducer extends RouteBuilder {
    @Inject
    AvroBuilder avroBuilder;

    @Override
    public void configure() {
        from("timer:avro?period=10000") // Trigger every 10 seconds, adjust as needed
                .process(exchange -> {
                    // Create Avro key object
                    OrderKey key = avroBuilder.buildOrderKey();
                    exchange.setProperty("AvroKey", key);

                    // Create Avro value object
                    Order value = avroBuilder.buildOrder(key, "XYZ10", 3);
                    exchange.setProperty("AvroValue", value);
                })
                .setHeader(KafkaConstants.KEY, simple("${exchangeProperty.AvroKey}"))
                .setBody(simple("${exchangeProperty.AvroValue}"))
                .to("kafka:{{axual.topic.name}}?")
                .log("\nMessage Produced: \nTopic: {{axual.topic.name}},  \nKey: ${exchangeProperty.AvroKey}, \nValue: ${exchangeProperty.AvroValue}");
    }
}