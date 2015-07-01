#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

#echo Running quiet build.
time mvn clean install -P core,emf,graph,rdf,sql,drools,eclipseocl,emfincquery,java,jena,mysql,neo4j,sesame || exit 1
# orientdb
#echo Build finished.
