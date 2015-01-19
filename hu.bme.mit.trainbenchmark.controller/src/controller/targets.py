"""
Created on Oct 4, 2014

@author: Zsolt Kovari

The module provides the certain .jar files relative accessible paths for
instance model generation and benchmark running.
"""
import glob

import handler


def get_generator_jar(format):
    """
    Return the generate's .jar file's path which belongs to the certain 
    format. The latter is provided by the format parameter.
    """
    folder = ("./trainbenchmark-{FORMAT}/"\
           + "hu.bme.mit.trainbenchmark.generator.{FORMAT}/target/"\
           ).format(FORMAT=format)
    files = glob.glob(folder + "*.jar")
    if (len(files) >0):
        target = files[0]
        return target
    else:
        return None


def get_benchmark_jar(tool):
    """
    Return the benchmark's .jar file's path which belongs to the certain 
    tool. The latter is provided by the tool parameter.
    """
    folder = ("./trainbenchmark-{PACKAGE}/"\
               + "hu.bme.mit.trainbenchmark.benchmark.{TOOL}/target/"\
               ).format(PACKAGE=handler.get_package_name(tool),TOOL=tool)
    files = glob.glob(folder + "*.jar")
    if (len(files) >0):
        target = files[0]
        return target
    else:
        print("Jar file not found!")
        return None


def get_model_path(format, scenario, size_str):
    """
    Return the used models' path by the certain format, scenario 
    and the actual size.
    """
    if (format not in models):
        return None
    if (scenario == "Batch"):
        scnr = "XForm"
    else:
        scnr = scenario
    # first format is the parameter, second is an embedded python function
    file = (common_models_path + "/railway-{SCENARIO}-{SIZE}." + \
            models[format]).format(SCENARIO=scnr.lower(),SIZE=size_str)
    return file


def get_common_model_path():
    """Return the folder's name where all of the models are stored.
    """
    return common_models_path


# the used models extensions
models = {
          'rdf': "ttl",
          'graph': "graphml",
          'emf': "concept",
          'sql': "sql"
          }


common_models_path = "./trainbenchmark-models"