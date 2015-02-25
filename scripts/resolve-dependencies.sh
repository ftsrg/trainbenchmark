#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../.."

if [ ! -d neo4j-shell-tools ]; then
  git clone https://github.com/szarnyasg/neo4j-shell-tools
fi
cd neo4j-shell-tools
scripts/build.sh
cd ..

if [ ! -d rdf-graph-drivers ]; then
  git clone https://github.com/FTSRG/rdf-graph-drivers
fi
cd rdf-graph-drivers
scripts/resolve-dependencies.sh
scripts/build.sh
mvn clean install -P fourstore -DskipTests
cd ..
