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

####################################################################################################
# Mix
####################################################################################################

levels.mix = c("RouteSensor-ConnectedSegments-PosLength-SemaphoreNeighbor-SwitchSensor-SwitchSet");
results.mix = load("../results/results-mix.csv", levels.mix)
times.mix = process.times(results.mix, T)
memories.plot = process.memories(results.individual)

# Time

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
