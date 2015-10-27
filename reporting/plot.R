library("reshape2")
library("ggplot2")
library("plyr")

results = read.csv("../results/results.csv")

# constants
modelsize.batch = data.frame(Scenario = "Batch", Size = 2^(0:12), Triples = c("4.7k", "7.9k", "21k", "41k", "89k", "192k", "374k", "717k", "1.5M", "2.8M", "5.7M", "11.5M", "23M"))
modelsize.inject = data.frame(Scenario = "Inject", Size = 4^(0:6), Triples = c("5k", "20k", "86k", "373k", "1.5M", "5.8M", "23.3M"))
modelsize.repair = data.frame(Scenario = "Repair", Size = 4^(0:6), Triples = c("4.9k", "20k", "85k", "372k", "1.5M", "5.8M", "23.2M"))
modelsizes = do.call(rbind, list(modelsize.batch, modelsize.inject, modelsize.repair))

halfpage.width = 210
halfpage.height = 150
halfpage.xbreaks = 2^(0:32)

# filtering for time values (not interested in the number of matches for visualization)
times = subset(results, MetricName == "Time")

# convert nanoseconds to seconds
times$MetricValue = times$MetricValue / 10^9

# replace hyphen with space in tool names
times$Tool = gsub('_', ' ', times$Tool)

# long table to wide table
times = dcast(times,
               Tool + Size + MetricName + Scenario + CaseName + Iteration + RunIndex ~ PhaseName,
               value.var = "MetricValue", fun.aggregate = sum)

# calculate aggregated values
derived.times = times
derived.times$read.and.check = derived.times$Read + derived.times$Check
derived.times$transformation.and.recheck = derived.times$Transformation + derived.times$Recheck

# summarize along the iterations
derived.times = ddply(
    .data = derived.times, 
    .variables = c("Tool", "Size", "MetricName", "Scenario", "CaseName", "RunIndex"), 
    summarize,
    read = sum(Read, na.rm = TRUE),
    check = sum(Check, na.rm = TRUE),
    read.and.check = sum(read.and.check, na.rm = TRUE), 
    transformation = sum(Transformation),
    recheck = sum(Recheck),
    transformation.and.recheck = sum(transformation.and.recheck)
)

# take the median values from each measurement
f = median
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

# melt data to 
plottimes = melt(
  data = derived.times,
  id.vars = c("Tool", "Size", "Scenario", "CaseName"), 
  measure.vars = c("read", "check", "read.and.check", "transformation.and.recheck",  "transformation", "recheck")
)


# plot

benchmark.plot = function(df, scenario, modelsizes, title, xbreaks, width = 210, height = 297) {
  # x axis labels
  modelsizes.scenario = modelsizes[modelsizes$Scenario == "Batch", "Triples"]
  xlabels = paste(xbreaks, "\n", modelsizes.scenario, sep="")
  print(xlabels)
  
  # y axis labels
  ys = -10:10
  ybreaks = 10^ys
  ylabels = parse(text=paste("10^", ys, sep=""))
  
  plot.title = gsub("\\.", " ", title)
  plot.filename = gsub("\\.", "-", title)
  
  base = ggplot(df) +
    labs(title = paste(scenario, " scenario, ", plot.title, sep=""), x = "model size\n#triples", y = "execution time [s]") +
    geom_point(aes(x = as.factor(Size), y = value, col = Tool, shape = Tool), size = 1.5) +
    geom_line(aes(x = as.factor(Size), y = value, col = Tool, group = Tool), size = 0.15) +
    scale_shape_manual(values = seq(0,24)) +
    #scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ variable, ncol = 2) +
    theme_bw() +
    theme(legend.key = element_blank(), legend.title = element_blank(), legend.position = "bottom") +
    guides(shape = guide_legend(ncol = 4))
  print(base)
  
  ggsave(file=paste("../diagrams/", scenario, "-", plot.filename, ".pdf", sep=""), width = width, height = height, units = "mm")
}

#levels.all.cases = c("ConnectedSegments-PosLength-RouteSensor-SemaphoreNeighbor-SwitchSensor-SwitchSet")
levels.cases = c("PosLength", "SwitchSensor", "RouteSensor", "SwitchSet", "ConnectedSegments", "SemaphoreNeighbor")
levels.phases = c("read",           "transformation",
                  "check",          "recheck",
                  "read.and.check", "transformation.and.recheck")

benchmark.plot.by.phase = function(df, scenario, modelsizes, levels, case, title, xbreaks = 2^(0:12)) {
  df = df[df$Scenario == scenario & df$CaseName == case, ]
  df$variable = factor(df$variable, levels = levels)
  benchmark.plot(df, scenario, modelsizes, title, xbreaks)
}
benchmark.plot.by.phase(plottimes, "Inject", modelsizes, levels.phases, "RouteSensor", "RouteSensor.query")


benchmark.plot.by.case = function(df, scenario, modelsizes, levels, phase, title, xbreaks = 2^(0:12)) {
  df = df[df$Scenario == scenario & df$variable == phase, ]
  df$CaseName = factor(df$CaseName, levels = levels)
  benchmark.plot(df, scenario, modelsizes, title, xbreaks)
}
benchmark.plot.by.case(plottimes, "Inject", modelsizes, levels.cases, "read", "read.phase")

