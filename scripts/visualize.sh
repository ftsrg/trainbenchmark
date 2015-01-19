#*******************************************************************************
# Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   Benedek Izso - initial API and implementation
#   Gabor Szarnyas - initial API and implementation
#*******************************************************************************
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
