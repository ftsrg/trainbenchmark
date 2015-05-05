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

[ -n "$FOURSTORE_CLUSTER_NAME" ] || . 4s-cluster-name.sh

FILENAME=$1

echo "Importing $FILENAME to $FOURSTORE_CLUSTER_NAME"
4s-import $FOURSTORE_CLUSTER_NAME $FILENAME
