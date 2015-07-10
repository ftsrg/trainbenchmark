#!/usr/bin/env python3
"""
Created on Mar 03, 2015

@author: Zsolt Kovari

"""
import unittest

from loader import Loader
from generate import Generator
import generate
import util


class GenerationTest(unittest.TestCase):
    
    @classmethod
    def setUpClass(cls):
        cls.loader = Loader()
        cls.generator = Generator()
        cls.id = 0
        cls.configs = None
        cls.models = None
    
    def load(self):
        self.loader.config_path = "tests/" + str(self.id) + ".json"
        self.configs = self.loader.load()
    
    def prevent(self):
        self.generator.prevent_multiple_generation(self.configs)
        self.models = self.generator.models 
        
    def all_size(self):
        size = 0
        for scenario in self.models:
            for format in self.models[scenario]:
                size += len(self.models[scenario][format])
        return size
    
    def size(self, scenario, format):
        return len(self.models[scenario][format])
    
    def test_generation_1(self):
        self.id = 1
        self.load()
        self.prevent()
        self.assertEqual(4, self.size("User", "graph"))
        self.assertEqual(4, self.all_size())
    
    def test_generation_2(self):
        self.id = 2
        self.load()
        self.prevent()
        self.assertEqual(3, self.size("User", "graph"))
        self.assertEqual(3, self.size("User", "emf"))
        self.assertEqual(6, self.all_size())
        
    def test_generation_3(self):
        self.id = 3
        self.load()
        self.prevent()
        self.assertEqual(3, self.size("User", "graph"))
        self.assertEqual(3, self.size("User", "emf"))
        self.assertEqual(3, self.size("User", "sql"))
        self.assertEqual(3, self.size("Repair", "graph"))
        self.assertEqual(3, self.size("Repair", "emf"))
        self.assertEqual(3, self.size("Repair", "sql"))
        self.assertEqual(18, self.all_size())

    def test_generation_4(self):
        self.id = 4
        self.load()
        self.prevent()
        self.assertEqual(6, self.size("User", "graph"))
        self.assertEqual(6, self.size("User", "emf"))
        self.assertEqual(6, self.size("User", "sql"))
        self.assertEqual(6, self.size("User", "rdf"))
        self.assertEqual(6, self.size("Repair", "graph"))
        self.assertEqual(6, self.size("Repair", "emf"))
        self.assertEqual(6, self.size("Repair", "sql"))
        self.assertEqual(6, self.size("Repair", "rdf"))
        self.assertEqual(48, self.all_size())

    def test_generation_5(self):
        self.id = 5
        self.load()
        self.prevent()
        self.assertEqual(4, self.size("User", "graph"))
        self.assertEqual(4, self.size("User", "emf"))
        self.assertEqual(4, self.size("User", "sql"))
        self.assertEqual(4, self.size("User", "rdf"))
        self.assertEqual(4, self.size("Repair", "graph"))
        self.assertEqual(4, self.size("Repair", "emf"))
        self.assertEqual(4, self.size("Repair", "sql"))
        self.assertEqual(4, self.size("Repair", "rdf"))
        self.assertEqual(32, self.all_size())

    def test_generation_6(self):
        self.id = 6
        self.load()
        self.prevent()
        self.assertEqual(4, self.size("User", "rdf"))
        self.assertEqual(2, self.size("Repair", "rdf"))
        self.assertEqual(6, self.all_size())
        
    def test_generation_7(self):
        self.id = 7
        self.load()
        self.prevent()
        self.assertEqual(6, self.size("User", "rdf"))
        self.assertEqual(1, self.size("Repair", "rdf"))
        self.assertEqual(2, self.size("User", "graph"))
        self.assertEqual(2, self.size("Repair", "graph"))
        self.assertEqual(4, self.size("User", "sql"))
        self.assertEqual(0, self.size("Repair", "sql"))
        self.assertEqual(15, self.all_size())

    def test_generation_8(self):
        self.id = 8
        self.load()
        self.prevent()
        self.assertEqual(7, self.size("User", "rdf"))
        self.assertEqual(1, self.size("Repair", "rdf"))
        self.assertEqual(4, self.size("User", "graph"))
        self.assertEqual(4, self.size("Repair", "graph"))
        self.assertEqual(6, self.size("User", "emf"))
        self.assertEqual(0, self.size("Repair", "emf"))
        self.assertEqual(22, self.all_size())


if __name__ == "__main__":
    util.set_working_directory()
    unittest.main(verbosity=2, failfast=False)