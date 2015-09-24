#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

rm -rf results/completed
mkdir results/completed
touch results/completed/.gitignore
