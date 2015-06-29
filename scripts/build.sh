#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

export MAVEN_OPTS="-Xmx2G"

mvn clean install -P core,emf,graph,rdf,sql,drools,eclipseocl,emfincquery,java,jena,mysql,neo4j,orientdb,sesame,virtuoso,allegro --fail-at-end
#,memsql
