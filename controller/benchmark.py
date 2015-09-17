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
    

def execute(configuration):
    """Benchmark function.
    """
    models = configuration.common.models
    submodels = configuration.common.submodels
    for scenario in configuration.scenarios:
        for size in configuration.sizes:
            for model in models:
                for sub in submodels:
                    format = configuration.format
                    path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".\
                        format(TOOL=configuration.tool)
                    util.set_working_directory(path)

                    target = targets.get_tool_jar(configuration.tool)
                    xmx = configuration.common.java_xmx
                    modif_method = configuration.common.modif_method
                    modif_constant = str(configuration.common.modif_constant)
                    iter_count = str(configuration.common.iter_count)
                    series = configuration.common.series

                    args = configuration.benchmark_args

                    for query in configuration.queries:
                        subprocess.call(["java", "-Xmx" + xmx, "-jar", target,
                                         "-scenario", scenario,
                                         "-model", model,
                                         "-subModel", sub,
                                         "-runIndex", str(series),
                                         "-query", query,
                                         "-modificationMethod", modif_method,
                                         "-modificationConstant", modif_constant,
                                         "-iterationCount", iter_count,
                                         "-size", str(size),
                                         args
                                        ])
                    util.set_working_directory("../")


if __name__ == "__main__":
    log.init_log()
    logging.info("Main module:benchmark")
    loader = Loader()
    configurations = loader.load()
    run_benchmark(configurations)

