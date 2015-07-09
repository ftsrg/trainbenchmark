#!/bin/bash
mvn install:install-file -Dfile=../../hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virt_sesame2.jar -DgroupId=virtuoso \
                     -DartifactId=virtuoso-sesame2 -Dversion=2.7.3 \
                     -Dpackaging=jar
mvn install:install-file -Dfile=../../hu.bme.mit.trainbenchmark.benchmark.virtuoso/src/main/resources/virtjdbc4.jar -DgroupId=com.virtuoso.virtjdbc4 \
                     -DartifactId=virtjdbc4 -Dversion=3.0 \
                     -Dpackaging=jar
mvn clean install -f ../../pom.xml -P sesame -DskipTests
