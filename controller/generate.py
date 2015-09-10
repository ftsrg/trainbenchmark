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
import util
from loader import Loader
import log


class Generator():
    def __init__(self):
        self.prevented = False
        self.models = dict()

    def generate_models(self, configurations):
        """
        Generates the models after the configurations parameter. 
        
        Parameters:
        @param configurations: a list of Configuration objects
        """
        logging.info("generate.generate_models called.")
        
        path = configurations[0].common.path
        util.set_working_directory(path)
        
        # models_path = targets.get_common_model_path()
        # if not os.path.exists(models_path):
        #     os.makedirs(models_path)
        if not self.prevented:
            self.prevent_multiple_generation(configurations)
        pp = pprint.PrettyPrinter(indent=4)
        pp.pprint(self.models)
        # mutual parameters for every configuration
        java_xmx = configurations[0].common.java_xmx
        args = configurations[0].common.generator_args
        models = configurations[0].common.models
        submodels = configurations[0].common.submodels
        for scenario in self.models:
            for format in self.models[scenario]:
                for size in self.models[scenario][format]:
                    for model in models:
                        for sub in submodels:
                            path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/". \
                                format(FORMAT=format)
                            util.set_working_directory(path)
                            target = targets.get_generator_jar(format)
                            subprocess.call(["java", "-Xmx" + java_xmx,
                                             "-jar", target,
                                             "-scenario", scenario,
                                             "-size", str(size),
                                             "-model", model,
                                             "-subModel", sub,
                                             args])
                            util.set_working_directory("../")
        
    def prevent_multiple_generation(self, configurations):
        """
        Gathers the mutual size parameters between configuration formats,
        scenarios and merge them into dictionaries. The latter is being stored 
        by the models variable.
        """
        unique_formats = set()
        unique_scenarios = set()
        for c in configurations:
            if c.format not in unique_formats:
                unique_formats.add(c.format)
        for c in configurations:
            for s in c.scenarios:
                if s not in unique_scenarios:
                    unique_scenarios.add(s)
        
        if "Batch" in unique_scenarios:
            unique_scenarios.remove("Batch")
        if "Repair" not in unique_scenarios:
            unique_scenarios.add("Repair")
        for s in unique_scenarios:
            formats = dict()
            for f in unique_formats:
                sizes = list()
                for config in configurations:
                    if (
                        (s in config.scenarios or
                        ("Batch" in config.scenarios and s == "Repair")) and
                        config.format == f
                    ):
                        sizes = list(set(sizes) | set(config.sizes))
                    sizes.sort()
                formats.update({f: sizes})
            self.models.update({s: formats})

        self.prevented = True
            

if __name__ == "__main__":
    log.init_log()
    logging.info("Main module:generate.")
    loader = Loader()
    configurations = loader.load()
    generator = Generator()
    generator.generate_models(configurations)
