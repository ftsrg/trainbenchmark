#!/usr/bin/env python3
"""
Created on Nov 23, 2014

@author: Zsolt Kovari

Create a virtual environment into the trainbenchmark-controller with venv.
Install easy_install then pip and further more the required third-party modules
based on the requirements.txt.
"""
import venv
import os
import subprocess

# set working directory to this script's location
full_path = os.path.realpath(__file__)
path = os.path.split(full_path)
os.chdir(path[0])
env = venv.EnvBuilder(system_site_packages=False,clear=True,symlinks=True)
env.create("../tb-env/")
subprocess.call(["../shell-scripts/install_pip_to_venv.sh"])
