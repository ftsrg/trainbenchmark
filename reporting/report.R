source("util.R")

####################################################################################################
# Individual
####################################################################################################

levels.individual = c("PosLength", "SwitchSensor", "RouteSensor", "SwitchSet", "ConnectedSegments", "SemaphoreNeighbor")
results.individual = load("../results/results-individual.csv", levels.individual)
times.individual = process.times(results.individual, F)

# Time

phase = "Read"
benchmark.plot(
  df = times.individual[times.individual$Phase == phase, ], 
  scenario = "Batch",
  artifacts = modelsizes,
  metric = "Time",
  title = "Time",
  facet = "Case",
  scale = "free_y"
)

# heatmaps

heatmap(df = times.individual, 
        attribute = "Tool",
        map.from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                     "Neo4j (Core API)", "Neo4j (Cypher)",
                     "Blazegraph (No Inferencing)", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                     "MySQL", "SQLite"), 
        map.to = c("EMF", "EMF", "EMF", "EMF", "EMF", "EMF", 
                   "graph", "graph",
                   "RDF", "RDF", "RDF", "RDF", "RDF", 
                   "SQL", "SQL"),
        title = "Formats",
        height = 85,
        ncol = 4)

heatmap(df = times.individual, 
        attribute = "Tool",
        map.from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                     "Neo4j (Core API)", "Neo4j (Cypher)",
                     "Blazegraph (No Inferencing)", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                     "MySQL", "SQLite"),
        map.to = c("in-memory", "in-memory", "in-memory", "in-memory", "in-memory", "in-memory",
               "disk-resident", "disk-resident",
               "disk-resident", "in-memory", "in-memory", "in-memory", "in-memory", 
               "in-memory", "in-memory"),
        title = "Storage",
        height = 85,
        ncol = 2)

heatmap(df = times.individual, 
        attribute = "Case",
        title = "Case",
        height = 60,
        ncol = 6)

####################################################################################################
# Mix
####################################################################################################

levels.mix = c("RouteSensor-ConnectedSegments-PosLength-SemaphoreNeighbor-SwitchSensor-SwitchSet");
results.mix = load("../results/results-mix.csv", levels.mix)
memories.plot = process.memories(results.mix)
#times.mix = process.times(results.mix, T)

# Memory

benchmark.plot(
  df = memories.plot$memories, 
  scenario = "Batch", 
  artifacts = modelsizes, 
  metric = "Memory", 
  title = "Memories", 
  #facet = "Case", 
  scale = "fixed", 
  toolnames = memories.plot$toolnames,
  height = 150
)

# Time

scenario = "Inject"
benchmark.plot(
  df = times.mix, 
  scenario = scenario, 
  artifacts = modelsizes, 
  metric = "Time", 
  title = "Time", 
  facet = "Phase", 
  scale = "free_y"
)

scenario = "Repair"
benchmark.plot(
  df = times.mix, 
  scenario = scenario, 
  artifacts = modelsizes, 
  metric = "Time", 
  title = "Time", 
  facet = "Phase", 
  scale = "free_y"
)