#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

mvn install:install-file \
       -Dfile=../hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/agraph-5.0.jar \
       -DgroupId=com.franz \
       -DartifactId=agraph -Dversion=5.0.0 \
       -Dpackaging=jar
mvn install:install-file \
       -Dfile=../hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/openrdf-sesame-2.7.11-onejar.jar \
       -DgroupId=org.openrdf.sesame \
       -DartifactId=sesame-onejar -Dversion=2.7.11 \
       -Dpackaging=jar
mvn install:install-file \
       -Dfile=../hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/json.jar \
       -DgroupId=org.json \
       -DartifactId=json -Dversion=1.0.0 \
       -Dpackaging=jar

