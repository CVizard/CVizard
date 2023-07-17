from kafka_connector import connect_kafka_producer, connect_kafka_consumer
from text_cleaner import anonymize_text, Engine
import os
from kafka import KafkaProducer, KafkaConsumer


input_topic_name = os.environ['PDF_TEXT_TOPIC']
output_topic_name = os.environ['CLEANED_TEXT_TOPIC']
bootstrap_servers = [os.environ['BOOTSTRAP_SERVERS']]


def main():
    producer = KafkaProducer(bootstrap_servers=bootstrap_servers, api_version=(0, 10))
    consumer = KafkaConsumer(input_topic_name, bootstrap_servers=bootstrap_servers, api_version=(0, 10))

    for msg in consumer:
        text = msg.value.decode('utf-8')
        key = msg.key
        anonymized_text = anonymize_text(text, Engine.PL)
        producer.send(output_topic_name, value=anonymized_text.encode('utf-8'), key=key)


if __name__ == '__main__':
    main()