#!/usr/bin/env python3
"""
Created on Sep 28, 2014

@author: Zsolt Kovari

The module is responsible for resolving the dependencies between repositories,
furthermore build the projects with maven. With the optional arguments,
there is an opportunity to generate the models and run the benchmark tests.

Arguments:
    -g, --generate: generates models
    -b, --benchmark: run benchmark
    -c, --core: just build the core
    -f, --format: just build the format
    -t, --tools: just build the tools
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


def resolve_dependencies(configurations):
    """
    Resolve the dependencies between repositories after the dependencies.json
    file.
    
    Parameters:
    @param configurations: a list contain Configuration objects
    """
    for config in configurations:
        for name in config.get_dependencies():
            dependency = loader.get_dependency(name)
            if dependency is not None:
                if dependency not in config.get_dependencies():
                    config.add_dependency(dependency)


def maven_build(tool, skip_tests):
    """Run a Maven build after the tool's name.
    
    Parameters:
    @param package: tool name
    @param skip_tests: skip JUNIT tests
    """
    logging.info("Build: " + tool)
    deps.build_unique_tools(tool)

    if skip_tests:
        subprocess.call(["mvn", "clean", "install", "-P",
                        util.get_package_name(tool), "-DskipTests"])
    else:
        subprocess.call(["mvn", "clean", "install", "-P",
                        util.get_package_name(tool)])


def build_projects(configurations, skip_tests, build_core=True,
                   build_formats=True, build_tools=True):
    """Build the projects.
    
    @param configurations: a list of Configuration objects
    @param skip_tests: skip JUNIT tests if given
    @param build_core: builds the core project if given
    @param build_formats: build the format projects and generators
    @param build_tools: build the tools projects
    """
    tools = list()
    formats = list()
    for config in configurations:
        tools.append(config.tool)
        formats.append(config.format)

    # make a new instance of the static attribute
    all_dependencies = configurations[0].all_dependencies.copy()
    if not build_core:
        if "core" in all_dependencies:
            all_dependencies.remove("core")
    if not build_formats:
        for f in formats:
            if f in all_dependencies:
                all_dependencies.remove(f)
    if not build_tools:
        for t in tools:
            if t in all_dependencies:
                all_dependencies.remove(t)
    logging.info("Build the following projects: " + all_dependencies.__str__())
    print("Build the following projects: " + all_dependencies.__str__())
    path = configurations[0].common.path
    util.set_working_directory()
    subprocess.call(["shell-scripts/export_maven_opts.sh",
                    configurations[0].common.maven_xmx])
    util.set_working_directory(path)

    while len(all_dependencies) > 0:
        deps.install_dependencies(all_dependencies[-1], path)
        maven_build(all_dependencies.pop(), skip_tests)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-g", "--generate",
                        help="generate models too",
                        action="store_true")
    parser.add_argument("-b", "--benchmark",
                        help="run the benchmark tests too",
                        action="store_true")
    parser.add_argument("-c", "--core",
                        help="just build the core",
                        action="store_true")
    parser.add_argument("-f", "--format",
                        help="just build the format",
                        action="store_true")
    parser.add_argument("-t", "--tools",
                        help="just build the tools",
                        action="store_true")
    parser.add_argument("-s", "--skip-tests",
                        help="skip JUNIT tests",
                        action="store_true")

    args = parser.parse_args()
    log.init_log()
    # set working directory to this file's path
    util.set_working_directory()
    build_all = True

    logging.info("Main module: build.py.")
    if args.core or args.format or args.tools:
        build_all = False

    loader = Loader()
    configurations = loader.load()

    resolve_dependencies(configurations)

    if build_all:
        build_projects(configurations,  args.skip_tests, build_core=True,
                       build_formats=True, build_tools=True)
    else:
        build_projects(configurations, args.skip_tests, args.core,
                       args.format, args.tools)

    if args.generate:
        generator = Generator()
        generator.generate_models(configurations)

    if args.benchmark:
        for config in configurations:
            benchmark.run_benchmark(configurations)
