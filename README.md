# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

:steam_locomotive: **Summary.** The Train Benchmark is a framework for measuring the performance of continuous model transformations, with a particular emphasis on the performance of incremental query reevaluation. The benchmark is actively developed since 2011.

:computer: **Technologies.** The framework is implemented in Java 8 (for the main components) and [Groovy](http://www.groovy-lang.org/) (for the scripts). The visualization is handled by [R scripts](https://www.r-project.org/). Both the build and the benchmark process in governed by [Gradle](https://gradle.org/).

:wave: **Contributions welcome.** If you would like to implement the benchmark on your tool, we recommend to read the [documentation](docs/) and also please do not hesitate to [get in contact](https://github.com/szarnyasg)!

:warning: **Warning.** The Train Benchmark is designed to run in an isolated server environment, e.g. virtual machines in the cloud. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences. See also issue [#75](https://github.com/FTSRG/trainbenchmark/issues/75).

:notebook_with_decorative_cover: **Note.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. _That fork is no longer maintained._ You should use this repository, containing the full, cross-technology Train Benchmark (also supporting RDF, SQL and property graph databases).

:book: **Details.** If you are interested in getting the benchmark working or contributing, visit the [documentation](docs/).

:books: **Publications.** For theoretical foundations, check out our [related publications](http://incquery.net/publications/trainbenchmark)

## License

The project uses the Apache 2.0 license and was supported by the MONDO EU FP-7 project.
It is currently developed by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
