#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d mondo-sam ]; then
  # the --branch option is only support by git 1.7.10+
  git clone https://github.com/FTSRG/mondo-sam.git
fi
cd mondo-sam
git checkout v0.1.0
mvn clean install -DskipTests
cd ..
