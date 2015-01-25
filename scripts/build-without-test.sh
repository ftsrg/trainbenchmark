#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

export MAVEN_OPTS="-XX:MaxPermSize=128M"
mvn clean install -DskipTests
