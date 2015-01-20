#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"
. 4s-cluster-name.sh

killall -9 4s-backend
while [[ ! -z `ps auxw | grep 4s-backen[d]` ]]; do
	echo "Waiting for 4store to shut down."
	sleep 1
done

4s-backend-destroy $FOURSTORE_CLUSTER_NAME
