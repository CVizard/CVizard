import uuid
import logging
from fastapi import (
    FastAPI,
    UploadFile, 
    Request, 
    status,
    Request
    )
from fastapi.responses import JSONResponse
from pdf_reader import pdf_to_text_tesseract
from kafka_connector import connect_kafka_producer
from exceptions import KafkaUploadException
import os


bootstrap_servers = os.environ.get('BOOTSTRAP_SERVERS')
topic_name = os.environ.get('PDF_TEXT_TOPIC')


app = FastAPI()
kafka = connect_kafka_producer(bootstrap_servers)


@app.exception_handler(KafkaUploadException)
async def kafka_exception_handler(request: Request, exc: KafkaUploadException):
    return JSONResponse(
        status_code=500,
        content={"message": f"Something went wrong {exc.name}"},
    )


@app.post("/upload")
async def upload_pdf_file(pdf_file: UploadFile):
    contents = await pdf_file.read()
    text = pdf_to_text_tesseract(contents)
    id = f"{uuid.uuid4()}"
    try:
        future = kafka.send(topic_name, key=id.encode("utf-8"), value=text.encode('utf-8'))
        metadata = future.get(timeout=10)
        return {"file_id": id}
    except Exception as e:
        logging.error(f"could not add event to kafka error has been thrown {e}")
        raise KafkaUploadException(name="error")