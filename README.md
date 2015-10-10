# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

**Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. This repository contains the original Train Benchmark which also supports RDF, SQL and property graph databases.

For theoretical and implementation details, check out the following documents:
* [Train Benchmark technical report](https://www.sharelatex.com/github/repos/FTSRG/trainbenchmark-docs/builds/latest/output.pdf) ([GitHub repository](https://github.com/FTSRG/trainbenchmark-docs))
* [Related publications](http://incquery.net/publications/trainbenchmark)

## Projects

### Generator projects

The generator projects are responsible for generating instance models. Currently, the following formats are supported:

* EMF
* Property graph
* RDF
* SQL

### Benchmark projects

The benchmark projects are responsible for running the benchmarks.

* EMF
  * Drools 5 & 6
  * EMF API
  * EMF-IncQuery
  * Eclipse OCL
* Property graph
  * Neo4j
  * OrientDB
* RDF
  * Allegro
  * Blazegraph
  * Jena
  * Sesame
  * Virtuoso
* SQL
  * MySQL

## Getting started

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark.

### Installation guide

The benchmark requires a 64-bit operating system. We recommend Ubuntu-based Linux systems.

### Environment

Make sure that you have the following environments.

* [Oracle JDK 7+](https://github.com/FTSRG/cheat-sheets/wiki/Linux#oracle-jdk)
* [Maven 3](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux#maven-3)
* Python 3

Install [MONDO-SAM](https://github.com/FTSRG/mondo-sam) and initialize the configuration file.

Provided that you start with a fresh Ubuntu server installation, you may use the provided install scripts:

```bash
scripts/init-jdk.sh && \
scripts/init-maven.sh && \
scripts/init-python.sh && \
scripts/init-mondo-sam.sh && \
scripts/init-config.sh
```

Certain tools need to be installed manually. For details, please follow their README file:

* [Allegro](hu.bme.mit.trainbenchmark.benchmark.allegro)
* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql)
* [Virtuoso](hu.bme.mit.trainbenchmark.benchmark.virtuoso)

Certain tools have dependencies that should be installed, either by buildin from source or by deploying them to your local Maven repository.

```bash
scripts/dep-allegro.sh && \
scripts/dep-neo4j.sh && \
scripts/dep-virtuoso.sh
```

### Usage

The `scripts/init-config.sh` script initializes the `config/config.yml` file. This file defines the configuration for the benchmark. The documentation is provided as comments in the file.

The `scripts` directory contains the `run.py` script which is used for the following purposes:
* `run.py -b` -- build the projects
* `run.py -b -s` -- build the projects without testing
* `run.py -g` -- generates the instance models
* `run.py -m` -- runs the benchmark
* `run.py -h` -- displays the help

### Importing to Eclipse

The projects are developed and tested with **Eclipse Mars**.

To import and develop the Train Benchmark, you need the m2e Eclipse plugin, included in Eclipse for Java developers. If you use another distribution (e.g. Eclipse Modeling), you can install it from the Mars update site or the m2e update site (<http://download.eclipse.org/technology/m2e/releases>).

### Naming conventions

To avoid confusion between the different implementations, we decided to use the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the Java implementation are named `JavaBenchmarkCase`, `JavaPosLength`, `JavaPosLengthMatch`, `JavaPosLengthTransformation`, while the classes in the EMF-IncQuery implementation are named `EMFIncQueryBenchmarkCase`, `EMFIncQueryPosLength`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.

## Reporting tools

### Convert the results

It is possible to convert the measurement results from JSON to CSV with the following script:

```bash
scripts/convert-results.sh
scripts/report.sh
```

### Interactive reporting

In order to use the interactive interface in MONDO-SAM, run the following:

```bash
scripts/interactive.sh
```
 
For further information, read the [instructions](https://github.com/FTSRG/mondo-sam/blob/master/README.md).

### Generating diagrams

Adjust the `config/reporting.json` file and run the `scripts/report.sh` script. The possible configuration values are listed in the [MONDO-SAM wiki](https://github.com/FTSRG/mondo-sam/wiki/Reporting).

## Instance models

The Train Benchmark provides two sorts of instance models:

* **Minimal models**, used only for testing
* **Scalable models**, used both for testing and benchmarking

The **minimal models** contain only a few (8-10) model elements to provide simple models for development and testing.

The **scalable models** are generated for each scenario in sizes denoted by the powers of two, e.g. `railway-repair-1`, `railway-repair-2`, `railway-repair-4`, etc.
