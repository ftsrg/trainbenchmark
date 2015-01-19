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

import handler
import benchmark
import generate
import loader
import deps
import log


def git_clone(repo):
    """
    Clone a remote repository by git after a Repository object.
    
    @param repo: Repository object
    """
    subprocess.call(["git", "clone", repo.url, "--branch", repo.branch,\
                     "--depth", repo.depth])


def resolve_dependencies(configurations):
    """
    Resolve the dependencies between repositories after the dependencies.json
    file.
    
    Parameters:
    @param configurations: a list contain Configuration objects
    """
    for config in configurations:
        for repo in config.get_repositories():
            dependency = loader.get_dependency(repo.name)
            if (dependency is not None):
                if (dependency not in config.get_repositories()):
                    config.add_repository(dependency)
                    #config.repositories.add(dependency)
    # change back working directory later, so store it now
    current_directory = os.getcwd()
    for config in configurations:
        handler.set_working_directory(config.path)
        for repo in config.get_repositories():
            if (os.path.exists(os.getcwd() + "/" + repo.folder) == False):
                git_clone(repo)
    handler.set_working_directory(current_directory)


def maven_build(configuration, package):
    """
    Run a Maven build after the configuration object parameter and  
    package name.
    
    Parameters:
    @param configuration: Configuration object
    @param package: package name
    """
    logging.info("Build: " + package)
    # change back working directory later, so store it now
    current_directory = os.getcwd() 
    # change working directory to this module's location
    handler.set_working_directory()
    # path is given relatively to this module's location
    subprocess.call(["../../shell-scripts/export_maven_opts.sh",\
                    configuration.maven_xmx, \
                    configuration.maven_maxpermsize])
    # jump to the project parent folder since configuration.path can be relative
    #handler.set_working_directory("../../../")
    handler.set_working_directory(configuration.path)
    subprocess.call(["mvn", "clean", "install", "-f",\
                     "./trainbenchmark-core/pom.xml", "-P",\
                     handler.get_package_name(package)])
    handler.set_working_directory(current_directory)


def build_projects(configurations, build_core=True,build_formats=True, \
                   build_tools=True):
    """Build the projects.
    """
    tools = list()
    formats = list()
    for config in configurations:
        tools.append(config.tool)
        formats.append(config.format)
    logging.info("Build the following projects:" + tools.__str__() + " - " +\
                  formats.__str__())
    # make a new instance of the static attribute
    # all_repositories contains every Repository which attached to
    # the Configuration object
    all_repositories = configurations[0].all_repositories.copy()
    while(len(all_repositories) > 0):
            # first build the top repository in the hierarchy
            if (build_core == True and \
                    all_repositories[-1].name not in tools and \
                    all_repositories[-1].name not in formats):
                # install further dependencies if necessary
                deps.install_dependencies(all_repositories[-1].name, \
                                          all_repositories[-1].path)
                maven_build(all_repositories[-1].config, \
						    all_repositories.pop().name)
            elif (build_formats == True and \
                    all_repositories[-1].name in formats):
                # install further dependencies if necessary
                deps.install_dependencies(all_repositories[-1].name, \
                                            all_repositories[-1].path)
                maven_build(all_repositories[-1].config, \
						    all_repositories.pop().name)
                generate.generate_models(all_repositories[-1].config, \
                                         basic=True)
            elif (build_tools == True and \
                    all_repositories[-1].name in tools):
                # install further dependencies if necessary
                deps.install_dependencies(all_repositories[-1].name, \
                                          all_repositories[-1].path)
                maven_build(all_repositories[-1].config, \
						    all_repositories.pop().name)
            else:
                all_repositories.pop()


if (__name__ == "__main__"):
    parser = argparse.ArgumentParser();
    parser.add_argument("-g","--generate",
                        help="generate models too",
                        action="store_true")
    parser.add_argument("-b","--benchmark",
                        help="run the benchmark tests too",
                        action="store_true")
    parser.add_argument("-c","--core",
                        help="just build the core",
                        action="store_true")
    parser.add_argument("-f","--format",
                        help="just build the format",
                        action="store_true")
    parser.add_argument("-t","--tools",
                        help="just build the tools",
                        action="store_true")

    args = parser.parse_args()
    log.init_log()
    # set working directory to this file's path
    handler.set_working_directory()
    build_all = True
    
    logging.info("Main module: build.py.")
    if (args.core == True or args.format == True or args.tools == True):
        build_all = False

    configurations = loader.get_configs_from_json()
    if (configurations is None):
        logging.error("No valid configurations were loaded.")
        sys.exit(1)

    resolve_dependencies(configurations)

    if (build_all == True):
        build_projects(configurations, build_core=True,\
                       build_formats=True, build_tools=True)
    else:
        build_projects(configurations, args.core, args.format, \
                   args.tools)
    
    if (args.generate == True):
        for config in configurations:
            generate.generate_models(config)
    if (args.benchmark == True):
        for config in configurations:
            benchmark.run_benchmark(config)
