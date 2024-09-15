package com.nonder.config;

import io.apicurio.registry.serde.avro.AvroKafkaDeserializer;
import io.apicurio.registry.serde.avro.AvroKafkaSerializer;

public interface ConfigProperties {
    String groupId();
    String clientId();
    String saslJaasConfig();
    String brokers();
    String securityProtocol();
    String saslMechanism();
    String registryURL();
    default String keySerializer() {return AvroKafkaSerializer.class.getName();};
    default String valueSerializer(){return AvroKafkaSerializer.class.getName();};
    default String  keyDeserializer(){return AvroKafkaDeserializer.class.getName();};
    default String  valueDeserializer(){return AvroKafkaDeserializer.class.getName();};
}