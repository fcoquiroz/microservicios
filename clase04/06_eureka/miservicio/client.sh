#!/usr/bin/env bash

dir="./build/libs/client$1"

mkdir -p $dir
cp build/libs/miservicio.jar $dir

cd $dir

appEnv="devclient$1"

echo "Running with $appEnv"

java -jar miservicio.jar --spring.profiles.active="$appEnv"

cd ../../..

