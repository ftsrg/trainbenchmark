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

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark. The configuration is stored in the `config.json` file. To alter the default configuration, just edit this file. Find more information [here](https://github.com/FTSRG/trainbenchmark/wiki/Configuration).

### Prerequisites

* 64-bit operating system (we recommend Ubuntu-based Linux systems)
* [Oracle JDK 7+](https://github.com/FTSRG/cheat-sheets/wiki/Linux#oracle-jdk)
* [Maven 3](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux#maven-3)
* Python 3

Certain tools need to be installed manually:

* [Allegro](hu.bme.mit.trainbenchmark.benchmark.allegro)
* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql)
* [Virtuoso](hu.bme.mit.trainbenchmark.benchmark.virtuoso)

### Installation guide

Install [MONDO-SAM](https://github.com/FTSRG/mondo-sam):

```bash
scripts/dep-mondo-sam.sh`
```

Initialize the Python environment:

```bash
scripts/init-python.sh
```

Initialize the configuration file:

```bash
scripts/init-config.sh
```

### Usage

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
