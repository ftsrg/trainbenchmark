"""
Created on Nov 25, 2014

@author: Zsolt Kovari

The module defines Configuration class.
"""


class Configuration:
    """
    Represents a configuration.
    
    @cvar all_dependencies: contains all dependencies of the tool
    """
    
    all_dependencies = list()

    def __init__(self):
        self.generator_args = None
        self.benchmark_args = None
        self.scenarios = None
        self.format = None
        self.tool = None
        self.sizes = None
        self.queries = None
        self.common = None
        self.__dependencies = list()
    
    def add_dependency(self, dependency):
        """
        Set the configuration repository which represents the tool's location.
        
        Parameters:
        repository: a Repository object
        """
        self.__dependencies.append(dependency)
        if dependency in self.all_dependencies:
            self.all_dependencies.remove(dependency)
        self.all_dependencies.append(dependency)

    def get_dependencies(self):
        return self.__dependencies


class CommonParameters:
    """
    Represents a container of the common parameters between
    configurations.
    """
    
    def __init__(self):
        self.maven_xmx = None 
        self.maven_maxpermsize = None
        self.java_xmx = None
        self.java_maxpermsize = None
        self.series = None
        self.modif_method = None
        self.modif_constant = None
        self.iter_count = None
        self.path = None
