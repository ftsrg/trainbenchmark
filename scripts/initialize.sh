#!/bin/bash

sudo apt-get install python3-setuptools
python3 ./ez_setup.py
easy_install pip
pip3 install -r ../init/requirements.txt
