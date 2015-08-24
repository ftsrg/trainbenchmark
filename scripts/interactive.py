#!/usr/bin/env python3

import os
import subprocess


def set_working_directory(path=None):
    """
    Sets the working directory to this script's folder or to the path
    optional parameter, if that is given.

    Parameters:
    @param path: optional parameter, a directory
    """
    if path is None:
        full_path = os.path.realpath(__file__)
        path = os.path.split(full_path)
        os.chdir(path[0])
    else:
        if os.path.exists(path):
            os.chdir(path)
        else:
            print("The given parameter is not a valid directory:" + path)


if __name__ == "__main__":
    set_working_directory()
    if not os.path.exists("../../mondo-sam"):
        set_working_directory("../../")
        subprocess.call(["git", "clone",
                         "https://github.com/FTSRG/mondo-sam.git",
                         "mondo-sam", "--branch", "master"])
        set_working_directory("./mondo-sam/reporting")
    else:
        set_working_directory("../../mondo-sam/reporting")

    subprocess.call("./interactive.sh")