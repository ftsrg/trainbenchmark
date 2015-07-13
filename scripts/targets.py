"""
Created on Oct 4, 2014

@author: Zsolt Kovari

The module provides the certain .jar files relative accessible paths for
instance model generation and benchmark running.
"""
import glob


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
          'emf': "emf",
          'sql': "sql"
          }

common_models_path = "../models"
