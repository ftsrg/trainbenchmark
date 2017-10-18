# Experimenting with the benchmark

To try out new queries, it is recommended to use an interactive UI, such as Neo4j's web browser.

## Experimenting using the Neo4j database

Download, unzip [Neo4j](http://neo4j.com/), start and visit <http://localhost:7474/>.

To load models conveniently, use the APOC library. Get the latest JAR from the [APOC releases](https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases) and copy it to the `neo4j/plugins` directory.

Edit the `neo4j/conf/neo4j.conf` file and add the following lines:

```
apoc.import.file.enabled=true
apoc.export.file.enabled=true

dbms.security.procedures.white_list=apoc.coll.*,apoc.load.*
```

Use the [`apoc.import.graphml`](https://neo4j-contrib.github.io/neo4j-apoc-procedures/#_import_graphml) procedure to load it. For example, to load a small well-formed model (`railway-batch-1.graphml`), use this command:

```
CALL apoc.import.graphml('/absolute/path/for/file/railway-batch-1.graphml', {batchSize: 1000, storeNodeIds:true, readLabels:true, useTypes:true})
YIELD nodes, relationships
RETURN *
```

To get a sample of the model, simply issue:

```
MATCH (n)
RETURN n
LIMIT 25
```

It's possible to provide parameters on the Neo4j browser's web UI. To see how, type `:help param`.
