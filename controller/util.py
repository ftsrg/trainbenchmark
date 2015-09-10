"""
Created on Sep 28, 2014

@author: Zsolt Kovari

A helper module, which collects the most frequently used functions 
by the other modules.

Functions:
    set_working_directory: changes the working directory
    get_power_of_two: creates a list of power of 2 numbers
    json_decode: loads json file to a python json object
    get_package_name: returns a corresponding package name
"""
import json
import os
import logging


def set_working_directory(path=None):
    """
    Sets the working directory to this script's folder or to the path
    optional parameter, if that is given.
    
    Parameters:
    @param path: optional parameter, a directory 
    """
    if path is None:
        full_path = os.path.realpath(__file__)
        path = os.path.split(full_path)
        os.chdir(path[0])
    else:
        if os.path.exists(path):
            os.chdir(path)
        else:
            logging.error("The given parameter is not a valid directory:" +
                          path)


def get_power_of_two(minsize, maxsize):
    """
    Returns power of two numbers between minsize and maxsize
    in a list.
    """
    all_size = []
    index = 1
    while index <= maxsize:
        if index >= minsize:
            all_size.append(index)
        index += 1
    return all_size


def json_decode(json_path):
    """
    Opens a .json file and returns as a python json object.
    The json_path parameter is the path of the file.
    """
    try:
        with open(json_path) as file:
            json_object = json.load(file)
    except IOError:
        logging.error("The file does not exist or cannot read:" +
                      (os.path.split(json_path))[1])
    except ValueError as value_error:
        logging.error((os.path.split(json_path))[1] +
                      " file is not valid \n", value_error)
    else:
        return json_object
    return None


def get_package_name(param):
    """Returns a package name which corresponds to the param parameter.
    """
    packages = {
        'drools5': 'drools',
        'drools6': 'drools'
    }
    if param in packages:
        return packages[param]
    return param
