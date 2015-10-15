#!/usr/bin/env python3
"""
Created on Sep 28, 2014

@author: Zsolt Kovari

This script assumes that the required dependencies are available from either 
the local Maven repository or the Maven Central Repository.
"""
import subprocess
import argparse

import yaml
import util

import os
import glob

import smtplib
from email.mime.text import MIMEText


def flatten(lst):
    return sum(([x] if not isinstance(x, list) else flatten(x) for x in lst), [])


def build(config, formats, skip_tests):
    profiles = {"core"}
    profiles = profiles.union(formats)
    profiles = profiles.union(config["tools"])

    profiles_arg = ",".join(profiles)

    cmd = ["mvn", "clean", "install", "-P", profiles_arg, "--fail-at-end"]
    if skip_tests:
        cmd.append("-DskipTests")
    subprocess.check_call(cmd)


def build_ci():
    cmd_ci = ["mvn", "clean", "install", "-P", "ci", "--fail-at-end"]
    subprocess.check_call(cmd_ci)
    # skip the tests for tools with third-party dependencies
    cmd_notest = ["mvn", "clean", "install", "-P", "notest", "-DskipTests", "--fail-at-end"]
    subprocess.check_call(cmd_notest)


def generate(config, formats):
    for format in formats:
        for scenario in config["scenarios"]:

            # dict only has one item
            for (scenario_name, _) in scenario.items():
                pass

            args = [""]
            if format in config["generator_optional_arguments"]:
                for optional_argument in config["generator_optional_arguments"][format]:
                    args.append("-" + optional_argument)

            for arg in args:
                path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format)
                util.set_working_directory(path)
                target = util.get_generator_jar(format)
                for size in config["sizes"]:
                    cmd = flatten(["java", 
                         config["java_opts"],
                         "-jar", target,
                         "-scenario", scenario_name,
                         "-size", str(size),
                         arg])
                    try:
                        subprocess.check_call(cmd)
                    except subprocess.CalledProcessError:
                        print("An error occured during model generation, skipping larger sizes for this scenario/format.")
                        break
                util.set_working_directory("..")


def measure(config):
    for scenario in config["scenarios"]:
        transformation_arguments = []

        # dict only has one item
        for (scenario_name, scenario_arguments) in scenario.items():
            if scenario_arguments is not None:
                for arg, value in scenario_arguments.items():
                    transformation_arguments.append("-" + arg)
                    transformation_arguments.append(str(value))

        for tool in config["tools"]:
            args = [""]
            if tool in config["benchmark_optional_arguments"]:
                for optional_argument in config["benchmark_optional_arguments"][tool]:
                    args.append("-" + optional_argument)

            for arg in args:
                path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".format(TOOL=tool)
                util.set_working_directory(path)
                target = util.get_tool_jar(tool)

                for query in config["queries"]:
                    for size in config["sizes"]:
                        
                        # remove all files in the temporary results directory
                        prev_files = glob.glob('../results/json/*')
                        for f in prev_files:
                            os.remove(f)

                        print("Running benchmark... " +
                              "runs: " + str(config["runs"]) +
                              ", tool: " + tool +
                              ", scenario: " + scenario_name +
                              ", query: " + query +
                              ", size: " + str(size) +
                              (", argument: " + arg if arg != "" else ""))
                        cmd = flatten(["java",
                               config["java_opts"],
                               "-jar", target,
                               "-runs", str(config["runs"]),
                               "-scenario", scenario_name,
                               "-query", query,
                               "-size", str(size),
                               transformation_arguments,
                               arg])
                        
                        try:
                            subprocess.check_call(cmd, timeout=config["timeout"])
                        except subprocess.TimeoutExpired:
                            print("Timeout, skipping larger sizes for this tool/scenario/query.")
                            break
                        except subprocess.CalledProcessError:
                            print("An error occured, skipping larger sizes for this tool/scenario/query.")
                            break

                        # if the runs were successful, move all files to the results
                        result_files = glob.glob('../results/json/*')
                        for f in result_files:
                            name = os.path.basename(f)
                            os.rename(f, '../results/completed/' + name)

                util.set_working_directory("..")


def send_mail(config):
    if "email" in config:
        address = config["email"]["address"]
        password = config["email"]["password"]
        host = config["email"]["host"]

        msg = MIMEText("<helpful information about the run>")
        msg["Subject"] = "Train Benchmark measurement ready"
        msg["From"] = address
        msg["To"] = address
        
        session = smtplib.SMTP(host)	 
        session.ehlo()
        session.starttls()
        session.login(address, password)
        session.sendmail(from_addr=address, to_addrs=[address], msg=msg.as_string())


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("-c", "--ci",
                        help="CI build",
                        action="store_true")
    parser.add_argument("-b", "--build",
                        help="build the projects",
                        action="store_true")
    parser.add_argument("-g", "--generate",
                        help="generate models",
                        action="store_true")
    parser.add_argument("-m", "--measure",
                        help="run the benchmark",
                        action="store_true")
    parser.add_argument("-s", "--skip-tests",
                        help="skip JUnit tests",
                        action="store_true")
    args = parser.parse_args()

    # set working directory to this file's path
    util.set_working_directory()

    with open("config/config.yml", 'r') as stream:
        config = yaml.load(stream)
    config["sizes"] = util.get_power_of_two(config["min_size"], config["max_size"])

    with open("config/formats.yml", 'r') as stream:
        tool_formats = yaml.load(stream)

    formats = set()
    for tool in config["tools"]:
        formats.add(tool_formats[tool])

    # if there are no args, execute a full sequence
    # with the test and the visualization/reporting
    no_args = all(val is False for val in vars(args).values())
    if no_args:
        args.build = True
        args.generate = True
        args.measure = True
    
    if args.ci:
        build_ci()
    if args.build:
        build(config, formats, args.skip_tests)
    if args.generate:
        generate(config, formats)
    if args.measure:
        measure(config)
        send_mail(config)
