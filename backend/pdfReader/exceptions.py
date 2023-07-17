
class KafkaUploadException(Exception):
    def __init__(self, name: str):
        self.name = name

