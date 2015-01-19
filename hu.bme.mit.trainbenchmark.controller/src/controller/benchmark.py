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
import handler
import loader
import log

def run_benchmark(configuration):
    """Run the benchmark after the configuration parameter.
    
    Parameters:
    @param configuration: a Configuration object
    """
    logging.info("benchmark.run_benchmark called.")
    handler.set_working_directory(configuration.path)
    if (os.path.exists("./trainbenchmark-results") == False):
        os.mkdir("trainbenchmark-results")
    if (configuration.tool in eclipse_based):
        eclipse_based[configuration.tool](configuration)
        return
    for series_index in range(1,configuration.measurements + 1):
        series_str = str(series_index)
        for scenario in configuration.scenarios:
            for size in configuration.sizes:
                format = configuration.format
                benchmark_artifact = targets.get_model_path(format,\
                                                            scenario,\
                                                            size)
                target = targets.get_benchmark_jar(configuration.tool)
                xmx = configuration.java_xmx
                maxpermsize = configuration.java_maxpermsize
                for query in configuration.queries:
                    logging.info("Run benchmark:(tool:" + configuration.tool + \
                                 ", scenario:" + scenario +\
                                 ", query:" + query + ", size:" + str(size)+")")
                    subprocess.call(["java", "-Xmx" + xmx, "-XX:MaxPermSize="\
                                     + maxpermsize, "-jar", target,\
                                     "-scenario", scenario,\
                                     "-seriesCount", str(series_index), \
                                     "-benchmarkArtifact", benchmark_artifact,\
                                     "-workspacePath", configuration.path,\
                                     "-query", query, "-nMax", "1"])


def run_eclipse_based_benchmark(configuration):
    """Run the eclipse based benchmark after the configuration parameter.
    
    Parameters:
    @param configuration: a Configuration object
    """
    logging.info("benchmark.run_eclipse_based_benchmark called")
    if (platform.system() == "Linux"):
        os = "linux"
        ws = "gtk"
    elif (platform.system() == "Darwin"): #OS X
        os = "macosx"
        ws = "cocoa"
    else:
        logging.error("Operating System is not supported!")
        return None
    target = ("./trainbenchmark-{TOOL}/hu.bme.mit.trainbenchmark.benchmark."\
           + "{TOOL}.product/target/products/hu.bme.mit.trainbenchmark."\
           + "benchmark.{TOOL}.product/{OS}/{WS}/x86_64/eclipse")\
           .format(TOOL=configuration.tool, OS=os, WS=ws)
    for series_index in range(1,configuration.measurements + 1):
        series_str = str(series_index)
        for scenario in configuration.scenarios:
            for size in configuration.sizes:
                format = configuration.format
                benchmark_artifact = targets.get_model_path(format,\
                                                            scenario,\
                                                            size)
                xmx = configuration.java_xmx
                maxpermsize = configuration.java_maxpermsize
                for query in configuration.queries:
                    logging.info("Run benchmark:(tool:" + configuration.tool + \
                                 ", scenario:" + scenario +\
                                 ", query:" + query + ", size:" + str(size)+")")
                    subprocess.call([target, "-scenario", scenario,\
                                     "-benchmarkArtifact", benchmark_artifact,\
                                     "-workspacePath", configuration.path,\
                                     "-seriesCount", series_str, \
                                     "-query", query ,\
                                     "-nMax", "1", \
                                     "-vmargs", "-Xmx" + xmx, \
                                     "-XX:MaxPermSize=" + maxpermsize, \
                                     ])


eclipse_based = {
                'eclipseocl': run_eclipse_based_benchmark,
                'emfincquery': run_eclipse_based_benchmark
                }

if (__name__ == "__main__"):
    log.init_log()
    logging.info("Main module:benchmark")
    configurations = loader.get_configs_from_json()
    if (configurations is None):
        logging.error("No valid configurations were loaded.")
        sys.exit(1)
    for config in configurations:
        run_benchmark(config)

