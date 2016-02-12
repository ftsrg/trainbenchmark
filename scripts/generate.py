import subprocess
import util
from typing import List


def generate_models(java_opts: List[str], formats: List, scenarios: List, sizes: List[str]):
    for format in formats:
        for format_name, format_option_sets in format.items():
            pass

        if format_option_sets is None:
            format_option_sets = [None]

        for scenario in scenarios:
            # extract the scenario name
            for scenario_name, _ in scenario.items():
                pass

            for format_option_set in format_option_sets:
                generate_model(java_opts, format_name, format_option_set, sizes, scenario_name)

        for format_option_set in format_option_sets:
            generate_minimals(java_opts, format_name, format_option_set)


def generate_model(java_opts: List[str], format_name: str, format_option_set: List, sizes: List[int], scenario_name: str):
    path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format_name)
    util.set_working_directory(path)
    target = util.get_generator_jar(format_name)
    options = util.get_command_line_options(format_option_set)

    for size in sizes:
        cmd = util.flatten(["java",
             java_opts,
             "-jar", target,
             "-scenario", scenario_name,
             "-size", str(size),
             options])
        print(util.highlight(" ".join(cmd), True, True))
        try:
            subprocess.check_call(cmd)
        except subprocess.CalledProcessError:
            print(util.highlight("An error occured during model generation, skipping larger sizes for this scenario/format.", False, True))
            break

    util.set_working_directory("..")


def generate_minimals(java_opts: List[str], format_name: str, format_option_set: List):
    path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format_name)
    util.set_working_directory(path)
    target = util.get_generator_jar(format_name)
    options = util.get_command_line_options(format_option_set)

    queries = ["ConnectedSegments", "PosLength", "RouteSensor", "SemaphoreNeighbor", "SwitchSensor", "SwitchSet"]
    for query in queries:
        cmd = util.flatten(["java",
             java_opts,
             "-jar", target,
             "-scenario", "Minimal",
             "-queryMix", query,
             options])
        print(util.highlight(" ".join(cmd), True, True))
        try:
            subprocess.check_call(cmd)
        except subprocess.CalledProcessError:
            print(util.highlight("An error occured during model generation, skipping larger sizes for this scenario/format.", False, True))
            break

    util.set_working_directory("..")
