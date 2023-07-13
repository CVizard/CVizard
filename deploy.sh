#!/bin/bash

docker-compose down

rm -rf Minionki2/

git pull origin main

cd Minionki2/

docker-compose build 

docker-compose up
