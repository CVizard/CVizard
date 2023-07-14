from kafka_connector import connect_kafka_producer, connect_kafka_consumer
from text_cleaner import anonymize_text, Engine


INPUT_TOPIC_NAME = 'pdf-text'
OUTPUT_TOPIC_NAME = 'cleaned-text'
BOOTSTRAP_SERVERS = ['kafka:29092']


def main():
    producer = connect_kafka_producer(BOOTSTRAP_SERVERS)
    consumer = connect_kafka_consumer(BOOTSTRAP_SERVERS, INPUT_TOPIC_NAME)

    for msg in consumer:
        text = msg.value.decode('utf-8')
        anonymized_text = anonymize_text(text, Engine.PL)
        producer.send(OUTPUT_TOPIC_NAME, anonymized_text.encode('utf-8'))


if __name__ == '__main__':
    main()