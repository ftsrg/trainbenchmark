#!/bin/bash

mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virt_sesame2.jar -DgroupId=virtuoso \
                     -DartifactId=virtuoso-sesame2 -Dversion=2.7.3 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virtjdbc4.jar -DgroupId=com.virtuoso.virtjdbc4 \
                     -DartifactId=virtjdbc4 -Dversion=3.0 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/agraph-5.0.jar -DgroupId=com.franz \
                     -DartifactId=agraph -Dversion=5.0.0 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/openrdf-sesame-2.7.11-onejar.jar -DgroupId=org.openrdf.sesame \
                     -DartifactId=sesame-onejar -Dversion=2.7.11 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/commons-io-2.4.jar -DgroupId=org.apache.commons \
                     -DartifactId=commons.io -Dversion=2.4.0 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/commons-pool-1.5.6.jar -DgroupId=org.apache.commons \
                     -DartifactId=commons.pool -Dversion=1.5.6 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/commons-codec-1.3.jar -DgroupId=org.apache.commons \
                     -DartifactId=commons.codec -Dversion=1.3.0 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=./hu.bme.mit.trainbenchmark.benchmark.allegro/src/main/resources/json.jar -DgroupId=org.json \
                     -DartifactId=json -Dversion=1.0.0 \
                     -Dpackaging=jar
