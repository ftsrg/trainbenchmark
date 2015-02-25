#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virt_sesame2.jar -DgroupId=virtuoso \
                     -DartifactId=virtuoso-sesame2 -Dversion=2.7.3 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virtjdbc4.jar -DgroupId=com.virtuoso.virtjdbc4 \
                     -DartifactId=virtjdbc4 -Dversion=3.0 \
                     -Dpackaging=jar
export MAVEN_OPTS="-XX:MaxPermSize=128M"
mvn clean install -P core,emf,graph,rdf,sql,java,drools,emfincquery,neo4j,jena,sesame,mysql -DskipTests
