# kafka-client-for-axual

This is a Quarkus 3.x project using Apache Camel Java DSL and Java 21. 
The project implements an Apache Kafka client using the Apache Camel Kafka component and connects to Axual, 
which is an Apache Kafka-based platform with additional features.

# Running

You need an Axual account to run this application. 
Additionally, you should have already created the application, topic, and schemas. 
The application and topic should also be properly configured.
When you run the application, it will start producing messages to Axual and consuming the same messages from Axual.
Please note that the current trial version of Axual does not support the Avro schema.

## Related Guides

- Camel Core ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/core.html)): Camel core functionality and basic Camel languages: Constant, ExchangeProperty, Header, Ref, Simple and Tokenize
- Camel Kafka ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/kafka.html)): Sent and receive messages to/from an Apache Kafka broker
- Camel Avro ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/avro.html)): Serialize and deserialize messages using Apache Avro binary data format
