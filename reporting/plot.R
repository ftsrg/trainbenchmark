library("reshape2")
library("ggplot2")
library("plyr")

results = read.csv("../results/results.csv")

# filtering for time values (not interested in the number of matches for visualization)
times = subset(results, MetricName == "Time")

# convert nanoseconds to seconds
times$MetricValue = times$MetricValue / 10^9

# replace hyphen with space in tool names
times$Tool = gsub('_', ' ', times$Tool)

# long table to wide table
times = dcast(times,
               Tool + Size + MetricName + Scenario + CaseName + Iteration + RunIndex ~ PhaseName,
               value.var = "MetricValue")

derived.times = times
derived.times$read.and.check = derived.times$Read + derived.times$Check
derived.times$transformation.and.recheck = derived.times$Transformation + derived.times$Recheck

derived.times = ddply(
    .data = derived.times, 
    .variables = c("Tool", "Size", "MetricName", "Scenario", "CaseName", "RunIndex"), 
    summarize,
    read.and.check = sum(read.and.check, na.rm = TRUE), 
    transformation.and.recheck = sum(transformation.and.recheck),
    read = sum(Read, na.rm = TRUE),
    check = sum(Check, na.rm = TRUE),
    transformation = sum(Transformation),
    recheck = sum(Recheck)
)

# adjust the function
f = median
#f = min

derived.times = ddply(
    .data = derived.times, 
    .variables = c("Tool", "Size", "MetricName", "Scenario", "CaseName"), 
    summarize, 
    read.and.check = f(read.and.check),
    transformation.and.recheck = f(transformation.and.recheck),
    read = f(read),
    check = f(check),
    transformation = f(transformation),
    recheck = f(recheck)
)

plottimes = melt(data = derived.times, id.vars = c("Tool", "Size", "Scenario", "CaseName"), measure.vars = c("read.and.check", "transformation.and.recheck", "read", "check", "transformation", "recheck"))

# plot

trainBenchmarkPlot = function(df, scenario, modelsizes, levels, variable) {
  df = df[df$Scenario == scenario & df$variable == variable, ]
  #print(head(df))
  df = melt(data = df, id.vars = c("Tool", "Size", "Scenario", "CaseName"), measure.vars = c("value"))
  
  # x axis labels
  modelsizes.scenario = as.vector(modelsizes[[scenario]])
  xbreaks = 4^(0:16)
  xlabels = paste(xbreaks, "\n", modelsizes.scenario, sep="")
  
  # y axis labels
  ys = -10:10
  ybreaks = 10^ys
  ylabels = parse(text=paste("10^", ys, sep=""))

  variable.title = gsub("\\.", " ", variable)
  variable.filename = gsub("\\.", "-", variable)
  
  df$CaseName <- factor(df$CaseName, levels = levels)
  
  base = ggplot(df) +
    labs(title = paste(scenario, " scenario, ", variable.title, sep=""), x = "Model size", y = "Execution time [s]") +
    geom_point(aes(x = as.factor(Size), y = value, col = Tool, shape = Tool), size = 1.5) +
    geom_line(aes(x = as.factor(Size), y = value, col = Tool, group = Tool), size = 0.15) +
    scale_shape_manual(values = seq(0,24)) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ CaseName, ncol = 2) +
    theme_bw() +
    theme(legend.key = element_blank(), legend.title = element_blank(), legend.position = "bottom") +
    guides(shape = guide_legend(ncol = 4))
  print(base)
  
  ggsave(file=paste("../diagrams/", scenario, "-", variable.filename, ".pdf", sep=""), width = 210, height = 297, units = "mm")
}

modelsize.batch = c("4.7k", "21k", "89k", "374k", "1.5M", "5.7M", "23M")
modelsize.inject = c("5k", "20k", "86k", "373k", "1.5M", "5.8M", "23.3M")
modelsize.repair = c("4.9k", "20k", "85k", "372k", "1.5M", "5.8M", "23.2M")

modelsizes = data.frame("Batch" = modelsize.batch, "Inject" = modelsize.inject, "Repair" = modelsize.repair);
levels = c("PosLength", "SwitchSensor", "RouteSensor", "SwitchSet", "ConnectedSegments", "SemaphoreNeighbor")

batch.scenarios = c("Batch")
transformation.scenarios = c("Inject", "Repair")

trainBenchmarkPlot(plottimes, "All", modelsizes, c("All"), "read")
trainBenchmarkPlot(plottimes, "All", modelsizes, c("All"), "check")
trainBenchmarkPlot(plottimes, "All", modelsizes, c("All"), "read.and.check")

for (scenario in c(batch.scenarios, transformation.scenarios)) {
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "read")
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "check")
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "read.and.check")
}

for (scenario in transformation.scenarios) {
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "transformation")
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "recheck")
  trainBenchmarkPlot(plottimes, scenario, modelsizes, levels, "transformation.and.recheck")
}
