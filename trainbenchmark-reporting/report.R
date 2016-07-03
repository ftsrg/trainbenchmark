library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

tsvs <- list.files("../results/", pattern = "times-.*\\.csv", full.names = T, recursive = T)
l <- lapply(tsvs, read.csv)
times <- rbindlist(l)

# preprocessing
times$Model = gsub("\\D+", "", times$Model)
times$Model = as.numeric(times$Model)
times$Time = times$Time / 10^9
# make the phases a factor with a fixed set of values to help dcasting
# (e.g. Batch measurements do not have Transformation and Recheck attributes, hence accessing the "Transformation" attribute would throw an error)
times$Phase = factor(times$Phase, levels = c("Read", "Check", "Transformation", "Recheck"))

times.wide = dcast(times,
                   Tool + Workload + Description + Model + Run ~ Phase,
                   value.var = "Time",
                   drop = F,
                   fun.aggregate = mean
)
# calculate aggregated values
times.derived = times.wide
times.derived$Read.and.Check = times.derived$Read + times.derived$Check
times.derived$Transformation.and.Recheck = times.derived$Transformation + times.derived$Recheck

times.aggregated.runs = ddply(
  .data = times.derived,
  .variables = c("Tool", "Workload", "Description", "Model"),
  .fun = colwise(median),
  .progress = "text"
)

times.aggregated.runs

times.plot = melt(
  data = times.aggregated.runs,
  id.vars = c("Tool", "Workload", "Description", "Model", "Run"),
  measure.vars = c("Read", "Check", "Read.and.Check", "Transformation", "Recheck", "Transformation.and.Recheck"),
  variable.name = "Phase",
  value.name = "Time"
)

times.plot$Phase = factor(times.plot$Phase, levels = c("Read", "Transformation", "Check", "Recheck", "Read.and.Check", "Transformation.and.Recheck"))

workloads = c("RouteSensor", "ConnectedSegments", "ActiveRoute")

for (workload in workloads) {
  print(workload)
  df = times.plot[times.plot$Workload == workload, ]
  
  # do not visualize empty data sets
  if (nrow(df) == 0) {
    next
  }

  p = ggplot(na.omit(df)) +
    aes(x = as.factor(Model)) +
    labs(title = workload, x = "Model size\n#Triples", y = "Execution times [s]") +
    geom_point(aes(y = Time, col = Description, shape = Description), size = 2.0) +
    geom_line(aes(y = Time, col = Description, group = Description), size = 0.5) +
    scale_y_log10() + #breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ Phase, ncol = 2, scale = "free_y")
  print(p)
  
  ggsave(plot = p, filename = paste("../diagrams/", workload, ".pdf", sep=""))
}
