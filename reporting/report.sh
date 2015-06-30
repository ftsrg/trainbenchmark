#!/bin/bash
cd "$( cd "$( dirname "$0" )" && pwd )"

python3 ../../mondo-sam/reporting/report.py --source ../../trainbenchmark/results/results.csv \
--output ../../trainbenchmark/diagrams/ --config ../../trainbenchmark/reporting/config.json
