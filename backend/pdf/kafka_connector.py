from kafka import KafkaProducer, KafkaConsumer


def connect_kafka_producer(bootstrap_servers: list[str]) -> KafkaProducer:
    return KafkaProducer(bootstrap_servers=bootstrap_servers, api_version=(0, 10))


def connect_kafka_consumer(bootstrap_servers: list[str], topic_name: str) -> KafkaConsumer:
    return KafkaConsumer(topic_name, bootstrap_servers=bootstrap_servers)
