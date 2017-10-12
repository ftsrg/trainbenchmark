# Developer Guide

## Projects

* The **generator projects** are responsible for generating instance models. For each format, there is a separate generator project, named `trainbenchmark-generator-*`.
* The **benchmark projects** are responsible for implementing the benchmarks to a specific tool (a database or a query engine). For each tool, there is a separate benchmark project, named `trainbenchmark-tool-*``

Currently, the following formats and tools are supported.

* **EMF:** EMF API, VIATRA
* **Property graph (Neo4j):** Neo4j, ([Graphflow](http://graphflow.io/) and [ingraph](http://docs.inf.mit.bme.hu/ingraph/) are work-in-progress)
* **Property graph (Tinkerpop):** Tinkergraph, JanusGraph, OrientDB
* **RDF:** Jena, Sesame 2.x
* **SQL:** MySQL, SQLite

## Naming conventions

**Note.** :notebook_with_decorative_cover: To avoid confusion between the different implementations, we decided to naming similar to the [Smurf Naming convention](http://blog.codinghorror.com/new-programming-jargon/) (see #21). For example, the classes in the EMF API implementation are named `EmfApiQueryPosLength`, `EmfApiQueryRouteSensor`, etc., while the classes in the VIATRA implementation are named `ViatraQueryPosLength`, `ViatraQueryRouteSensor`, etc. We found that relying on the package names to differentiate class names is error-prone and should be avoided.
