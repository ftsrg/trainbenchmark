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


def build(formats, tools, skip_tests):
    profiles = {"core"}
    profiles = profiles.union(formats)
    profiles = profiles.union(tools)

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


def generate(formats, scenarios, sizes):
    for scenario in scenarios:
        for format in formats:
            path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format)
            util.set_working_directory(path)
            target = util.get_generator_jar(format)
            for size in sizes:
                print("Generate model: <format: " + format +
                      ", scenario: " + scenario +
                      ", size: " + str(size) + ">")
                subprocess.call(["java", "-Xmx" + java_xmx,
                                 "-jar", target,
                                 "-scenario", scenario,
                                 "-size", str(size)])
            util.set_working_directory("..")


def measure(tools, scenarios, sizes, queries, optional_arguments: dict):
    for tool in tools:
        if tool in optional_arguments:
            args = optional_arguments[tool]
        else:
            args = [""]

        for arg in args:
            path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".format(TOOL=tool)
            util.set_working_directory(path)
            target = util.get_tool_jar(tool)

            for scenario in scenarios:
                for size in sizes:
                    for query in queries:
                        print("Run benchmark: <tool: " + tool +
                              ", scenario: " + scenario +
                              ", query: " + query +
                              ", size: " + str(size) +
                              (", arg: " + arg if arg != "" else "") +
                              ">")
                        cmd = ["java", "-Xmx" + java_xmx, "-jar", target,
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
        build(formats, config.tools, args.skip_tests)
    if args.generate:
        generate(formats, config.scenarios, config.sizes)
    if args.measure:
        measure(config.tools, config.scenarios, config.sizes, config.queries, config.optional_arguments, config.java_opts)
