#!/usr/bin/env python3
import subprocess
import argparse

import yaml
import util

if __name__ == "__main__":

    with open("config/config.yml", 'r') as stream:
        config = yaml.load(stream)

    formats = ["emf", "graph", "rdf", "sql"]

    for format in formats:
        for query in config["queries"]:
            args = [""]
            if format in config["generator_optional_arguments"]:
                for optional_argument in config["generator_optional_arguments"][format]:
                    args.append("-" + optional_argument)

            for arg in args:
                path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format)
                util.set_working_directory(path)
                target = util.get_generator_jar(format)
                cmd = ["java", "-Xmx" + config["java_opts"]["xmx"],
                     "-jar", target,
                     "-scenario", "Minimal",
                     "-query", query,
                   arg]
                try:
                    subprocess.check_call(cmd)
                except subprocess.CalledProcessError:
                    print("An error occured during model generation.")
                util.set_working_directory("..")
