#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )/../models"

#for variant in inferred metamodel; do
for variant in inferred; do
	echo $variant
	for scenario in batch inject repair; do
		echo $scenario
		#ag --count "^[^@#].*[;.]$" *-$scenario-*$variant.ttl | sed "s/\(.*\):\(.*\)/\2\t\1/" | sort --numeric-sort
		grep --count "^[^@#].*[;.]$" *-$scenario-*$variant.ttl | sed "s/\(.*\):\(.*\)/\2\t\1/" | sort --numeric-sort
		echo
	done
	echo
done
