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

nodes=`grep nodes /etc/4store.conf | sed "s/ //"`
node_list=`echo $nodes | awk -F'=' '{print $2}'`
node_array=$(echo $node_list | tr ";" "\n")

for node in $node_array; do
	if [ $node = "localhost" ] || [ $node = "127.0.0.1" ]; then
		echo "Starting 4store on localhost"
		4s-boss
	else
		echo "Starting 4store on remote node: $node"
		ssh $node "4s-boss"
	fi
done

[ -n "$FOURSTORE_CLUSTER_NAME" ] || . 4s-cluster-name.sh

4s-admin stop-stores $FOURSTORE_CLUSTER_NAME
4s-admin delete-stores $FOURSTORE_CLUSTER_NAME
