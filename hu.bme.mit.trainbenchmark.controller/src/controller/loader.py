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
import handler
from config import Configuration, Repository, CommonParameters


def checking_hook(pairs):
    result = dict()
    for key,value in pairs:
        if key in result:
            raise KeyError("Duplicate key specified: %s" % key)
        result[key] = value
    return result


def get_configs_from_json():
    """
    Loads a config.json file and run a validation process. If the configurations 
    seem valid, returns a list with Configuration objects.
    In the case of invalid config.json file, returns None.
    """
    handler.set_working_directory()
    # paths relatively to this script's location
    config_path = "../../config/config.json"
    schema_path = "../../config/config_schema.json"
    tools_path = "../../config/tools_source.json"
    
    schema_json = handler.json_decode(schema_path)
    if (schema_json is None):
        logging.error("Problem has occurred during the decoding procedure" + \
                      " with the following file: " + schema_path + ".")
        return None
    tools_json = handler.json_decode(tools_path)
    if (tools_json is None):
        logging.error("Problem has occurred during the decoding procedure" + \
                      " with the following file: " + tools_path + ".")
        return None

    try:
        with open(config_path, mode="r") as file:
            config_string = file.read()
        decoder= json.JSONDecoder(object_pairs_hook=checking_hook)
        config_json = decoder.decode(config_string)
            #all_config_json = json.load(file)
    except IOError:
        logging.error("The file does not exist or cannot read:" +\
                      (os.path.split(config_path))[1])
        return None
    except ValueError as value_error:
        logging.error((os.path.split(config_path))[1] +\
                      " file is not valid \n")
        print(value_error)
        return None
    except KeyError as k_error:
        logging.error("Duplicate key specified.")
        print(k_error)
        print("Modify: " + (os.path.split(config_path))[1])
        return None

    valid = validation.is_valid_json(config_json, schema_json)
    if (valid == False):
        logging.error("Validation failed of " + \
                      (os.path.split(config_path))[1] + " file.")
        return None
    
    configurations = list()
    unique_tools = set() 
    
    # common parameters    
    common = CommonParameters()
    common.maven_xmx = config_json["MAVEN_OPTS"]["Xmx"]
    common.maven_maxpermsize = config_json["MAVEN_OPTS"]["XX:MaxPermSize"]
    common.java_xmx = config_json["JAVA_OPTS"]["xmx"]
    common.java_maxpermsize = config_json["JAVA_OPTS"]["maxPermSize"]
    common.series = config_json["Series"]
    common.modif_method = config_json["ModificationMethod"]
    common.modif_constant = config_json["ModificationConstant"]
    common.iter_count = config_json["IterationCount"]
    # relatively from this script's path
    handler.set_working_directory("../../../")
    path = os.getcwd() # store the absolute path instead of relative
    common.path = path
    handler.set_working_directory()
    
    # tools with unique configuration
    if (len(config_json["UniqueConfigurations"]) > 0):
        for unique in config_json["UniqueConfigurations"]:
            unique_tools.add(unique["Tool"])
            if ("MinSize" in unique.keys()):
                if ("MaxSize" in unique.keys()):
                    sizes = handler.get_power_of_two(unique["MinSize"],\
                                                     unique["MaxSize"])
                else:
                    sizes = handler.get_power_of_two(unique["MinSize"],\
                                                     config_json["MaxSize"])
            elif ("MaxSize" in unique.keys()):
                sizes = handler.get_power_of_two(config_json["MinSize"],\
                                                 unique["MaxSize"])
            else:
                sizes = handler.get_power_of_two(config_json["MinSize"],\
                                                 config_json["MaxSize"])
            if (len(sizes) == 0):
                logging.error("Problem with min and maxsize."+ \
                              "Too short the range between them.")
                return None
            
            if ("Scenarios" in unique.keys()):
                scenarios = unique["Scenarios"]
            else:
                scenarios = config_json["Scenarios"]
            if ("Queries" in unique.keys()):
                queries = unique["Queries"]
            else:
                queries = config_json["Queries"]
            config = Configuration()
            config.tool = unique["Tool"]
            config.queries = queries
            config.scenarios = scenarios
            config.sizes = sizes
            config.common = common
            config.format = tools_json[unique["Tool"]]["format"]
            configurations.append(config)
    
    # tools with the default configuration
    for tool in config_json["Tools"]:
        if (tool not in unique_tools):
            sizes = handler.get_power_of_two(config_json["MinSize"],\
                                             config_json["MaxSize"])
            if (len(sizes) == 0):
                logging.error("Problem with min and maxsize."+ \
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
            
            configurations.append(config)
    
            
    for config in configurations:
        config.add_dependency(config.tool)
    return configurations


def get_dependency(tool):
    """
    Returns the parameter dependency as string. Returns None if
    there is not exist any dependency for the given parameter.
    """
    # path relatively to this script's location
    current_directory = os.getcwd() 
    handler.set_working_directory()
    dependencies_path = "../../config/dependencies.json"
    dependencies_json = handler.json_decode(dependencies_path)
    if (dependencies_json is None):
        logging.error("Problem has occurred during the decoding procedure" + \
                      " with the following file: " + dependencies_path + ".")
        return None
    handler.set_working_directory(current_directory)
    if (tool not in dependencies_json):
        logging.warning("Does not exist a dependency for " + tool + ".")
        return None
    else:
        logging.info("A dependency exists: " + tool + "->" + \
                     dependencies_json[tool]["name"] + ".")
        
        return dependencies_json[tool]["name"]

