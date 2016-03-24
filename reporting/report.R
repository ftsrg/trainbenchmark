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

df = times.individual
df$Artifact = discretize(df$Artifact, "fixed", categories = c(-Inf,16,256,Inf), labels = c("small", "medium", "large"))
df$Time = discretize(df$Time, "fixed", categories = c(-Inf,.1,1,10,Inf), labels = c("instantaneous", "fast", "acceptable", "slow"))

formats = df
formats$Tool = mapvalues(formats$Tool,
                    from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                             "Neo4j (Core API)", "Neo4j (Cypher)",
                             "Blazegraph", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                             "MySQL", "SQLite"),
                    to = c("EMF", "EMF", "EMF", "EMF", "EMF", "EMF", 
                           "graph", "graph",
                           "RDF", "RDF", "RDF", "RDF", "RDF", 
                           "SQL", "SQL"))
frequencies = as.data.frame(table(formats[, c("Artifact", "Time", "Tool")]))
p = ggplot(frequencies) +
  geom_tile(aes(x = Artifact, y = Time, fill = Freq)) +
  scale_fill_gradient(low = "white", high = "darkred") +
  facet_wrap(~ Tool, ncol = 2)
p

storage = df
storage$Tool = mapvalues(storage$Tool,
                    from = c("Drools5", "Drools6", "Eclipse OCL", "EMF API", "EMF-IncQuery (Incremental)", "EMF-IncQuery (Local Search)",
                             "Neo4j (Core API)", "Neo4j (Cypher)",
                             "Blazegraph", "Jena (Inferencing)", "Jena (No Inferencing)", "Sesame (Inferencing)", "Sesame (No Inferencing)",
                             "MySQL", "SQLite"),
                    to = c("in-memory", "in-memory", "in-memory", "in-memory", "in-memory", "in-memory",
                           "disk-resident", "disk-resident",
                           "disk-resident", "in-memory", "in-memory", "in-memory", "in-memory", 
                           "in-memory", "in-memory"))
frequencies = as.data.frame(table(storage[, c("Artifact", "Time", "Tool")]))
p = ggplot(frequencies) +
  geom_tile(aes(x = Artifact, y = Time, fill = Freq)) +
  scale_fill_gradient(low = "white", high = "darkred") +
  facet_wrap(~ Tool, ncol = 2)
p


####################################################################################################
# Mix
####################################################################################################

levels.mix = c("RouteSensor-ConnectedSegments-PosLength-SemaphoreNeighbor-SwitchSensor-SwitchSet");
results.mix = load("../results/results-mix.csv", levels.mix)
times.mix = process.times(results.mix, T)
memories.plot = process.memories(results.individual)

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

# Memory

benchmark.plot(
  df = memories.plot$memories, 
  scenario = "Batch", 
  artifacts = modelsizes, 
  metric = "Memory", 
  title = "Memories", 
  facet = "Case", 
  scale = "fixed", 
  toolnames = memories.plot$toolnames
)
