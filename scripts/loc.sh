#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

scripts/cloc-1.62.pl --match-f '.*\.java' --exclude-dir hu.bme.mit.trainbenchmark.benchmark.emfincquery.patterns/ .