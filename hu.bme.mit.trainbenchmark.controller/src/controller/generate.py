#!/usr/bin/env python3
"""
Created on Oct 5, 2014

@author: Zsolt Kovari

This module generates the certain models.
"""
import subprocess
import os
import sys
import logging

import targets
import handler
import loader
import log

def generate_models(configuration):
    """
    Generates the models after the configuration parameter. If basic parameter
    is True, then generates the smallest models only. 
    
    Parameters:
    @param configuration: a Configuration object
    """
    logging.info("generate.generate_models called.")
    handler.set_working_directory(configuration.path)
    target = targets.get_generator_jar(configuration.format)
    if (target is None):
        logging.error("Generator .jar file cannot be found of " +\
                      configuration.format)
        return None
    models_path = targets.get_common_model_path()
    if(os.path.exists(models_path) == False):
        os.makedirs(models_path)
    
    for scenario in configuration.scenarios:
        for size in configuration.sizes:
            logging.info("Generate model:(format:" + configuration.format + \
                         ", scenario:" + scenario + ", size:" + str(size)+ \
                         ", path:" + configuration.path + ")") 
            java_xmx = configuration.java_xmx
            java_maxpermsize = configuration.java_maxpermsize
            subprocess.call(["java", "-Xmx" + java_xmx,"-XX:MaxPermSize=" +\
                             java_maxpermsize, "-jar", target, "-scenario",\
                             "Repair" if scenario=="Batch" else scenario, \
                             "-size", size, "-workspacePath", \
                             configuration.path])


if (__name__ == "__main__"):
    log.init_log()
    logging.info("Main module:generate.")
    configurations = loader.get_configs_from_json()
    if (configurations is None):
        logging.error("No valid configurations were loaded.")
        sys.exit(1)
    #sizes = list()
    #for c in configurations:
    #    sizes = list(set(sizes) | set(c.sizes))
    #print(sizes)
    for config in configurations:
        generate_models(config)

        