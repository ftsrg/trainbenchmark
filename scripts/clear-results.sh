#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

rm -rf results
mkdir -p results/completed
mkdir -p results/json
touch results/completed/.gitignore
