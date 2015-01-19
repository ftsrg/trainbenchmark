"""
Created on Dec 7, 2014

@author: Zsolt Kovari

A log initialization module.
"""
import os
import time
import logging

import handler


def init_log():
    """Initialize logger handlers.
    """
    handler.set_working_directory()
    os.makedirs("../../log/dist", exist_ok=True)
    os.makedirs("../../log/all", exist_ok=True)
    
    if (os.path.exists("../../log/all/logs.txt") == False):
        open("../../log/all/logs.txt", mode="a").close()
    
    # unique logging file
    log_file = "../../log/dist/" + time.strftime("%Y-%m-%d %H:%M:%S") + ".txt"
    log_file = log_file.replace(" ", "_")
    log_file = log_file.replace(":", "_")
    open(log_file, mode="w").close()
    logging.basicConfig(filename=log_file, format="[%(levelname)s] " +\
                        "Module:%(module)s, Message:%(message)s", level=logging.INFO)
    
    console_handler = logging.StreamHandler()
    # common logging file
    file_handler = logging.FileHandler("../../log/all/logs.txt")
    
    file_handler.setLevel(logging.INFO)
    console_handler.setLevel(logging.ERROR)
    c_formatter = logging.Formatter("[%(levelname)s] Module:%(module)s" + \
                                    ", Message:%(message)s")
    console_handler.setFormatter(c_formatter)
    f_formatter = logging.Formatter("%(asctime)s [%(levelname)s] Module:" + \
                                    "%(module)s, Message:%(message)s")
    file_handler.setFormatter(f_formatter)
    logging.getLogger().addHandler(console_handler)
    logging.getLogger().addHandler(file_handler)


