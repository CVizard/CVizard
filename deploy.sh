#!/bin/bash

docker-compose down

rm -rf Minionki2/

cd Minionki2/

docker-compose build 

docker-compose up
