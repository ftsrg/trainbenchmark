#!/bin/bash

mvn clean install -P core,emf,graph,rdf,java,neo4j,sesame --fail-at-end
