import subprocess


def build(java_opts, formats, tools, skip_tests):
    profiles = {"core"}
    profiles = profiles.union(formats)
    profiles = profiles.union(tools)

    profiles_arg = ",".join(profiles)

    cmd = ["mvn", "clean", "install", "-P", profiles_arg, "--fail-at-end"]
    if skip_tests:
        cmd.append("-DskipTests")
    subprocess.check_call(cmd)
