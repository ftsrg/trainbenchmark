#!/usr/bin/env python3
"""
Created on Feb 24, 2015

@author: Zsolt Kovari

Converts JSON files to CSV.
"""
import argparse
import glob
import json
import csv
import os

import handler


def load_results(path):
    """
    Loads json files under the given path parameter and store them in a list as 
    json objects.
    
    Parameters:
    @param path: the location of json files
    """
    file_paths = glob.glob(path + "/*.json")
    json_objects = list()
    for file_path in file_paths:
        with open(file_path) as file:
            json_objects.append(json.load(file))
    return json_objects


def write_to_csv(json_objects, csvpath):
    """Convert json objects into csv.
    
    Parameters:
    @param json_objects: a list of json objects 
    @param csvpath: location of the CSV file
    """
    full_path = os.path.realpath(csvpath)
    path = os.path.split(full_path)
    if (os.path.exists(path[0]) == False):
        os.mkdir(path[0])  
    with open(csvpath, mode='w') as csvfile:
        headers = ["Size", "PhaseName", "MetricName", "Sequence", \
                      "MetricValue", "Scenario", "CaseName", "RunIndex", "Tool"]
        writer = csv.DictWriter(csvfile, headers)
        writer.writeheader()
        
        for result in json_objects:
            sequence = 1
            row = dict()
            for h in headers:
                row.update({h:"NaN"})
            row.update({"Tool":result["tool"]})
            row.update({"Size":result["artifactSize"]})
            row.update({"RunIndex":result["runIndex"]})
            row.update({"CaseName":result["query"]})
            row.update({"Scenario":result["scenario"]})
            row.update({"PhaseName":"Read"})
            row.update({"MetricName":"Time"})
            row.update({"MetricValue":result["readTime"]})
            row.update({"Sequence":sequence})
            writer.writerow(row)
            
            checks = result["checkTimes"]
            edits = result["editTimes"]
            row.update({"PhaseName": "Check"})
            sequence += 1
            row.update({"Sequence":sequence})
            row.update({"MetricName":"Time"})
            row.update({"MetricValue":checks[0]})
            writer.writerow(row)
            
            checks.pop(0)
            for i in range(0,len(checks)):
                row.update({"PhaseName":"Edit"})
                sequence += 1
                row.update({"Sequence":sequence})
                row.update({"MetricName": "Time"})
                row.update({"MetricValue": edits[i]})
                
                writer.writerow(row)
                
                row.update({"PhaseName":"ReCheck"})
                sequence += 1
                row.update({"Sequence":sequence})
                row.update({"MetricName": "Time"})
                row.update({"MetricValue": checks[i]})
                
                writer.writerow(row)


if (__name__ == "__main__"):
    parser = argparse.ArgumentParser( \
                      formatter_class=argparse.ArgumentDefaultsHelpFormatter \
                      )
    parser.add_argument("-c","--csvfile",
                        default="../../../results/csv/results.csv",
                        help="Path of the CSV file where the "+\
                             "results will be merged"
                        )
    parser.add_argument("-s","--source",
                        default="../../../results/",
                        help="Path of the results json files location."
                        )
    args = parser.parse_args()
    
    handler.set_working_directory()
    json_objects = load_results(args.source)
    write_to_csv(json_objects, args.csvfile)
    print("The results has been written successfully.")
