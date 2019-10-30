#!/usr/bin/env sh

docker run -p 5672:5672 -p 15672:15672 --rm -d rabbitmq:3.6.12-management-alpine
