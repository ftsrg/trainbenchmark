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
from config import Configuration, Repository


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
    #config_json = handler.json_decode(config_path)
    #if (config_json is None):
    #    return None
    try:
        with open(config_path, mode="r") as file:
            all_config_string = file.read()
        decoder= json.JSONDecoder(object_pairs_hook=checking_hook)
        all_config_json = decoder.decode(all_config_string)
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
    
    configurations = list()
    for top_element in all_config_json: 
        # iterate every embedded configuration and create every one of them
        # another json object
        config_json = all_config_json[top_element]
        valid = validation.is_valid_json(config_json, schema_json)
        if (valid == False):
            logging.error("Validation failed of " + \
                          (os.path.split(config_path))[1] + " file.")
            return None
    
        sizes = handler.get_power_of_two(config_json["minSize"],\
                                         config_json["maxSize"])
        if (len(sizes) == 0):
            logging.error("Problem with min and maxsize."+ \
                          "Too short the range between them.")
            return None
        scenarios = config_json["scenarios"]
        #format = config_json["format"]
        queries = config_json["queries"]
    
        # workspacePath can be relative
        handler.set_working_directory()
        handler.set_working_directory("../../../")
        handler.set_working_directory(config_json["workspacePath"])
        path = os.getcwd() # store the absolute path instead of relative
    
        series = config_json["series"]
        maven_xmx = config_json["MAVEN_OPTS"]["Xmx"]
        maven_maxpermsize = config_json["MAVEN_OPTS"]["XX:MaxPermSize"]
        java_xmx = config_json["JAVA_OPTS"]["xmx"]
        java_maxpermsize = config_json["JAVA_OPTS"]["maxPermSize"]
    
        # create a Configuration object for every tool
        for tool in config_json["tools"]:
            format = tools_json[tool]["format"]
            configurations.append(Configuration(scenarios, format, tool, sizes,\
                                                queries, path, series, \
                                                maven_xmx, maven_maxpermsize, \
                                                java_xmx, java_maxpermsize))
    
    for config in configurations:
        # add a new Repository object to every Configuration
        config.add_repository(Repository(config.tool,\
                                         tools_json[config.tool]["url"],\
                                         tools_json[config.tool]["folder"],\
                                         tools_json[config.tool]["branch"],\
                                         tools_json[config.tool]["depth"]))
    return configurations


def get_dependency(tool):
    """
    Returns the parameter dependency as a Repository object. Returns None if
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
        return Repository(dependencies_json[tool]["name"],\
                          dependencies_json[tool]["url"],\
                          dependencies_json[tool]["folder"],\
                          dependencies_json[tool]["branch"],\
                          dependencies_json[tool]["depth"])
