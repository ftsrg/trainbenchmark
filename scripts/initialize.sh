#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

sudo apt-get update
sudo apt-get install -y python3 python3-setuptools
sudo easy_install3 pip
sudo pip3 install -r ../requirements.txt
