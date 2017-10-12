#!/bin/bash

NEO4J_VERSION=3.2.5

cd "$( cd "$( dirname "$0" )" && pwd )/../"
wget https://neo4j.com/artifact.php?name=neo4j-community-$NEO4J_VERSION-unix.tar.gz -O neo4j.tar.gz
tar xf neo4j.tar.gz
mv neo4j-community-$NEO4J_VERSION neo4j-server
