#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/.."

cd results
git clean -f -x -d .
