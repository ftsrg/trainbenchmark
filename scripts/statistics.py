#!/usr/bin/python3
from collections import Counter, defaultdict
import sys


def parseLine(line: str):
    parts = line.split(" ")
    # full line (":_1 a :Semaphore ;")
    if len(parts) == 4:
        (_, name, value, _) = parts
        god[name][value] += 1
    # line with inferred subject ("	:signal :SIGNAL_GO .")
    elif len(parts) == 3:
        (name, value, _) = parts
        #cut tab from beginning
        name = name[1:]
        god[name][value] += 1


def calculateStats(name, counter: Counter):
    uniqueCounts = Counter(counter.values())
    print("{}:".format(name))
    print("  {} unique values".format(len(counter.keys())))
    print("  {} unique value arities".format(len(uniqueCounts)))
    print("  " + str(uniqueCounts))


def parseFile(inputPath):
    with open(inputPath) as f:
        for line in f:
            parseLine(line)
        for key in god:
            calculateStats(key, god[key])


if __name__ == "__main__":
    god = defaultdict(Counter)
    inputFile = sys.argv[1]
    parseFile(inputFile)
