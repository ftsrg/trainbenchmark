#!/bin/bash

pushd `dirname $0` > /dev/null

cd ../hu.bme.mit.trainbenchmark.analyze

rm figure/* namedFigs/*

#scenarios="XForm User"
scenarios="Batch"
for scenario in ${scenarios[@]}; do
    if [[ $scenario == "Batch" || $scenario == "XForm" ]]; then
        Rscript -e "library(knitr); knit('plotLines.rhtml')"
    elif [[ $scenario == "User" ]]; then
        ./qRes.sh
        Rscript -e "library(knitr); knit('plotLines-user.rhtml')"
    fi
done

popd > /dev/null
