import subprocess
import util

import os
import glob


def measure_tools(java_opts, timeout, runs, scenarios, sizes, tools, query_mixes, benchmark_optional_arguments):
    for scenario in scenarios:
        transformation_arguments = []

        # dict only has one item
        for (scenario_name, scenario_arguments) in scenario.items():
            if scenario_arguments is not None:
                for arg, value in scenario_arguments.items():
                    transformation_arguments.append("-" + arg)
                    transformation_arguments.append(str(value))

        for tool in tools:
            path = "./hu.bme.mit.trainbenchmark.benchmark.{TOOL}/".format(TOOL=tool)
            util.set_working_directory(path)
            target = util.get_tool_jar(tool)

            args = [""]
            if tool in benchmark_optional_arguments:
                for optional_argument in benchmark_optional_arguments[tool]:
                    args.append("-" + optional_argument)

            for arg in args:
                for query_mix in query_mixes:
                    measure_tool(java_opts, timeout, target, scenario_name, runs, sizes, query_mix, transformation_arguments, arg)

            util.set_working_directory("..")


def measure_tool(java_opts, timeout, target, scenario_name, runs, sizes, query_mix, transformation_arguments, arg):
    for size in sizes:
        # remove all files in the temporary results directory
        prev_files = glob.glob('../results/json/*')
        for f in prev_files:
            os.remove(f)

        cmd = util.flatten(["java",
               java_opts,
               "-jar", target,
               "-runs", str(runs),
               "-scenario", scenario_name,
               "-querymix", query_mix.split(" "),
               "-size", str(size),
               transformation_arguments,
               arg])
        print(cmd)
        
        try:
            subprocess.check_call(cmd, timeout=timeout)
        except subprocess.TimeoutExpired:
            print("Timeout, skipping larger sizes for this tool, scenario and query mix.")
        except subprocess.CalledProcessError:
            print("An error occured, skipping larger sizes for this tool, scenario and query mix.")
            break

        # if the runs were successful, move all files to the results
        result_files = glob.glob('../results/json/*')
        for f in result_files:
            name = os.path.basename(f)
            os.rename(f, '../results/completed/' + name)
