"""
Created on Nov 25, 2014

@author: Zsolt Kovari
The module is responsible for validation.
"""
import os
import logging

from jsonschema import validate, Draft4Validator
from jsonschema.exceptions import ValidationError, SchemaError, best_match


def is_valid_json(instance_json, schema_json):
    """
    Make a validation check whether the instance_json given parameter is 
    valid by the defined constraints of schema_json. Return True if the first 
    parameter is schema based, or return False if that is invalid.
    
    Parameters:
    @param instance_json: json object which will be checked
    @param schema_json: the schema json object  
    """
    try:
        validate(instance_json, schema_json)
    except ValueError as value_error:
        logging.error("Json file is not valid \n", value_error)
        return False
    except ValidationError:
        logging.error("A problem has occurred during the validation, here: " +
                      best_match(Draft4Validator(schema_json)
                                 .iter_errors(instance_json)).message)
        return False
    except SchemaError:
        logging.error("The provided schema is malformed.")
        return False
    
    if instance_json["MinSize"] > instance_json["MaxSize"]:
        logging.error("Maxsize is lower than minsize. Change the " +
                      "config.json file")
        return False
    if len(instance_json["UniqueConfigurations"]) > 0:
        for conf in instance_json["UniqueConfigurations"]:
            if "MinSize" in conf.keys() and "MaxSize" in conf.keys():
                if conf["MinSize"] > conf["MaxSize"]:
                    logging.error("Maxsize is lower than minsize. Change the " +
                                  "config.json file")
    return True
