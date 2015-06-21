# Train Benchmark

* Jenkins: [![Build Status](https://build.inf.mit.bme.hu/jenkins/buildStatus/icon?job=Train-Benchmark)](https://build.inf.mit.bme.hu/jenkins/job/Train-Benchmark/)
* Travis: [![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)
* Waffle: [![Stories in Ready](https://badge.waffle.io/FTSRG/trainbenchmark.png?label=ready&title=Ready)](https://waffle.io/FTSRG/trainbenchmark)
* [Sonar](http://build.inf.mit.bme.hu/sonar/dashboard/index/14429)

**Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), targeting EMF tools. This repository contains the original Train Benchmark which also supports RDF, SQL and property graph databases.

For theoretical and implementation details, check out the following documents:
* [Train Benchmark technical report](https://www.sharelatex.com/github/repos/FTSRG/trainbenchmark-docs/builds/latest/output.pdf) ([GitHub repository](https://github.com/FTSRG/trainbenchmark-docs))
* [Related publications](http://incquery.net/publications/trainbenchmark)

## Prerequisites

* 64-bit operating system (we recommend Ubuntu-based Linux systems)
* [Oracle JDK 7](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux#oracle-jdk-7) (JDK 8 is not supported yet)
* [Maven 3](https://github.com/FTSRG/technology-cheat-sheets/wiki/Linux#maven-3)

## Projects

### Generator projects

The generator projects are responsible for generating instance models. Currently, the following formats are supported:

* EMF
* Property graph
* RDF
* SQL

### Benchmark projects

The benchmark projects are responsible for running the benchmarks.

* Drools
* EMF-IncQuery
* Java
* Jena
* MySQL
* Neo4j
* Sesame

## Getting started

To get familiar with the Train Benchmark, we recommend to try an existing implementation, e.g. the Sesame implementation.

## Platform dependencies

#### Linux

Make sure you have Maven 3 on the path.

```bash
$ mvn --version
```

#### Windows

Please note that the Windows version of the benchmark is not complete (e.g. 4store is not available on Windows) and is not maintained actively.

* [Cygwin](http://www.cygwin.com/) is required to run the shell scripts.
* Make sure the `JAVA_HOME` environment variable is set to the folder of the JDK.
* Due to legacy [limitations](http://msdn.microsoft.com/en-us/library/aa365247), Eclipse applications do no support paths longer than 260 characters. This causes the following error: `The Eclipse executable launcher was unable to locate its companion shared library`.
  To work around this, on Windows the build script moves the generated Eclpise application products higher in the directory structure and the benchmark script calls the applications accordingly.

### Importing to Eclipse

The projects are developed and tested with **Eclipse Kepler**. In Eclipse Luna you may experience problems if you wish to edit the EMF metamodel.

To import and develop the Train Benchmark, you need the m2e Eclipse plugin, included in Eclipse for Java developers. If you use another distribution (e.g. Eclipse Modeling), you can install it from the Kepler update site or the m2e update site (<http://download.eclipse.org/technology/m2e/releases>).

### Naming conventions

To avoid confusion between the different implementations, we decided to use the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the Java implementation are named `JavaBenchmarkCase`, `JavaPosLength`, `JavaPosLengthMatch`, `JavaPosLengthTransformation`, while the classes in the EMF-IncQuery implementation are named `EMFIncQueryBenchmarkCase`, `EMFIncQueryPosLength`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.
