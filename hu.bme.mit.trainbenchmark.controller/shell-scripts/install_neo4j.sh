#!/bin/bash
cd ../../../
git clone https://github.com/szarnyasg/neo4j-shell-tools
cd neo4j-shell-tools
scripts/build.sh
cd ..

git clone https://github.com/FTSRG/rdf-graph-drivers
cd rdf-graph-drivers
scripts/resolve-dependencies.sh
scripts/build.sh
mvn clean install -P fourstore -DskipTests
cd ..
