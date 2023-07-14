from kafka_connector import connect_kafka_producer, connect_kafka_consumer
from text_cleaner import anonymize_text, Engine
import os


input_topic_name = os.environ.get('INPUT_TOPIC_NAME')
output_topic_name = os.environ.get('OUTPUT_TOPIC_NAME')
bootstrap_servers = [os.environ.get('BOOTSTRAP_SERVERS')]


def main():
    producer = connect_kafka_producer(bootstrap_servers)
    consumer = connect_kafka_consumer(bootstrap_servers, input_topic_name)

    for msg in consumer:
        text = msg.value.decode('utf-8')
        anonymized_text = anonymize_text(text, Engine.PL)
        producer.send(output_topic_name, anonymized_text.encode('utf-8'))


if __name__ == '__main__':
    main()