#!/bin/bash

cd "$( cd "$( dirname "$0" )" && pwd )"

sudo apt-get install python3-setuptools
sudo easy_install3 pip
sudo pip3 install -r ../requirements.txt
