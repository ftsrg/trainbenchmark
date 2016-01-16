import subprocess
import util

from typing import List
from util import flatten

def build(java_opts: List[str], format_names: List[str], tool_names: List[str], skip_tests: bool, test: bool, offline: bool):
    profiles = {"core"}
    profiles = profiles.union(format_names)
    profiles = profiles.union(tool_names)

    profiles_arg = ",".join(profiles)

    if test:
        mvn_args = "test"
    else:
        mvn_args = ["clean", "install"]

    if offline:
        mvn_args += ["--offline"]

    cmd = flatten(["mvn", mvn_args, "--activate-profiles", profiles_arg, "--fail-at-end"])
    if skip_tests:
        cmd.append("-DskipTests")

    print(cmd)
    try:
        subprocess.check_call(cmd)
    except subprocess.CalledProcessError:
        print(util.highlight("Build failed.", False, True))
        exit(1)
