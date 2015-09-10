"""
Created on Nov 25, 2014

@author: Zsolt Kovari

The module defines Configuration  and CommonParameters class.
"""


class Configuration:
    """
    Represents a configuration.
    
    @cvar all_dependencies: contains every dependency of the tools
    """
    
    all_dependencies = list()

    def __init__(self):
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
        Add a project's identifier to the object's dependencies. The parameter
        is attached to the all_dependencies variable too, as the last element
        of the list.

        Parameters:
        @param dependency: String variable
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
        self.generator_args = None
        self.maven_xmx = None
        self.java_xmx = None
        self.series = None
        self.modif_method = None
        self.modif_constant = None
        self.iter_count = None
        self.path = None
        self.models = None
        self.submodels = None
