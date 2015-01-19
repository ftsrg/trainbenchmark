# Train Benchmark graph format

[![Build Status](https://travis-ci.org/FTSRG/trainbenchmark-graph.svg?branch=master)](https://travis-ci.org/FTSRG/trainbenchmark-graph)

The Train Benchmark supports the property graph data model [1][2] and generates files in GraphML format [3].

- [1] http://arxiv.org/abs/1006.2361
- [2] https://github.com/tinkerpop/blueprints/wiki/Property-Graph-Model
- [3] http://graphml.graphdrawing.org/

This project requires the `neo4j-shell-tools` project.

1. <https://github.com/szarnyasg/neo4j-shell-tools>: clone and build.

2. <https://github.com/jexp/neo4j-shell-tools>: this also requires another project, `geoff`, which is built with Gradle. The `scripts/resolve-dependencies.sh` script installs Gradle (on Ubuntu-based systems) and resolves these dependencies.
