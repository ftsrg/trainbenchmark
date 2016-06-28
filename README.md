# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

**Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. _That fork is no longer maintained._ You should use this repository, containing the full, cross-technology Train Benchmark (also supporting RDF, SQL and property graph databases).

**Warning.** The Train Benchmark is designed to run in an isolated server environment, e.g. virtual machines in the cloud. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences.

For theoretical and implementation details, check out the following documents:
* [Train Benchmark technical report](https://www.sharelatex.com/github/repos/FTSRG/trainbenchmark-docs/builds/latest/output.pdf) ([GitHub repository](https://github.com/FTSRG/trainbenchmark-docs))
* [Related publications](http://incquery.net/publications/trainbenchmark)

## Projects

* The **generator projects** are responsible for generating instance models. For **each format**, there is a separate generator project.
* The **benchmark projects** are responsible for running the benchmarks. For each tool, there is a separate benchmark project.

Currently, the following formats and tools are supported.

* **EMF:** Drools 5 & 6, Eclipse OCL, EMF API, VIATRA
* **Property graph:** Neo4j, Tinkergraph
* **RDF:** Blazegraph, Jena, Sesame
* **SQL:** MySQL, SQLite

## Getting started

The framework provides a set of scripts for building the projects, generating the instance models and running the benchmark.

### Installation guide

The benchmark requires a 64-bit operating system. We recommend Ubuntu-based Linux systems.

### Setup

* Initialization
    * [`install-jdk.sh`](scripts/install-jdk.sh): installs [Oracle JDK 8](https://github.com/FTSRG/cheat-sheets/wiki/Linux-packages#oracle-jdk)
    * [`install-gradle.sh`](scripts/install-maven.sh): installs Gradle.

Provided that you start with a fresh Ubuntu server installation, you can run the provided install scripts like this:

```bash
scripts/install-jdk.sh && \
scripts/install-gradle.sh
```

#### Optional dependencies

Some tools require dependencies, e.g. installing a database manager or adding artifacts to your local Maven repository

* [MySQL](hu.bme.mit.trainbenchmark.benchmark.mysql): install with `sudo apt-get install -y mysql-server` and set the root password to empty.
* [SQLite](hu.bme.mit.trainbenchmark.benchmark.sqlite): install with `sudo apt-get install -y sqlite3`.
* (Deprecated.) [AllegroGraph](hu.bme.mit.trainbenchmark.benchmark.allegro): download the [Allegro server](http://franz.com/agraph/downloads/server). Unzip the file and run `./configure-agraph`. Adjust the port to `10035` (default value), set the username and password to `super`. Start the server with `./agraph`.
* (Deprecated.) [Virtuoso](hu.bme.mit.trainbenchmark.benchmark.virtuoso): run the `scripts/dep-virtuoso.sh` script to resolve the dependencies. Issue the `sudo apt-get install virtuoso-opensource` command to install Virtuoso and set the password to `dba`.

### Usage

The benchmark configuration is defined in the `config/config.yml` file (this is created by the [`scripts/init-config.sh`](scripts/init-config.sh) script). The script is based on the default configuration stored in [`config/default-config.yml`](config/default-config.yml) which also provided the documentation as comments in the file.

The `scripts` directory contains the [`run.py`](scripts/run.py) script which is used for the following purposes:
* `scripts/run.py -b` -- build the projects
* `scripts/run.py -b -s` -- build the projects without testing
* `scripts/run.py -g` -- generates the instance models for the defined tools
* `scripts/run.py -f` -- only generates the instance models for the specified formats (useful if you only need the models)
* `scripts/run.py -m` -- runs the benchmark measurements
* `scripts/run.py -h` -- displays the help

### Importing to Eclipse

The projects are developed and tested with **Eclipse Mars**.

To develop the Train Benchmark, you a Gradle Eclipse plugin from the **Eclipse Marketplace**, e.g. the **Buildship: Eclipse Plug-ins for Gradle**. Also install the Eclipse Groovy tooling from <https://github.com/groovy/groovy-eclipse/wiki>.

To import the projects, choose **Import...** | **Gradle Project**, specify the root directory as the repository directory and import them with the default **Gradle distribution** (**Gradle wrapper (recommended)**).

### Naming conventions

**Note.** To avoid confusion between the different implementations, we decided to naming similar to the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the EMF API implementation are named `EmfApiQueryPosLength`, `EmfApiQueryRouteSensor`, etc., while the classes in the VIATRA implementation are named `ViatraQueryPosLength`, `ViatraQueryRouteSensor`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.

## Reporting tools



## Instance models

The Train Benchmark provides two sorts of instance models:

* **Minimal models**, used only for testing
* **Scalable models**, used both for testing and benchmarking

The **minimal models** contain only a few (8-10) model elements to provide simple models for development and testing.

The **scalable models** are generated for each scenario in sizes denoted by the powers of two, e.g. `railway-repair-1`, `railway-repair-2`, `railway-repair-4`, etc.
