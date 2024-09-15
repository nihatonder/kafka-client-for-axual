package com.nonder.config;

import com.nonder.model.KafkaComponentFactory;
import io.smallrye.config.ConfigMapping;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.camel.component.kafka.KafkaComponent;

public class Config {
    @Inject
    KafkaConfig kafkaConfig;

    @Named("kafka")
    @ApplicationScoped
    public KafkaComponent kafka() {
        KafkaComponentFactory componentFactory = new KafkaComponentFactory();
        return componentFactory.createKafkaComponent(kafkaConfig);
    }

    @ConfigMapping(prefix = "kafka", namingStrategy = ConfigMapping.NamingStrategy.VERBATIM)
    public interface KafkaConfig extends ConfigProperties {}
}