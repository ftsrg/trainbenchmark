#*******************************************************************************
# Copyright (c) 2010-2014, Gabor Szarnyas, Istvan Rath and Daniel Varro
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
# Gabor Szarnyas - initial API and implementation
#*******************************************************************************
#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

echo "[4s-boss]
discovery = sole
nodes = ${1:-127.0.0.1}

[trainbenchmark_cluster]
port = 7890" > /etc/4store.conf
