"""
Created on Nov 26, 2014

@author: Zsolt Kovari
Defines more unique dependencies and install them.
"""
import os
import shutil
import subprocess

import handler

def install_dependencies(name, path):
    """
    Install any dependencies for the given name parameter if necessary.
    
    Parameters:
    @param name: search dependency for this parameter
    @param path: the location to install
    """
    if (name in install_deps):
        install_deps[name](path)


def install_neo4j_deps(path):
    """
    Install gradle then clone/build neo4j-shell-tools and geoff 
    under that folder which is given via path parameter.
    """
    # change back working directory later, so store it now
    current_directory = os.getcwd() 
    # change working directory to this module's location
    handler.set_working_directory()
    # jump to the project parent folder since path can be relative
    handler.set_working_directory("../../../")
    handler.set_working_directory(path)
    if (os.path.exists("./trainbenchmark-neo4j/scripts/init-neo4j") == True):
        print("The Neo4j install is already initialized. If you encounter"\
             + "any problems, please delete the init-done file"\
             + "in the trainbenchmark-neo4j/scripts directory.")
        return
    if (os.path.exists("./deps") == True):
        shutil.rmtree("./deps") # delete it and every subfolder
    os.mkdir("./deps")
    # install_neo4j shell script
    handler.set_working_directory()
    subprocess.call(["../../shell-scripts/install_neo4j.sh", path])
    # Set the working directory to this script's folder.
    handler.set_working_directory("../../../")
    handler.set_working_directory(path)
    # create an empty file
    new_file = open("./trainbenchmark-neo4j/scripts/init-neo4j", "w")
    new_file.close()


install_deps = {
                'neo4j':install_neo4j_deps
                }
