"""
Created on Nov 25, 2014

@author: Zsolt Kovari
The module is responsible for validation.
"""
import os

import handler

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
        validate(instance_json,schema_json)
    except ValueError as value_error:
        print("Json file is not valid \n", value_error)
        return False
    except ValidationError as validation_error:
        print("A problem has occurred during the validation, here:")
        # determine the error's first place
        print(best_match(Draft4Validator(schema_json)\
                         .iter_errors(instance_json)).message)
        return False
        #rint(best_match(Draft4Validator(schema_json).iter_errors(instance_json)).message)
        #print(ErrorTree(Draft4Validator(schema_json).iter_errors(instance_json)))
    except SchemaError:
        print("The provided schema is malformed.")
        return False
    
    if (instance_json["minSize"] > instance_json["maxSize"]):
        print("Maxsize is lower than minsize. Change the "\
              "config.json file")
        return False
    
    # store the actual working directory to change back later
    current_path = os.getcwd()
    # set the working directory to this module's path
    handler.set_working_directory();
    # instance_json["workspacePath"] must be checked relatively
    handler.set_working_directory("../../../")
    if (os.path.exists(instance_json["workspacePath"]) == False):
        print(instance_json["workspacePath"] +\
              "is not an existing directory.")
        handler.set_working_directory(current_path);
        return False
    elif(os.path.isdir(instance_json["workspacePath"]) == False):
        print(instance_json["workspacePath"] + " is not a directory!")
        handler.set_working_directory(current_path);
        return False
    handler.set_working_directory(current_path);
    return True
