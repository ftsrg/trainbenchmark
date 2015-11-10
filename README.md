# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

**Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. This repository contains the original Train Benchmark which also supports RDF, SQL and property graph databases.

**Warning.** The Train Benchmark is designed to run in a server environment. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences.

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

### Setup

#### Automatic

Provided that you start with a fresh Ubuntu server installation, you may use the provided install scripts:

```bash
scripts/init-jdk.sh && \
scripts/init-maven.sh && \
scripts/init-python.sh && \
scripts/dep-mysql.sh && \
scripts/dep-neo4j.sh && \
scripts/dep-virtuoso.sh && \
scripts/dep-mondo-sam.sh
```

#### Manual

Alternatively, install the following software:

* [Oracle JDK 8](https://github.com/FTSRG/cheat-sheets/wiki/Linux#oracle-jdk)
* [Maven 3](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux#maven-3)
* Python 3
* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql)
* Neo4j
* [Virtuoso](hu.bme.mit.trainbenchmark.benchmark.virtuoso)
* [MONDO-SAM 0.1](https://github.com/FTSRG/mondo-sam/tree/v0.1.0)

### Usage

Initialize the configuration file by running:

```
scripts/init-config.sh
```

This creates `config/config.yml` which defines the configuration for the benchmark. The documentation is provided as comments in the file.

The `scripts` directory contains the `run.py` script which is used for the following purposes:
* `scripts/run.py -b` -- build the projects
* `scripts/run.py -b -s` -- build the projects without testing
* `scripts/run.py -g` -- generates the instance models
* `scripts/run.py -m` -- runs the benchmark
* `scripts/run.py -h` -- displays the help

### Importing to Eclipse

The projects are developed and tested with **Eclipse Mars**.

To import and develop the Train Benchmark, you need the m2e Eclipse plugin, included in Eclipse for Java developers. If you use another distribution (e.g. Eclipse Modeling), you can install it from the Mars update site or the m2e update site (<http://download.eclipse.org/technology/m2e/releases>).

### Naming conventions

To avoid confusion between the different implementations, we decided to use the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the Neo4j implementation are named `Neo4jBenchmarkCase`, `Neo4jPosLengthChecker`, `JavaPosLengthMatch`, `JavaPosLengthTransformation`, while the classes in the EMF-IncQuery implementation are named `EMFIncQueryBenchmarkCase`, `EMFIncQueryPosLengthChecker`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.

## Reporting tools

### Install R packages

Follow the instructions [here](https://github.com/FTSRG/mondo-sam/blob/master/README.md#reporting-in-r).

### Convert the results

It is possible to convert the measurement results from JSON to CSV with the following script:

```bash
scripts/convert-results.sh
```

### Interactive reporting

In order to use the interactive interface in MONDO-SAM, install additional R packages as described [here](https://github.com/FTSRG/mondo-sam/tree/v0.1.0#interactive-reporting), then run the following:

```bash
scripts/interactive.py
```

### Generating diagrams

Adjust the `config/reporting.json` file and run the `scripts/report.sh` script. The possible configuration values are listed in the [MONDO-SAM wiki](https://github.com/FTSRG/mondo-sam/wiki/Reporting).

## Instance models

The Train Benchmark provides two sorts of instance models:

* **Minimal models**, used only for testing
* **Scalable models**, used both for testing and benchmarking

The **minimal models** contain only a few (8-10) model elements to provide simple models for development and testing.

The **scalable models** are generated for each scenario in sizes denoted by the powers of two, e.g. `railway-repair-1`, `railway-repair-2`, `railway-repair-4`, etc.
