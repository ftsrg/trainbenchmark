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


from jsonschema.exceptions import ValidationError
from config import Configuration


class Loader():
    config_path = "config/config.json"
    schema_path = "config/config_schema.json"
    tools_path = "config/tools_source.json"
    dependencies_path = "config/dependencies.json"

    def load(self):
        """
        Loads a config.json file and run a validation process.
        If the configurations seem valid, returns Configuration object.

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
            msg = "The file does not exist or cannot be read:" + \
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
            msg = "Validation failed for " + \
                  (os.path.split(self.config_path))[1] + "."
            logging.error(msg)
            raise ValidationError(msg)

        config = Configuration()
        config.iterations = config_json["IterationCount"]
        config.runs = config_json["Runs"]
        config.queries = config_json["Queries"]
        config.scenarios = config_json["Scenarios"]
        config.sizes = util.get_power_of_two(config_json["MinSize"], config_json["MaxSize"])
        config.tools = config_json["Tools"]

        return config

    def get_dependency(self, tool):
        """
        Returns the tool's dependency as string. And returns None in that case
        if a dependency would not exist.
        """
        # path relatively to this script's location
        current_directory = os.getcwd()
        util.set_working_directory()
        dependencies_json = util.json_decode(self.dependencies_path)
        if dependencies_json is None:
            logging.error("Problem has occurred during the decoding procedure" +
                          " with the following file: " + self.dependencies_path)
            return None
        util.set_working_directory(current_directory)
        if tool not in dependencies_json:
            logging.warning("Dependency for " + tool + " does not exist.")
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
