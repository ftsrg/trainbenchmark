#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"
. 4s-cluster-name.sh

4s-cluster-stop $FOURSTORE_CLUSTER_NAME
4s-cluster-destroy $FOURSTORE_CLUSTER_NAME
