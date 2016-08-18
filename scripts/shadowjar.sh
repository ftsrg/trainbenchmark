#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"

./gradlew build shadowJar -x test
