#!/usr/bin/env python3
"""
Created on Sep 28, 2014

@author: Zsolt Kovari

The module is responsible for resolving the dependencies between repositories,
furthermore build the projects with Maven. With the optional arguments,
there is an opportunity to generate the models and run the benchmark tests.

SzG:
This script assumes that the required dependencies are available from either 
the local Maven repository or the Maven Central Repository.
"""
import os
import subprocess
import sys
import argparse
import logging

import util
import benchmark
from generate import Generator
from loader import Loader
import deps
import log

def flatten(lst):
    return sum(([x] if not isinstance(x, list) else flatten(x) for x in lst), [])


def build(configurations, skip_tests):
    """Build the projects.
    
    @param configurations: a list of Configuration objects
    @param skip_tests: skip JUnit tests if given
    """
    profiles = {"core"}
    for tool in config.tools:
        profiles.add(tool)
        profiles.add(loader.get_dependency(tool))

    profiles_arg = ",".join(profiles)
    print(profiles_arg)

    cmd = ["mvn", "clean", "install", "-P", profiles_arg]
    if skip_tests:
        cmd.append("-DskipTests")
    subprocess.call(cmd)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-b", "--build",
                        help="build the project",
                        action="store_true")
    parser.add_argument("-g", "--generate",
                        help="generate models",
                        action="store_true")
    parser.add_argument("-m", "--measure",
                        help="run the benchmark",
                        action="store_true")
    parser.add_argument("-s", "--skip-tests",
                        help="skip JUnit tests",
                        action="store_true")
    args = parser.parse_args()

    log.init_log()
    # set working directory to this file's path

    util.set_working_directory()

    logging.info("Main module: run.py.")
    loader = Loader()
    config = loader.load()

    # if there are no args, execute a full sequence
    # with the test and the visualization/reporting
    no_args = all(val is False for val in vars(args).values())
    if no_args:
        args.test = True
    if args.build:
        build(config, args.skip_tests)
    if args.generate:
        pass
    if args.measure:
        pass
    if args.test:
        build(config, True)
