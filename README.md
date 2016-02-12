# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

**Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. This repository contains the original Train Benchmark which also supports RDF, SQL and property graph databases.

**Warning.** The Train Benchmark is designed to run in an isolated server environment, e.g. virtual machines in the cloud. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences.

For theoretical and implementation details, check out the following documents:
* [Train Benchmark technical report](https://www.sharelatex.com/github/repos/FTSRG/trainbenchmark-docs/builds/latest/output.pdf) ([GitHub repository](https://github.com/FTSRG/trainbenchmark-docs))
* [Related publications](http://incquery.net/publications/trainbenchmark)

## Projects

* The **generator projects** are responsible for generating instance models. For **each format**, there is a separate generator project.
* The **benchmark projects** are responsible for running the benchmarks. For each tool, there is a separate benchmark project.

Currently, the following formats and tools are supported.

* **EMF:** Drools 5 & 6, Eclipse OCL, EMF API, EMF-IncQuery
* **Property graph:** Neo4j
* **RDF:** Blazegraph, Jena, Sesame
* **SQL:** MySQL

## Getting started

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark.

### Installation guide

The benchmark requires a 64-bit operating system. We recommend Ubuntu-based Linux systems.

### Setup

* Initialization
    * [`init-config.sh`](scripts/init-config.sh): initializes the configuration file, `config/config.yml`
* Installation
    * [`install-python.sh`](scripts/install-python.sh): installs [Python 3](https://www.python.org/downloads/) and the required packages
    * [`install-jdk.sh`](scripts/install-jdk.sh): installs [Oracle JDK 8](https://github.com/FTSRG/cheat-sheets/wiki/Linux-packages#oracle-jdk)
    * [`install-maven.sh`](scripts/install-maven.sh): installs [Maven 3](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux-packages#maven-3)
* Dependencies
    * [`dep-mondo-sam.sh`](scripts/dep-mondo-sam.sh): resolves the [MONDO-SAM 0.1](https://github.com/FTSRG/mondo-sam/tree/v0.1.0) dependency used by the benchmark framework
    * [`dep-graph.sh`](scripts/dep-graph.sh): resolves the [Neo4j Shell Tools](https://github.com/jexp/neo4j-shell-tools) dependency required by the `graph` components

Provided that you start with a fresh Ubuntu server installation, you can run the provided install scripts like this:

```bash
scripts/init-config.sh && \
scripts/install-python.sh && \
scripts/install-jdk.sh && \
scripts/install-maven.sh && \
scripts/dep-mondo-sam.sh && \
scripts/dep-graph.sh
```

#### Optional dependencies

Some tools require dependencies, e.g. installing a database manager or adding artifacts to your local Maven repository

* [AllegroGraph](hu.bme.mit.trainbenchmark.benchmark.allegro): download the [Allegro server](http://franz.com/agraph/downloads/server). Unzip the file and run `./configure-agraph`. Adjust the port to `10035` (default value), set the username and password to `super`. Start the server with `./agraph`.
* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql): install with `sudo apt-get install -y mysql-server` and set the root password to empty.
* [Virtuoso](hu.bme.mit.trainbenchmark.benchmark.virtuoso): run the `scripts/dep-virtuoso.sh` script to resolve the dependencies. Issue the `sudo apt-get install virtuoso-opensource` command to install Virtuoso and set the password to `dba`.

### Usage

The benchmark configuration is defined in the `config/config.yml` file (this is be created by the [`scripts/init-config.sh`](scripts/init-config.sh) script). The script is based on the default configuration stored in [`config/default-config.yml`](config/default-config.yml) which also provided the documentation as comments in the file.

The `scripts` directory contains the [`run.py`](scripts/run.py) script which is used for the following purposes:
* `scripts/run.py -b` -- build the projects
* `scripts/run.py -b -s` -- build the projects without testing
* `scripts/run.py -g` -- generates the instance models for the defined tools
* `scripts/run.py -f` -- only generates the instance models for the specified formats (useful if you only need the models)
* `scripts/run.py -m` -- runs the benchmark measurements
* `scripts/run.py -h` -- displays the help

### Importing to Eclipse

The projects are developed and tested with **Eclipse Mars**.

To import and develop the Train Benchmark, you need the m2e Eclipse plugin, included in Eclipse for Java developers. If you use another distribution (e.g. Eclipse Modeling), you can install it from the Mars update site or the m2e update site (<http://download.eclipse.org/technology/m2e/releases>).

### Naming conventions

**Note.** To avoid confusion between the different implementations, we decided to use the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the Neo4j implementation are named `Neo4jBenchmarkCase`, `Neo4jPosLengthChecker`, `JavaPosLengthMatch`, `JavaPosLengthTransformation`, while the classes in the EMF-IncQuery implementation are named `EMFIncQueryBenchmarkCase`, `EMFIncQueryPosLengthChecker`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.

## Reporting tools

Convert the measurement results from JSON to CSV with the following script:

```bash
scripts/convert-results.sh
```

Use the reporting tools provided in the [mondo-sam-reporting](https://github.com/FTSRG/mondo-sam-reporting) repository.

## Instance models

The Train Benchmark provides two sorts of instance models:

* **Minimal models**, used only for testing
* **Scalable models**, used both for testing and benchmarking

The **minimal models** contain only a few (8-10) model elements to provide simple models for development and testing.

The **scalable models** are generated for each scenario in sizes denoted by the powers of two, e.g. `railway-repair-1`, `railway-repair-2`, `railway-repair-4`, etc.
