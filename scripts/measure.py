import send_mail
import subprocess
import util

import os
import glob
from typing import List


def measure_tools(java_opts: List[str], timeout: int, runs: int, scenarios: List, sizes: List[int], tools: List, query_mixes: List, email, max_levels: int, max_memory: int):
    for scenario in scenarios:
        transformation_arguments = []

        # dict only has one item
        for (scenario_name, scenario_arguments) in scenario.items():
            if scenario_arguments is not None:
                for arg, value in scenario_arguments.items():
                    transformation_arguments.append("-" + arg)
                    transformation_arguments.append(str(value))

        for tool in tools:
            for tool_name, tool_option_sets in tool.items():
                pass

            path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".format(TOOL=tool_name)
            util.set_working_directory(path)
            target = util.get_tool_jar(tool_name)

            if tool_option_sets is None:
                tool_option_sets = [None]

            for query_mix in query_mixes:
                for tool_option_set in tool_option_sets:
                    for size in sizes:
                        print(util.highlight("Tool: " + tool_name + ", scenario: " + scenario_name + ", size: " + str(size) + ", query mix: " + query_mix + ", runs: " + str(runs), "info", True))
                        print(util.highlight("==============================================================================", "info", True))
                        measure_tool(java_opts, timeout, target, scenario_name, runs, size, query_mix, transformation_arguments, tool_option_set, max_levels, max_memory)

            util.set_working_directory("..")
    send_mail.send_mail(email)


def measure_tool(java_opts: List[str], timeout: int, target: str, scenario_name: str, runs: int, size: int, query_mix: List[str], transformation_arguments: List, tool_option_set: List, max_levels: int, max_memory: int):
    options = util.get_command_line_options(tool_option_set)
    level = 0
    initial_memory = max_memory
    memory_quantum = max_memory

    while (level < max_levels and max_memory <= initial_memory):
        level += 1
        memory_quantum //= 2
        print(util.highlight("Level " + str(level) + ". Testing with maximum memory of " + str(max_memory) + " MB.", "info", True))

        cmd = util.flatten(["java",
               java_opts,
               "-Xms" + str(max_memory) + "M",
               "-Xmx" + str(max_memory) + "M",
               "-jar", target,
               "-runs", str(runs),
               "-scenario", scenario_name,
               "-queryMix", query_mix.split(" "),
               "-maxMemory", str(max_memory),
               "-size", str(size),
               transformation_arguments,
               options])

        print(util.highlight(" ".join(cmd), "debug", True))
        if test(cmd, timeout):
            print(util.highlight("Execution finished, testing with less memory.", "success", False))
            max_memory -= memory_quantum
        else:
            print(util.highlight("Execution failed, testing with more memory.", "failure", False))
            max_memory += memory_quantum

def test(cmd, timeout):
    try:
        subprocess.check_call(cmd, timeout=timeout)
    except subprocess.TimeoutExpired:
        print(util.highlight("Timeout, skipping larger sizes for this tool, scenario and query mix.", "error", True))
        return False
    except subprocess.CalledProcessError:
        print(util.highlight("An error occured, skipping larger sizes for this tool, scenario and query mix.", "error", True))
        return False
    return True
