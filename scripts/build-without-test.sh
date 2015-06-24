#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

export MAVEN_OPTS="-Xmx2G"
./scripts/build-artifacts.sh

if [[ $1 == "--travis" ]]; then
	QUIET="--quiet"
fi

time mvn clean install $QUIET -P core,emf,graph,rdf,sql,java,drools,emfincquery,neo4j,fourstore,jena,sesame,mysql,allegro,virtuoso,eclipseocl,memsql -DskipTests
if [[ $1 == "travis" && $? != 0 ]]; then
	exit 1
fi

echo Build finished.
