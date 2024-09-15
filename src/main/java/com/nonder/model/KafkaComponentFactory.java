package com.nonder.model;

import com.nonder.config.ConfigProperties;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConfiguration;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Boolean.TRUE;

@ApplicationScoped
public class KafkaComponentFactory {
    private static final String REGISTRY_URL_KEY = "apicurio.registry.url";
    private static final String REGISTRY_FIND_LATEST_KEY = "apicurio.registry.find-latest";

    public KafkaComponent createKafkaComponent(ConfigProperties configProperties) {
        KafkaComponent kafkaComponent = new KafkaComponent();
        kafkaComponent.setConfiguration(createConfig(configProperties));

        return kafkaComponent;
    }

    private KafkaConfiguration createConfig(ConfigProperties properties) {
        KafkaConfiguration configuration = new KafkaConfiguration();

        // Broker
        configuration.setBrokers(properties.brokers());
        configuration.setGroupId(properties.groupId());
        configuration.setClientId(properties.clientId());

        // Security
        configuration.setSecurityProtocol(properties.securityProtocol());
        configuration.setSaslMechanism(properties.saslMechanism());

        String saslJaasConfig = properties.saslJaasConfig();
        configuration.setSaslJaasConfig(saslJaasConfig);

        // Serializers and Deserializers
        configuration.setKeySerializer(properties.keySerializer());
        configuration.setKeyDeserializer(properties.keyDeserializer());
        configuration.setValueSerializer(properties.valueSerializer());
        configuration.setValueDeserializer(properties.valueDeserializer());

        // Additional Properties
        Map<String, Object> additionalProperties = new HashMap<>();
        additionalProperties.put(REGISTRY_URL_KEY, properties.registryURL());
        additionalProperties.put(REGISTRY_FIND_LATEST_KEY, TRUE);
        configuration.setAdditionalProperties(additionalProperties);

        return configuration;
    }
}