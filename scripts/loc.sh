#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

cloc --match-f '.*\.java' --exclude-dir hu.bme.mit.trainbenchmark.emf.model,hu.bme.mit.trainbenchmark.benchmark.emfincquery.patterns/ .