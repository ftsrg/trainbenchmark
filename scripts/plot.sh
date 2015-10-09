#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../reporting"

R -f plot.R
