source("util.R")

####################################################################################################
# Individual / Runtime
####################################################################################################

levels.individual = c("PosLength", "SwitchSensor", "RouteSensor", "SwitchSet", "ConnectedSegments", "SemaphoreNeighbor")
results.individual = load("../results/results-individual-runtime.csv", levels.individual)
times.individual = process.times(results.individual, F)

# Time

phase = "Read"
benchmark.plot(
  df = times.individual[times.individual$Phase == phase, ], 
  scenario = "Batch",
  filename = ,
  artifacts = modelsizes,
  metric = "Time",
  title = "Time",
  facet = "Case",
  scale = "free_y"
)

# heatmaps

heatmap(df = times.individual, 
        attributes = c("Tool"),
        map.from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                     "Neo4j (Core API)", "Neo4j (Cypher)",
                     "Blazegraph (No Inferencing)", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                     "MySQL", "SQLite"), 
        map.to = c("EMF", "EMF", "EMF", "EMF", "EMF", "EMF", 
                   "graph", "graph",
                   "RDF", "RDF", "RDF", "RDF", "RDF", 
                   "SQL", "SQL"),
        title = "Comparison of performance by formats",
        filename = "formats",
        height = 65,
        width = 160,
        ncol = 4,
        legend.position = "right")



heatmap(df = times.individual, 
        attributes = c("Tool"),
        map.from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                     "Neo4j (Core API)", "Neo4j (Cypher)",
                     "Blazegraph (No Inferencing)", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                     "MySQL", "SQLite"),
        map.to = c("in-memory", "in-memory", "in-memory", "in-memory", "in-memory", "in-memory",
               "disk-resident", "disk-resident",
               "disk-resident", "in-memory", "in-memory", "in-memory", "in-memory", 
               "in-memory", "in-memory"),
        title = "Comparison of performance by storage",
        filename = "storage",
        height = 85,
        width = 90,
        ncol = 2,
        legend.position = "bottom")



times.individual.read.and.check.only = subset(
  times.individual, 
  Phase == "Read and Check")
heatmap(df = times.individual.read.and.check.only, 
        attributes = c("Case"),
        title = "Complexity of queries (read and check phase)",
        filename = "queries",
        height = 90,
        ncol = 6,
        legend.position = "bottom")



times.individual.aggregated.only = subset(
  times.individual, 
  Phase == "Read and Check" | Phase == "Transformation and Recheck")
heatmap(df = times.individual.aggregated.only,
        attributes = c("Phase", "Scenario"),
        title = "Complexity of scenarios",
        filename = "scenarios",
        width = 100,
        height = 100,
        legend.position = "bottom")


####################################################################################################
# Mix
####################################################################################################

levels.mix = c("RouteSensor-ConnectedSegments-PosLength-SemaphoreNeighbor-SwitchSensor-SwitchSet");

####################################################################################################
# Mix / Memory
####################################################################################################

results.mix.memory = load("../results/results-mix-memory.csv", levels.mix)
memories.plot = process.memories(results.mix.memory)

benchmark.plot(
  df = memories.plot$memories,
  scenario = "Batch",
  artifacts = modelsizes,
  metric = "Memory",
  title = "Batch scenario, query mix, memory consumption",
  filename = "batch-mix-memory",
  scale = "fixed", 
  toolnames = memories.plot$toolnames,
  height = 150
)

####################################################################################################
# Mix / Runtime
####################################################################################################

results.mix.runtime = load("../results/results-mix-runtime.csv", levels.mix)
times.mix = process.times(results.mix.runtime, T)

metric = "Time"
facet = "Phase"
scale = "free_y"

benchmark.plot(
  df = times.mix, scenario = "Batch", artifacts = modelsizes, metric = metric, 
  title = "Batch scenario, query mix, execution time", filename = "batch-mix-runtime", facet = facet, scale = scale
)

benchmark.plot(
  df = times.mix, scenario = "Inject", artifacts = modelsizes, metric = metric, 
  title = "Inject scenario, query mix, execution time", filename = "inject-mix-runtime", facet = facet, scale = scale
)

benchmark.plot(
  df = times.mix, scenario = "Repair", artifacts = modelsizes, metric = "Time", 
  title = "Repair scenario, query mix, execution time", filename = "repair-mix-runtime", facet = facet, scale = scale
)

