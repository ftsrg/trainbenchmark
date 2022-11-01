# Train Benchmark

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark)

:steam_locomotive: **Summary.** The Train Benchmark is a framework for measuring the performance of continuous model transformations, with a particular emphasis on the performance of incremental query reevaluation. The benchmark is actively developed since 2011.

:book: **Documentation.** If you are interested in implementing the benchmark on your tool, please visit the [documentation](docs/).

:movie_camera: **Presentation.** For a short summary, check out [this presentation](https://www.slideshare.net/szarnyasg/the-train-benchmark-crosstechnology-performance-evaluation-of-continuous-model-queries/), delivered at the [Linked Data Benchmark Council's](http://ldbcouncil.org/) [9th Technical User Community meeting](http://wiki.ldbcouncil.org/pages/viewpage.action?pageId=59277315).

:books: **Publications.** The definitive publication on the benchmark is our journal paper
[The Train Benchmark: cross-technology performance evaluation of continuous model queries](http://link.springer.com/article/10.1007/s10270-016-0571-8). For use cases, also check out the [related publications](https://github.com/FTSRG/publication-pages/wiki/Benchmarking-query-technologies-in-model-driven-scenarios).

:computer: **Technologies.** The framework is implemented in Java 8 (for the main components) and [Groovy](http://www.groovy-lang.org/) (for scripts). The visualization is handled by [R scripts](https://www.r-project.org/). Both the build and the benchmark process in governed by [Gradle](https://gradle.org/).

:wave: **Contributions welcome.** If you would like to implement the benchmark on your tool, we recommend to read the [documentation](docs/) and also please do not hesitate to [get in touch](https://github.com/szarnyasg)!

:warning: **Warning.** The Train Benchmark is designed to run in an isolated server environment, e.g. virtual machines in the cloud. Some implementations may shut down or delete existing databases, so only run it on your developer workstation if you understand the consequences. See also issue [#75](https://github.com/FTSRG/trainbenchmark/issues/75).

:warning: **Warning.** The Train Benchmark has a fork for the [2015 Transformation Tool Contest](https://github.com/FTSRG/trainbenchmark-ttc), primarily targeting EMF tools. _That fork is no longer maintained._ You should use this repository, containing the full, cross-technology Train Benchmark (also supporting RDF, SQL and property graph databases).

:page_facing_up: **Citing the benchmark.** For referencing the benchmark, please cite the paper in [Software and Systems Modeling](http://link.springer.com/article/10.1007/s10270-016-0571-8) with the following BibTeX snippet.

:triangular_ruler: **Models.** Pre-generated models for scale factors 1 to 4096 are available as a [`tar.zst` package](https://surfdrive.surf.nl/files/index.php/s/QBXOSE38sbSw1yX).

```
@article{DBLP:journals/sosym/SzarnyasIRV18,
  author    = {G{\'{a}}bor Sz{\'{a}}rnyas and
               Benedek Izs{\'{o}} and
               Istv{\'{a}}n R{\'{a}}th and
               D{\'{a}}niel Varr{\'{o}}},
  title     = {The Train Benchmark: cross-technology performance evaluation of continuous
               model queries},
  journal   = {Software and System Modeling},
  volume    = {17},
  number    = {4},
  pages     = {1365--1393},
  year      = {2018},
  url       = {https://doi.org/10.1007/s10270-016-0571-8},
  doi       = {10.1007/s10270-016-0571-8},
  timestamp = {Fri, 07 Sep 2018 14:25:47 +0200},
  biburl    = {https://dblp.org/rec/bib/journals/sosym/SzarnyasIRV18},
  bibsource = {dblp computer science bibliography, https://dblp.org}
}
```

## License

The project uses the Eclipse Public License 1.0 and was supported by the MONDO EU FP7 (EU ICT-611125) project.
It is primarily maintained by the MTA-BME Lendület Research Group on Cyber-Physical Systems.
