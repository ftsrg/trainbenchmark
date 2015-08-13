#!/usr/bin/env python3
"""
Created on Sep 28, 2014

@author: Zsolt Kovari

This script assumes that the required dependencies are available from either 
the local Maven repository or the Maven Central Repository.
"""
import subprocess
import argparse

import util
from loader import Loader


def build(config, formats, skip_tests):
    profiles = {"core"}
    profiles = profiles.union(formats)
    profiles = profiles.union(config.tools)

    profiles_arg = ",".join(profiles)

    cmd = ["mvn", "clean", "install", "-P", profiles_arg]
    if skip_tests:
        cmd.append("-DskipTests")
    subprocess.check_call(cmd)


def build_ci():
    cmd_ci = ["mvn", "clean", "install", "-P", "ci"]
    subprocess.check_call(cmd_ci)
    # skip the tests for tools with third-party dependencies
    cmd_notest = ["mvn", "clean", "install", "-P", "notest", "-DskipTests"]
    subprocess.check_call(cmd_notest)


def generate(config, formats):
    for scenario in config.scenarios:
        for format in formats:
            path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format)
            util.set_working_directory(path)
            target = util.get_generator_jar(format)
            for size in config.sizes:
                print("Generate model: <format: " + format +
                      ", scenario: " + scenario +
                      ", size: " + str(size) + ">")
                subprocess.call(["java", "-Xmx" + config.java_opts["Xmx"],
                                 "-jar", target,
                                 "-scenario", scenario,
                                 "-size", str(size)])
            util.set_working_directory("..")


def measure(config):
    for tool in config.tools:
        if tool in config.optional_arguments:
            args = config.optional_arguments[tool]
        else:
            args = [""]

        for arg in args:
            path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".format(TOOL=tool)
            util.set_working_directory(path)
            target = util.get_tool_jar(tool)

            for scenario in config.scenarios:
                for size in config.sizes:
                    for query in config.queries:
                        print("Run benchmark: <tool: " + tool +
                              ", scenario: " + scenario +
                              ", query: " + query +
                              ", size: " + str(size) +
                              (", arg: " + arg if arg != "" else "") +
                              ">")
                        cmd = ["java", "-Xmx" + config.java_opts["Xmx"], "-jar", target,
                               "-scenario", scenario,
                               "-query", query,
                               "-size", str(size),
                               arg
                               ]
                        subprocess.call(cmd)
            util.set_working_directory("..")


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-c", "--ci",
                        help="CI build",
                        action="store_true")
    parser.add_argument("-b", "--build",
                        help="build the projects",
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

    # set working directory to this file's path
    util.set_working_directory()
    loader = Loader()
    config = loader.load()

    formats = set()
    for tool in config.tools:
        formats.add(loader.get_format(tool))

    # if there are no args, execute a full sequence
    # with the test and the visualization/reporting
    no_args = all(val is False for val in vars(args).values())
    if no_args:
        args.build = True
        args.generate = True
        args.measure = True
    if args.ci:
        build_ci()
    if args.build:
        build(config, formats, args.skip_tests)
    if args.generate:
        generate(config, formats)
    if args.measure:
        measure(config)
