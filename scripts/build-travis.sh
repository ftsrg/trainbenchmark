#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

./scripts/build-artifacts.sh

echo Running quiet build.
time mvn clean install --quiet -P core,emf,graph,rdf,sql,java,drools,emfincquery,neo4j,fourstore,jena,sesame,mysql,allegro,virtuoso,memsql -DskipTests || exit 1
echo Build finished.
