#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

sudo apt-get -y update
sudo apt-get install -y python3 python3-setuptools python3-dev
wget https://bootstrap.pypa.io/get-pip.py
python3 get-pip.py
pip3 install --upgrade pip
pip3 install -r ../config/requirements.txt
