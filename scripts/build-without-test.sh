#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."
export MAVEN_OPTS="-XX:MaxPermSize=128M"
./scripts/build-artifacts.sh

if [[ $1 == "--travis" ]]; then
	QUIET="--quiet"
fi

time mvn clean install $QUIET -P core,emf,graph,rdf,sql,java,drools,emfincquery,neo4j,fourstore,jena,sesame,mysql,allegro,virtuoso,eclipseocl -DskipTests
if [[ $1 == "travis" && $? != 0 ]]; then
	exit $?
fi

echo Build finished.
