#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"
. 4s-cluster-name.sh

# invoke destroy script just to be sure
./4s-destroy-single.sh

# start single node
4s-backend-setup -v $FOURSTORE_CLUSTER_NAME
4s-backend $FOURSTORE_CLUSTER_NAME
