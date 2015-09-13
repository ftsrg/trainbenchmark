#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

sudo apt-get -y update
sudo apt-get install -y python3 python3-setuptools
wget https://bootstrap.pypa.io/get-pip.py
sudo python3 get-pip.py
sudo pip3 install -r ../requirements.txt
