#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

wget https://bootstrap.pypa.io/get-pip.py
python3 get-pip.py
sudo pip3 install -r ../requirements.txt
