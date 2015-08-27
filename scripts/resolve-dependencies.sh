#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d neo4j-shell-tools ]; then
  git clone https://github.com/jexp/neo4j-shell-tools
fi
cd neo4j-shell-tools
mvn clean install -DskipTests
cd ..
if [ ! -d mondo-sam ]; then
  git clone https://github.com/FTSRG/mondo-sam.git --branch issue-9
fi
cd mondo-sam
mvn clean install -DskipTests
cd ..
