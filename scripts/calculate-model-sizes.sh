#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../models"

wc -l railway-batch-*-metamodel.ttl
wc -l railway-inject-*-metamodel.ttl
wc -l railway-repair-*-metamodel.ttl
