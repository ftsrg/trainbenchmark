"""
Created on Nov 25, 2014

@author: Zsolt Kovari

The module is responsible for providing valid Configuration objects.
"""

import sys
import os
import json
import logging

import validation
import util
from config import Configuration, CommonParameters

from jsonschema.exceptions import ValidationError


class Loader():
    def __init__(self):
        self.config_path = "config/config.json"
        self.schema_path = "config/config_schema.json"
        self.tools_path = "config/tools_source.json"

    def load(self):
        """
        Loads a config.json file and run a validation process. 
        If the configurations seem valid, returns a list with 
        Configuration objects. 

        """
        util.set_working_directory()
        # paths relatively to this script's location        
        schema_json = util.json_decode(self.schema_path)
        if schema_json is None:
            msg = "Problem has occurred during the decoding procedure" + \
                  " with the following file: " + self.schema_path + "."
            logging.error(msg)
            raise IOError(msg)
        tools_json = util.json_decode(self.tools_path)
        if tools_json is None:
            msg = "Problem has occurred during the decoding procedure" + \
                  " with the following file: " + self.tools_path + "."
            logging.error(msg)
            raise IOError(msg)
    
        try:
            with open(self.config_path, mode="r") as file:
                config_string = file.read()
            decoder = json.JSONDecoder(object_pairs_hook=checking_hook)
            config_json = decoder.decode(config_string)
        except IOError:
            msg = "The file does not exist or cannot read:" + \
                  (os.path.split(self.config_path))[1]
            logging.error(msg)
            raise IOError(msg)
        except ValueError as value_error:
            msg = (os.path.split(self.config_path))[1] + " file is not valid"
            logging.error(msg)
            print(value_error)
            raise ValidationError(msg)
        except KeyError as k_error:
            msg = "Duplicate key specified."
            logging.error(msg)
            print(k_error)
            print("Modify: " + (os.path.split(self.config_path))[1])
            raise ValidationError(msg)
    
        valid = validation.is_valid_json(config_json, schema_json)
        if not valid:
            msg = "Validation failed of " + \
                  (os.path.split(self.config_path))[1] + " file."
            logging.error(msg)
            raise ValidationError(msg)
        
        configurations = list()
        unique_tools = set() 
        
        # common parameters    
        common = CommonParameters()
        common.generator_args = config_json["Arguments"]["Generator"]
        common.maven_xmx = config_json["MAVEN_OPTS"]["Xmx"]
        common.java_xmx = config_json["JAVA_OPTS"]["Xmx"]
        common.series = config_json["Series"]
        common.modif_method = config_json["ModificationMethod"]
        common.modif_constant = config_json["ModificationConstant"]
        common.iter_count = config_json["IterationCount"]
        common.models = config_json["Model"]
        common.submodels = config_json["Submodel"]

        # relatively from this script's path
        util.set_working_directory("../")
        path = os.getcwd()  # store the absolute path instead of relative
        common.path = path
        util.set_working_directory()
        
        # tools with unique configuration
        if len(config_json["UniqueConfigurations"]) > 0:
            for unique in config_json["UniqueConfigurations"]:
                unique_tools.add(unique["Tool"])
                if "MinSize" in unique.keys():
                    if "MaxSize" in unique.keys():
                        sizes = util.get_power_of_two(unique["MinSize"],
                                                      unique["MaxSize"])
                    else:
                        sizes = util.get_power_of_two(unique["MinSize"],
                                                      config_json["MaxSize"])
                elif "MaxSize" in unique.keys():
                    sizes = util.get_power_of_two(config_json["MinSize"],
                                                  unique["MaxSize"])
                else:
                    sizes = util.get_power_of_two(config_json["MinSize"],
                                                  config_json["MaxSize"])
                if len(sizes) == 0:
                    logging.error("Problem with min and maxsize." +
                                  "Too short the range between them.")
                    return None
                
                if "Scenarios" in unique.keys():
                    scenarios = unique["Scenarios"]
                else:
                    scenarios = config_json["Scenarios"]
                if "Queries" in unique.keys():
                    queries = unique["Queries"]
                else:
                    queries = config_json["Queries"]
                    generator_args = config_json["Arguments"]["Generator"]
                if "Benchmark" in unique.keys():
                    benchmark_args = unique["Benchmark"]
                else:
                    benchmark_args = config_json["Arguments"]["Benchmark"]

                config = Configuration()
                config.benchmark_args = benchmark_args
                config.tool = unique["Tool"]
                config.queries = queries
                config.scenarios = scenarios
                config.sizes = sizes
                config.common = common
                config.format = tools_json[unique["Tool"]]["format"]
                configurations.append(config)
        
        # tools with the default configuration
        for tool in config_json["Tools"]:
            if tool not in unique_tools:
                sizes = util.get_power_of_two(config_json["MinSize"],
                                              config_json["MaxSize"])
                if len(sizes) == 0:
                    logging.error("Problem with min and maxsize." +
                                  "Too short the range between them.")
                    return None
                scenarios = config_json["Scenarios"]
                queries = config_json["Queries"]
                
                config = Configuration()
                config.tool = tool
                config.queries = queries
                config.scenarios = scenarios
                config.sizes = sizes
                config.common = common
                config.format = tools_json[tool]["format"]
                config.benchmark_args = config_json["Arguments"]["Benchmark"]
                
                configurations.append(config)
        for config in configurations:
            config.add_dependency(config.tool)
        return configurations

    def get_dependency(self, tool):
        """
        Returns the tool's dependency as string. And returns None in that case
        if a dependency would not exist.
        """
        # path relatively to this script's location
        current_directory = os.getcwd() 
        util.set_working_directory()
        dependencies_path = "config/dependencies.json"
        dependencies_json = util.json_decode(dependencies_path)
        if dependencies_json is None:
            logging.error("Problem has occurred during the decoding procedure" +
                          " with the following file: " + dependencies_path)
            return None
        util.set_working_directory(current_directory)
        if tool not in dependencies_json:
            logging.warning("Does not exist a dependency for " + tool + ".")
            return None
        else:
            logging.info("A dependency exists: " + tool + "->" +
                         dependencies_json[tool]["name"] + ".")
            
            return dependencies_json[tool]["name"]
        

def checking_hook(pairs):
    result = dict()
    for key, value in pairs:
        if key in result:
            raise KeyError("Duplicate key specified: %s" % key)
        result[key] = value
    return result
