#!/usr/bin/env python3
"""
This script runs the benchmark and performs some additional operations:
* building the code
* generating the models
* running the benchmark
The script assumes that the required dependencies are available from either 
the local Maven repository or the Maven Central Repository.
"""
import argparse

import yaml

import build
import generate
import measure
import util


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
    parser.add_argument("-f", "--formats-only",
                        help="generate the formats specified in the configuration file",
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

    # load the configuration file and determine the derived value
    with open("config/config.yml", 'r') as stream:
        config = yaml.load(stream)
    with open("config/formats.yml", 'r') as stream:
        tool_formats = yaml.load(stream)
  
    tools = config["tools"]
    java_opts = config["java_opts"]
    query_mixes = config["query_mixes"]
    scenarios = config["scenarios"]
    runs = config["runs"]
    timeout = config["timeout"]
    benchmark_optional_arguments = config["benchmark_optional_arguments"]
    generator_optional_arguments = config["generator_optional_arguments"]
    sizes = util.get_power_of_two(config["min_size"], config["max_size"])
    if ("email" in config):
        email = config["email"]
    else:
        email = None

    if args.formats_only:
        formats = config["formats"]
    else:
        formats = set()
        for tool in tools:
            formats.add(tool_formats[tool])

    # Run the framework. If there are no args, execute a full sequence
    # with the test and the visualization/reporting.
    no_args = all(val is False for val in vars(args).values())
    if no_args:
        args.build = True
        args.generate = True
        args.measure = True
    if args.build:
        build.build(java_opts, formats, tools, args.skip_tests)
    if args.generate:
        generate.generate_models(java_opts, formats, scenarios, sizes, generator_optional_arguments)
    if args.measure:
        measure.measure_tools(java_opts, timeout, runs, scenarios, sizes, tools, query_mixes, benchmark_optional_arguments, email)
