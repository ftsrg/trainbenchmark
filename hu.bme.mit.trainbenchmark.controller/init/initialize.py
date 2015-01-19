#!/usr/bin/env python3
"""
Created on Nov 24, 2014

@author: Zsolt Kovari

Install pip and the required third-party modules based on the requirements.txt.
"""
import os
import subprocess

# set working directory to this script's location
full_path = os.path.realpath(__file__)
path = os.path.split(full_path)
os.chdir(path[0])
subprocess.call(["../shell-scripts/install_pip.sh"])