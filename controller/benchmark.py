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
    for series_index in range(1, configuration.common.series + 1):
        for scenario in configuration.scenarios:
            for size in configuration.sizes:
                format = configuration.format
                path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".\
                    format(TOOL=configuration.tool)
                util.set_working_directory(path)
                # benchmark_artifact = targets.get_model_path(format,
                #                                             scenario,
                #                                             size)

                target = targets.get_tool_jar(configuration.tool)
                xmx = configuration.common.java_xmx
                modif_method = configuration.common.modif_method
                modif_constant = str(configuration.common.modif_constant)
                iter_count = str(configuration.common.iter_count)
                model = configuration.common.model
                args = configuration.benchmark_args

                for query in configuration.queries:
                    logging.info("Run benchmark:(tool:" + configuration.tool +
                                 ", scenario:" + scenario +
                                 ", query:" + query + ", size:" + str(size)+")")
                    subprocess.call(["java", "-Xmx" + xmx, "-jar", target,
                                     "-scenario", scenario,
                                     "-model", model,
                                     "-runIndex", str(series_index),
                                     "-query", query,
                                     "-modificationMethod", modif_method,
                                     "-modificationConstant", modif_constant,
                                     "-iterationCount", iter_count,
                                     "-size", str(size),
                                     args
                                     ])
                util.set_working_directory("../")


# def run_eclipse_based_benchmark(configuration):
#     """Run the eclipse based benchmark after the configuration parameter.
#
#     Parameters:
#     @param configuration: a Configuration object
#     """
#     logging.info("benchmark.run_eclipse_based_benchmark called")
#     if platform.system() == "Linux":
#         os = "linux"
#         ws = "gtk"
#     elif platform.system() == "Darwin": #OS X
#         os = "macosx"
#         ws = "cocoa"
#     else:
#         logging.error("Operating System is not supported!")
#         return None
#     target = ("./hu.bme.mit.trainbenchmark.benchmark." +
#               "{TOOL}.product/target/products/hu.bme.mit.trainbenchmark." +
#               "benchmark.{TOOL}.product/{OS}/{WS}/x86_64/eclipse")\
#         .format(TOOL=configuration.tool, OS=os, WS=ws)
#     for series_index in range(1, configuration.series + 1):
#         series_str = str(series_index)
#         for scenario in configuration.scenarios:
#             for size in configuration.sizes:
#                 format = configuration.format
#                 benchmark_artifact = targets.get_model_path(format,
#                                                             scenario,
#                                                             size)
#                 xmx = configuration.java_xmx
#                 maxpermsize = configuration.java_maxpermsize
#                 for query in configuration.queries:
#                     logging.info("Run benchmark:(tool:" + configuration.tool +
#                                  ", scenario:" + scenario +
#                                  ", query:" + query + ", size:" + str(size)+")")
#                     subprocess.call([target, "-scenario", scenario,
#                                      "-benchmarkArtifact", benchmark_artifact,
#                                      "-workspacePath", configuration.path,
#                                      "-runIndex", series_str,
#                                      "-query", query,
#                                      "-nMax", "1",
#                                      "-vmargs", "-Xmx" + xmx,
#                                      "-XX:MaxPermSize=" + maxpermsize,
#                                      ])
#
#
# eclipse_based = {
#     'eclipseocl': run_eclipse_based_benchmark,
#     'emfincquery': run_eclipse_based_benchmark
#     }

if __name__ == "__main__":
    log.init_log()
    logging.info("Main module:benchmark")
    loader = Loader()
    configurations = loader.load()
    run_benchmark(configurations)

