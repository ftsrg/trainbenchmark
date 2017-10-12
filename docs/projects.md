## Projects

* The **generator projects** are responsible for generating instance models. For each format, there is a separate generator project, named `trainbenchmark-generator-*`.
* The **benchmark projects** are responsible for implementing the benchmarks to a specific tool (a database or a query engine). For each tool, there is a separate benchmark project, named `trainbenchmark-tool-*``

Currently, the following formats and tools are supported.

* **EMF:** EMF API, VIATRA
* **Property graph (Neo4j):** Neo4j, ([Graphflow](http://graphflow.io/) and [ingraph](http://docs.inf.mit.bme.hu/ingraph/) are work-in-progress)
* **Property graph (Tinkerpop):** Tinkergraph, JanusGraph, OrientDB
* **RDF:** Jena, Sesame 2.x
* **SQL:** MySQL, SQLite
