#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

export MAVEN_OPTS="-XX:MaxPermSize=128M"

./scripts/build-artifacts.sh

mvn clean install -P core,emf,graph,rdf,sql,java,drools,emfincquery,neo4j,jena,sesame,mysql --fail-at-end
#,allegro,virtuoso
