"""
Created on Oct 4, 2014

@author: Zsolt Kovari

The module provides the certain .jar files relative accessible paths for
instance model generation and benchmark running.
"""
import glob
import logging


def get_generator_jar(format):
    """
    Returns the generator's .jar file's path which belongs to the certain
    format.
    """
    # folder = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/target/" \
    #     .format(FORMAT=format)
    folder = "./target/"
    files = glob.glob(folder + "*.jar")
    for f in files:
        if "-sources.jar" not in f:
            return f

    logging.error("JAR file does not exist of " + format)
    raise FileNotFoundError("JAR file does not exist of " + format)


def get_tool_jar(tool):
    """
    Returns the tool's .jar file's path.
    """
    # folder = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/target/" \
    #    .format(TOOL=tool)
    folder = "./target/"
    files = glob.glob(folder + "*.jar")
    for f in files:
        if "-sources.jar" not in f:
            return f
    logging.error("JAR file does not exist of " + tool)
    raise FileNotFoundError("JAR file does not exist of " + tool)


def get_model_path(format, scenario, size_str):
    """
    Returns the used models' path represented by the certain format, scenario
    and current size.
    """
    if format not in models:
        return None
    if scenario == "Batch":
        scnr = "Repair"
    else:
        scnr = scenario
    file = (common_models_path + "/railway-{SCENARIO}-{SIZE}." +
            models[format]).format(SCENARIO=scnr.lower(), SIZE=size_str)
    return file


def get_common_model_path():
    """Returns the folder's name where all of the models are stored.
    """
    return common_models_path


# the used models extensions
models = {
          'rdf': "ttl",
          'graph': "graphml",
          'emf': "emf",
          'sql': "sql"
          }


common_models_path = "../models"
