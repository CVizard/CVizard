from fastapi import FastAPI, UploadFile, File
from pdf_reader import pdf_to_text_tesseract
from kafka_connector import connect_kafka_producer


BOOTSTRAP_SERVERS = ['localhost:29092']
TOPIC_NAME = 'pdf-text'


app = FastAPI()
kafka = connect_kafka_producer(BOOTSTRAP_SERVERS)


@app.post("/upload")
async def upload_pdf_file(pdf_file: UploadFile = File(...)):
    contents = await pdf_file.read()
    text = pdf_to_text_tesseract(contents)
    kafka.send(TOPIC_NAME, text.encode('utf-8'))
    return {"filename": pdf_file.filename}


    
    