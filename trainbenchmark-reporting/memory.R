library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

source('util.R')

# constants
workloads = c("Batch")

sizes = list()
sizes[["Batch"]] = c("4k", "13k", "34k", "67k", "138k", "278k", "579k", "1.2M", "2.3M", "4.7M", "9.3M", "18.6M", "36.9M")

# load the data
tsvs = list.files("../results/", pattern = "memory-.*\\.csv", full.names = T, recursive = T)
l = lapply(tsvs, read.csv)
memories.raw = rbindlist(l)

# preprocess the data
memories = memories.raw
memories = model_filenames_to_sizes(memories)
memories = subset(memories, select = -c(Description))

plot_labels = ddply(
  .data = memories,
  .variables = c("Tool", "Workload"),
  .fun = colwise(max),
  .progress = "text"
)

memories.plot = memories

for (workload in workloads) {
  workload = "Batch"
  workloadSizes = sizes[[workload]]
  
  # filter the dataframe to the current workload
  df = memories.plot[memories.plot$Workload == workload, ]
  
  # do not visualize empty data sets
  if (nrow(df) == 0) {
    print(paste("No rows to visualize for workload", workload))
    next
  }
  
  # x axis labels
  xbreaks = unique(df$Model)
  currentWorkloadSizes = head(workloadSizes, n=length(xbreaks))
  xlabels = paste(xbreaks, "\n", currentWorkloadSizes, sep = "")
  
  # y axis labels
  yaxis = nice_y_axis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels
  
  p = ggplot(df) + #na.omit(df)) +
    aes(x = as.factor(Model), y = Memory) +
    labs(title = workload, x = "Model size\n#Elements", y = "Execution times [MB]") +
    geom_point(aes(col = Tool, shape = Tool), size = 2.0) +
    scale_shape_manual(values = seq(0, 15)) +
    geom_line(aes(col = Tool, group = Tool), size = 0.5) +
    scale_x_discrete(breaks = xbreaks, labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    guides(color = guide_legend(ncol = 4)) +
    theme_bw() +
    theme(
      text = element_text(size = 10),
      legend.key = element_blank(), 
      legend.title = element_blank(), 
      legend.position = "bottom",
      axis.text = element_text(size = 7)
    ) +
    geom_label_repel(
      data = plot_labels, 
      aes(x = as.factor(Model), y = Memory, label = Tool,  col = Tool),
      size = 1.6, 
      show.legend = F, 
      label.padding = unit(0.12, "lines")
    )
  print(p)
  
  ggsave(
    plot = p,
    filename = paste("../diagrams/memories-", workload, ".pdf", sep=""),
    width = 210, height = 150, units = "mm"
  )
}
