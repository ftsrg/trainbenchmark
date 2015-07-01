#!/bin/bash
cd "$( cd "$( dirname "$0" )" && pwd )"

../../mondo-sam/reporting/convert_results.py --source ../../trainbenchmark/results/json/ --jsonfile ../../trainbenchmark/results/results.json --csvfile ../../trainbenchmark/results/results.csv
