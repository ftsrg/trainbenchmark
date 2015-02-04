"""
Created on Nov 25, 2014

@author: Zsolt Kovari

The module defines Configuration and Repository classes.
"""

class Configuration:
    """
    The class represents a configuration.
    
    @cvar all_dependencies: contains all dependencies of projects
    """
    
    all_dependencies = list()
    def __init__(self):
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
        #repository.path = self.path
        #dependency.config = self
        self.__dependencies.append(dependency)
        if (dependency in self.all_dependencies):
            self.all_dependencies.remove(dependency)
        self.all_dependencies.append(dependency)

    
    def get_dependencies(self):
        return self.__dependencies


class CommonParameters:
    """
    The class represents a container of the common parameters between
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


class Repository:
    """The class represents a Git repository.
    """
    def __init__(self, name):
        self.name = name
        #self.url = url
        #self.folder = folder
        #self.branch = branch
        #self.depth = depth
        
        # initialized when a Repository is attached to a Configuration object
        #self.path = None
        self.config = None
        
    
    def __eq__(self, other):
        if (self.name == other.name):
            return True
        else:
            return False
   
