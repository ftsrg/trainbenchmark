import subprocess
import util

from typing import List

def build(java_opts: List[str], format_names: List[str], tool_names: List[str], skip_tests: bool):
    profiles = {"core"}
    profiles = profiles.union(format_names)
    profiles = profiles.union(tool_names)

    profiles_arg = ",".join(profiles)

    cmd = ["mvn", "clean", "install", "-P", profiles_arg, "--fail-at-end"]
    if skip_tests:
        cmd.append("-DskipTests")

    try:
        subprocess.check_call(cmd)
    except subprocess.CalledProcessError:
        print(util.highlight("Build failed.", False, True))
        exit(1)
