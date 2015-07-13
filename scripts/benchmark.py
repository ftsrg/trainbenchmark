#!/usr/bin/env python3
"""
Created on Sep 30, 2014

@author: Zsolt Kovari

This module is responsible for running the benchmark measurements.
"""
import subprocess
import sys
import os
import platform
import logging

import targets
import util
from loader import Loader
import log


def run_benchmark(configurations):
    """Run the benchmark after the configurations parameter.
    
    Parameters:
    @param configurations: a list of Configuration objects
    """
    logging.info("benchmark.run_benchmark called.")
    util.set_working_directory(configurations[0].common.path)
    if not os.path.exists("./results"):
        os.mkdir("results")

    for config in configurations:
        execute(config)
