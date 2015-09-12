#!/bin/bash

#sbt
echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 642AC823
sudo apt-get update
sudo apt-get install sbt

cd "$( cd "$( dirname "$0" )" && pwd )/../.."
git clone https://github.com/FTSRG/incqueryd-core.git
cd incqueryd-core
./publishM2.sh