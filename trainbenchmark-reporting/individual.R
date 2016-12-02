library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)
library(arules)

source('util.R')

# constants
workloads = c("ConnectedSegments", "PosLength", "RouteSensor", "SemaphoreNeighbor", "SwitchMonitored", "SwitchSet")
phases = c("Read", "Check", "Read.and.Check")
phasesPrettified = c("Read", "Check", "Read and Check")

sizes = list()      # 1     2      4      8      16      32      64      128     256     512     1024    2048   4096
sizes[["Repair"]] = c("8k", "15k", "33k", "66k", "135k", "271k", "566k", "1.1M", "2.2M", "4.6M", "9.3M", "18M", "37M")

toolList = read.csv("tool-list.csv", colClasses=c(rep("character",1)))

# load the data
tsvs = list.files("../results/", pattern = "times-.*\\.csv", full.names = T, recursive = T)
l = lapply(tsvs, read.csv)
times = rbindlist(l)

# preprocess the data
memories$Tool = factor(memories$Tool, levels = toolList$Tool)
keep_descriptions_first_char(times)

times$Model = gsub("\\D+", "", times$Model)
times$Model = as.numeric(times$Model)
times$Time = times$Time / 10^6
# make the phases a factor with a fixed set of values to help dcasting
# (e.g. Batch measurements do not have Transformation and Recheck attributes, 
# hence accessing the "Transformation" attribute would throw an error)
times$Phase = factor(times$Phase, levels = c("Read", "Check", "Transformation", "Recheck"))

times.wide = dcast(data = times,
                   formula = Tool + Workload + Description + Model + Run ~ Phase,
                   value.var = "Time",
                   drop = T,
                   fun.aggregate = mean
)

# calculate aggregated values
times.derived = times.wide
times.derived$Read.and.Check = times.derived$Read + times.derived$Check

# calculate the median value of runs
times.aggregated.runs = ddply(
  .data = times.derived,
  .variables = c("Tool", "Workload", "Description", "Model"),
  .fun = colwise(median),
  .progress = "text"
)
# drop the "Run" column
times.aggregated.runs = subset(times.aggregated.runs, select = -c(Run))

times.processed = melt(
  data = times.aggregated.runs,
  id.vars = c("Tool", "Workload", "Description", "Model"),
  measure.vars = phases,
  variable.name = "Phase",
  value.name = "Time"
)

# beautify plotted record:
# 1. change dots to spaces
# 2. make sure that the phases are still factors
times.plot = times.processed
times.plot$Phase = gsub('\\.', ' ', times.plot$Phase)
times.plot$Phase = factor(times.plot$Phase, levels = phasesPrettified)

### line charts
for (phase in phasesPrettified) {
  # filter the dataframe to the current phase
  df = times.plot[times.plot$Phase == phase, ]
  
  # do not visualize empty data sets
  if (nrow(df) == 0) {
    print(paste("No rows to visualize for phase", phase))
    next
  }
  
  # x axis labels
  xbreaks = unique(df$Model)
  currentWorkloadSizes = head(workloadSizes, n=length(xbreaks))
  xlabels = paste(xbreaks, "\n", currentWorkloadSizes, sep = "")
  
  # drop every other models size
  evens = seq(2, max(log2(max(df$Model)), 2), by=2)
  xlabels[evens] = ""
  
  # y axis labels
  yaxis = nice_y_axis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels
  
  p = ggplot(df) + #na.omit(df)) +
    aes(x = as.factor(Model), y = Time) +
    labs(title = paste("Individual query execution time,", phase, "phase"), x = "Model size\n#Elements", y = "Execution times [ms]") +
    geom_point(aes(col = Tool, shape = Tool), size = 2.0) +
    scale_shape_manual(values = seq(0, 15)) +
    geom_line(aes(col = Tool, group = Tool), size = 0.5) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ Workload, ncol = 2, scale = "fixed") +
    guides(color = guide_legend(ncol = 4)) +
    theme_bw() +
    theme(
      plot.title = element_text(hjust = 0.5),
      text = element_text(size = 10),
      legend.key = element_blank(), 
      legend.title = element_blank(), 
      legend.position = "bottom",
      axis.text = element_text(size = 9)
    )
  print(p)
  
  ggsave(
    plot = p,
    filename = paste("../diagrams/individual-", phase, ".pdf", sep=""),
    width = 210, height = 297, units = "mm"
  )
}
