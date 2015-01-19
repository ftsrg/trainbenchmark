"""
Created on Nov 25, 2014

@author: Zsolt Kovari

The module defines Configuration and Repository classes.
"""

class Configuration:
    """
    The class represents a configuration.
    
    To add a new Repository object to self.repositories, call add_repository()
    method instead of self.repositories.append().
    
    @cvar all_repositories: contains all Repository objects which are attached
          to any Configuration object
    """
    
    all_repositories = list()
    def __init__(self, scenarios, format, tool, sizes, queries, path,\
                 measurements, maven_xmx, maven_maxpermsize, java_xmx,\
                 java_maxpermsize):
        self.scenarios = scenarios
        self.format = format
        self.tool = tool
        self.sizes = sizes
        self.queries = queries
        self.path = path
        self.measurements = measurements
        self.maven_xmx = maven_xmx 
        self.maven_maxpermsize = maven_maxpermsize
        self.java_xmx = java_xmx
        self.java_maxpermsize = java_maxpermsize
        self.__repositories = list()
    
    
    def add_repository(self, repository):
        """
        Set the configuration repository which represents the tool's location.
        
        Parameters:
        repository: a Repository object
        """
        repository.path = self.path
        repository.config = self
        self.__repositories.append(repository)
        if (repository in self.all_repositories):
            self.all_repositories.remove(repository)
        self.all_repositories.append(repository)

    
    def get_repositories(self):
        return self.__repositories


class Repository:
    """The class represents a Git repository.
    """
    def __init__(self, name, url, folder, branch, depth):
        self.name = name
        self.url = url
        self.folder = folder
        self.branch = branch
        self.depth = depth
        # initialized when a Repository is attached to a Configuration object
        self.path = None
        self.config = None
        
    
    def __eq__(self, other):
        if (self.name == other.name and self.path == other.path):
            return True
        else:
            return False
   
