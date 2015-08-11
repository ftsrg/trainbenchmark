#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d mondo-sam ]; then
  git clone https://github.com/FTSRG/mondo-sam.git
fi
cd mondo-sam
mvn clean install -DskipTests
cd ..
