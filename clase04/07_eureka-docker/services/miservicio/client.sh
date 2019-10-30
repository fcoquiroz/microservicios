#!/usr/bin/env bash

dir="./build/libs/client$1"

mkdir -p $dir
cp build/libs/eureka_client.jar $dir

cd $dir

appEnv="devclient$1"

echo "Running with $appEnv"

java -jar eureka_client.jar --spring.profiles.active="$appEnv"

cd ../../..

