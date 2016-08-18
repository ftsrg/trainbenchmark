#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../"

./gradlew build -x test --continue
