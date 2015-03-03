#!/usr/bin/env python3
"""
Created on Oct 5, 2014

@author: Zsolt Kovari

Generates the certain models. Multiple instance generation 
is prevented.
"""
import subprocess
import os
import sys
import logging
import pprint

import targets
import handler
import loader
import log


def generate_models(configurations):
    """
    Generates the models after the configurations parameter. 
    
    Parameters:
    @param configurations: a list of Configuration objects
    """
    logging.info("generate.generate_models called.")
    
    path = configurations[0].common.path
    handler.set_working_directory(path)
    
    models_path = targets.get_common_model_path()
    if(os.path.exists(models_path) == False):
        os.makedirs(models_path)
    if (prevented == False):
        print(prevented)
        prevent_multiple_generation(configurations)
    
    # mutual parameters for every configuration
    java_xmx = configurations[0].common.java_xmx
    java_maxpermsize = configurations[0].common.java_maxpermsize
    
    for scenario in models:
        for format in models[scenario]:
            target = targets.get_generator_jar(format)
            if (target is None):
                logging.error("Generator .jar cannot be found of " + format)
                return None
            if (len(models[scenario][format]) > 0):
                for size in models[scenario][format]:
                    logging.info("Generate model:(format:" + format + \
                                 ", scenario:" + scenario + \
                                 ", size:" + str(size)+ ")") 
                    subprocess.call(["java", "-Xmx" + java_xmx,\
                                     "-XX:MaxPermSize=" + java_maxpermsize,\
                                      "-jar", target, "-scenario", scenario, \
                                      "-size", str(size), \
                                      "-workspacePath", path \
                                    ])
        


def prevent_multiple_generation(configurations):
    """
    Gathers the mutual size parameters between configuration formats,
    scenarios and merge them into dictionaries. The latter is being stored 
    by the models variable.
    """
    unique_formats = set()
    unique_scenarios = set()
    for c in configurations:
        if (c.format not in unique_formats):
            unique_formats.add(c.format)
    for c in configurations:
        for s in c.scenarios:
            if (s not in unique_scenarios):
                unique_scenarios.add(s)
    
    if ("Batch" in unique_scenarios):
        unique_scenarios.remove("Batch")
    if ("Repair" not in unique_scenarios):
        unique_scenarios.add("Repair")
    for s in unique_scenarios:
        formats = dict()
        for f in unique_formats:
            sizes = list()
            for config in configurations:
                if (
                    (s in config.scenarios or \
                    ("Batch" in config.scenarios and s == "Repair")) and \
                    config.format == f
                    ):
                    sizes = list(set(sizes) | set(config.sizes))
                sizes.sort()
            formats.update({f:sizes})
        models.update({s:formats})

    print("=" * 40)
    pp = pprint.PrettyPrinter(indent=4)
    pp.pprint(models)
    global prevented
    prevented = True
            

prevented = False
models = dict()

if (__name__ == "__main__"):
    log.init_log()
    logging.info("Main module:generate.")
    configurations = loader.get_configs_from_json()
    if (configurations is None):
        logging.error("No valid configurations were loaded.")
        sys.exit(1)
    if (prevented == False):
        prevent_multiple_generation(configurations)
    generate_models(configurations)

        
