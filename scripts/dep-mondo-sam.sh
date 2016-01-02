#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d mondo-sam ]; then
  # the --branch option is only support by git 1.7.10+
  git clone https://github.com/FTSRG/mondo-sam.git --depth=1
fi
cd mondo-sam
mvn clean install -DskipTests
cd ..
