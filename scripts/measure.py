import send_mail
import subprocess
import util

import os
import glob


def measure_tools(java_opts, timeout, runs, scenarios, sizes, tools, query_mixes, email):
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

            if tool_option_sets is not None:
                for tool_option_set in tool_option_sets:
                    for query_mix in query_mixes:
                        measure_tool(java_opts, timeout, target, scenario_name, runs, sizes, query_mix, transformation_arguments, tool_option_set)

            util.set_working_directory("..")
    send_mail.send_mail(email)


def measure_tool(java_opts, timeout, target, scenario_name, runs, sizes, query_mix, transformation_arguments, tool_option_set):
    for size in sizes:
        # remove all files in the temporary results directory
        prev_files = glob.glob('../results/json/*')
        for f in prev_files:
            os.remove(f)

        options = util.get_command_line_options(tool_option_set)

        cmd = util.flatten(["java",
               java_opts,
               "-jar", target,
               "-runs", str(runs),
               "-scenario", scenario_name,
               "-querymix", query_mix.split(" "),
               "-size", str(size),
               transformation_arguments,
               options])
        print(util.highlight(" ".join(cmd), True, True))
        
        try:
            subprocess.check_call(cmd, timeout=timeout)
        except subprocess.TimeoutExpired:
            print(util.highlight("Timeout, skipping larger sizes for this tool, scenario and query mix.", True, True))
        except subprocess.CalledProcessError:
            print(util.highlight("An error occured, skipping larger sizes for this tool, scenario and query mix.", True, True))
            break

        # if the runs were successful, move all files to the results
        result_files = glob.glob('../results/json/*')
        for f in result_files:
            name = os.path.basename(f)
            os.rename(f, '../results/completed/' + name)
