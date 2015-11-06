#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d mondo-sam ]; then
  git clone --branch v0.1.0 https://github.com/FTSRG/mondo-sam
fi
cd mondo-sam
mvn clean install -DskipTests
cd ..
