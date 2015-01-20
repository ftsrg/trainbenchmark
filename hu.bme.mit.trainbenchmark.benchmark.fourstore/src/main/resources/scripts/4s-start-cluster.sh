#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"
. 4s-cluster-name.sh

# invoke destroy script just to be sure
./4s-destroy-cluster.sh

# start cluster
4s-cluster-create $FOURSTORE_CLUSTER_NAME
4s-cluster-start $FOURSTORE_CLUSTER_NAME
