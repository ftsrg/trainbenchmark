#!/bin/bash

NEO4J_VERSION=3.3.0

cd "$( cd "$( dirname "$0" )" && pwd )/../"
rm -rf neo4j-server
wget -q https://neo4j.com/artifact.php?name=neo4j-community-$NEO4J_VERSION-unix.tar.gz -O neo4j.tar.gz
tar xf neo4j.tar.gz
mv neo4j-community-$NEO4J_VERSION neo4j-server
