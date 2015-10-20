library("reshape2")
library("ggplot2")
library("plyr")

results <- read.csv("../results/results.csv")

# filtering for time values (not interested in the number of matches for visualization)
times <- subset(results, MetricName == "Time")

# convert nanoseconds to seconds
times$MetricValue <- times$MetricValue / 10^9

# replace hyphen with space in tool names
times$Tool <- gsub('_', ' ', times$Tool)

# long table to wide table
times <- dcast(times,
               Tool + Size + MetricName + Scenario + CaseName + Iteration + RunIndex ~ PhaseName,
               value.var = "MetricValue")

derived.times <- times
derived.times$read.and.check <- derived.times$Read + derived.times$Check
derived.times$transformation.and.recheck <- derived.times$Transformation + derived.times$Recheck

derived.times <- ddply(
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

derived.times <- ddply(
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

plottimes <- melt(data = derived.times, id.vars = c("Tool", "Size", "Scenario", "CaseName"), measure.vars = c("read.and.check", "transformation.and.recheck", "read", "check", "transformation", "recheck"))

# plot

trainBenchmarkPlot <- function(df, scenario, variable, xlabels) {
  df <- df[df$Scenario == scenario & df$variable == variable, ]
  #print(head(df))
  df <- melt(data = df, id.vars = c("Tool", "Size", "Scenario", "CaseName"), measure.vars = c("value"))
  
  # x axis labels
  xbreaks = 4^(0:24)
  modelsizes = as.vector(xlabels[[scenario]])
  xvalues = paste(4^(0:24), "\n", modelsizes, "\n", "update")
  
    
  # y axis labels
  ys = -10:10
  ybreaks = 10^ys
  yvalues = parse(text=paste("10^", ys, sep=""))

  variable.title <- gsub("\\.", " ", variable)
  variable.filename <- gsub("\\.", "-", variable)
  
  base <- ggplot(df) +
    labs(title = paste(scenario, " scenario, ", variable.title, sep=""), x = "Model size", y = "Execution time [s]") +
    geom_point(aes(x = as.factor(Size), y = value, col = Tool, shape = Tool), size = 1.5) +
    geom_line(aes(x = as.factor(Size), y = value, col = Tool, group = Tool), size = 0.15) +
    scale_shape_manual(values = seq(0,24)) +
    scale_x_discrete(breaks = xbreaks, labels = xvalues) +
    scale_y_log10(breaks = ybreaks, labels = yvalues) +
    facet_wrap(~ CaseName, ncol = 2) +
    theme_bw() +
    theme(legend.key = element_blank(), legend.title = element_blank(), legend.position = "bottom") +
    guides(shape = guide_legend(ncol = 4))
  print(base)
  
  ggsave(file=paste("../diagrams/", scenario, "-", variable.filename, ".pdf", sep=""), width = 210, height = 297, units = "mm")
}

modelsize.batch <- c("8k", "37k", "158k", "662k", "2.6M", "10M", "40.7M")
modelsize.inject <- c("9k", "35k", "152k", "660k", "2.7M", "10.3M", "41.2M")
modelsize.repair <- c("9k", "35k", "151k", "658k", "2.7M", "10.3M", "41.1M")

xlabels <- data.frame("Batch" = modelsize.batch, "Inject" = modelsize.inject, "Repair" = modelsize.repair);

batch.scenarios = c("Batch")
transformation.scenarios = c("Inject", "Repair")

for (scenario in c(batch.scenarios, transformation.scenarios)) {
  trainBenchmarkPlot(plottimes, scenario, "read", xlabels)
  trainBenchmarkPlot(plottimes, scenario, "check", xlabels)
  trainBenchmarkPlot(plottimes, scenario, "read.and.check", xlabels)
}

for (scenario in transformation.scenarios) {
  trainBenchmarkPlot(plottimes, scenario, "transformation", xlabels)
  trainBenchmarkPlot(plottimes, scenario, "recheck", xlabels)
  trainBenchmarkPlot(plottimes, scenario, "transformation.and.recheck", xlabels)
}
