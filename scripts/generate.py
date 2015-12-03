import subprocess
import util


def generate_models(java_opts, formats, scenarios, sizes, generator_optional_arguments):
    for format in formats:
        for scenario in scenarios:

            # dict only has one item
            for (scenario_name, _) in scenario.items():
                pass

            args = [""]
            if format in generator_optional_arguments:
                for optional_argument in generator_optional_arguments[format]:
                    args.append("-" + optional_argument)

            for arg in args:
                generate_model(java_opts, format, sizes, scenario_name, arg)


def generate_model(java_opts, format, sizes, scenario_name, arg):
    path = "./hu.bme.mit.trainbenchmark.generator.{FORMAT}/".format(FORMAT=format)
    util.set_working_directory(path)
    target = util.get_generator_jar(format)
    for size in sizes:
        cmd = util.flatten(["java", 
             java_opts,
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
