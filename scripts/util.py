"""
Created on Sep 28, 2014

@author: Zsolt Kovari

A helper module, which collects the most frequently used functions
by the other modules.

Functions:
    set_working_directory: changes the working directory
    get_power_of_two: creates a list of power of 2 numbers
"""
import os
import logging
import glob


def flatten(lst):
    return sum(([x] if not isinstance(x, list) else flatten(x) for x in lst), [])


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
        os.chdir(path[0] + "/..")
    else:
        if os.path.exists(path):
            os.chdir(path)
        else:
            logging.error("The given parameter is not a valid directory: " +
                          path)


def get_power_of_two(min_size, max_size):
    """
    Returns power of two numbers between minsize and maxsize
    in a list.
    """
    all_sizes = []
    index = 1
    while index <= max_size:
        if index >= min_size:
            all_sizes.append(index)
        index *= 2
    if len(all_sizes) == 0:
        raise RuntimeError('The range between minsize and maxsize is too short.')

    return all_sizes


def get_generator_jar(format):
    """
    Returns the generator's .jar file's path which belongs to the certain
    format.
    """
    folder = "target/"
    files = glob.glob(folder + "*.jar")
    for f in files:
        if "-sources.jar" not in f:
            return f

    raise FileNotFoundError("JAR file does not exist for " + format)


def get_tool_jar(tool):
    """
    Returns the tool's .jar file's path.
    """
    folder = "target/"
    files = glob.glob(folder + "*.jar")
    for f in files:
        if "-sources.jar" not in f:
            return f

    raise FileNotFoundError("JAR file does not exist for " + tool)


def get_model_path(format, scenario, size_str):
    """
    Returns the used models' path represented by the certain format, scenario
    and current size.
    """
    if format not in extensions:
        return None

    scnr = scenario
    file = (common_models_path + "/railway-{SCENARIO}-{SIZE}." +
            extensions[format]).format(SCENARIO=scnr.lower(), SIZE=size_str)
    return file


def get_common_model_path():
    """Returns the folder's name where all of the models are stored.
    """
    return common_models_path


# the used models extensions
extensions = {
          'rdf': "ttl",
          'graph': "graphml",
          'emf': "xmi",
          'sql': "sql"
          }

common_models_path = "../models"
