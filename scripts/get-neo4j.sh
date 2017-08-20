#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"
wget https://neo4j.com/artifact.php?name=neo4j-community-3.2.3-unix.tar.gz -O neo4j.tar.gz
tar xf neo4j.tar.gz
mv neo4j-community-3.2.3 neo4j-server
