"""
Created on Nov 26, 2014

@author: Zsolt Kovari
Defines more unique dependencies and install them.
"""
import os
import shutil
import subprocess
import logging

import handler


def install_dependencies(name, path):
    """
    Install any dependencies for the given name parameter if necessary.
    
    Parameters:
    @param name: search dependency for this parameter
    @param path: the location to install
    """
    if name in install_deps:
        install_deps[name](path)


def build_unique_tools(package):
    """Handle any special build procedure which required by the certain tools.
    
    Parameters:
    @param name: tool identity
    """
    if package in build_deps:
        build_deps[package](package)
        return True
    return False


def install_neo4j_deps(path):
    """
    Clones and builds neo4j-shell-tools and rdf-graph-drivers.
    """
    # change back working directory later, so store it now
    current_directory = os.getcwd() 
    # change working directory to this module's location
    handler.set_working_directory()
    # jump to the project parent folder since path can be relative
    if os.path.exists("../../../../neo4j-shell-tools"):
        logging.info("Neo4j-shell-tools has been deployed.")
    else:
        handler.set_working_directory("../../shell-scripts/")
        subprocess.call(["./install_neo4j.sh"])
    # Set the working directory to this script's folder.
    handler.set_working_directory(current_directory)


def build_virtuoso(package):
    current_directory = os.getcwd()
    handler.set_working_directory()
    handler.set_working_directory("../../shell-scripts/")
    subprocess.call(["./build_virtuoso.sh"])

    handler.set_working_directory(current_directory)



def build_allegro(package):
    current_directory = os.getcwd()
    handler.set_working_directory()
    handler.set_working_directory("../../shell-scripts/")
    subprocess.call(["./build_allegro.sh"])
    
    handler.set_working_directory(current_directory)

install_deps = {
    'neo4j': install_neo4j_deps
}
build_deps = {
    'virtuoso': build_virtuoso,
    'allegro': build_allegro
}
