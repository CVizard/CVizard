FROM python:3.11-slim-buster

WORKDIR /app

COPY requirements.txt .
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

RUN apt-get update \
  && apt-get -y install netcat gcc \
  && apt-get clean

RUN pip install --upgrade pip
RUN pip install -r requirements-dev.txt

COPY . . 

COPY ./entrypoint.sh .
RUN chmod 777 /app/entrypoint.sh \                                              
    && ln -s /app/entrypoint.sh / \
    && chmod +x /app/entrypoint.sh
CMD ["/app/entrypoint.sh"]