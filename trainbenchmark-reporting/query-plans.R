library(data.table)
library(reshape2)
library(plyr)
library(ggplot2)
library(ggrepel)

source('util.R')

# constants
phases = c("Read", "Check", "Read.and.Check", "Transformation", "Recheck", "Transformation.and.Recheck")

sizes = list()
#                     1     2      4      8      16      32      64      128     256     512     1024    2048   4096
sizes[["Inject"]] = c("5k", "19k", "31k", "67k", "138k", "283k", "573k", "1.2M", "2.3M", "4.6M", "9.2M", "18M", "37M")
sizes[["Repair"]] = c("8k", "15k", "33k", "66k", "135k", "271k", "566k", "1.1M", "2.2M", "4.6M", "9.3M", "18M", "37M")

# load the data
tsvs = list.files("../results/", pattern = "times-.*\\.csv", full.names = T, recursive = T)
tsvs
l = lapply(tsvs, read.csv, colClasses = c("character", "character", "character", "character", "numeric", "character", "numeric", "numeric"))
times = rbindlist(l)

# determine the workloads in the results files
workloads = unique(times$Workload)

#times = keep_descriptions_first_char(times)
times$Description = paste(times$Tool, times$Description)

# preprocess the data
times$Model = gsub("\\D+", "", times$Model)
times$Model = as.numeric(times$Model)
times$Time = times$Time / 10^6
# make the phases a factor with a fixed set of values to help dcasting
# (e.g. Batch measurements do not have Transformation and Recheck attributes, 
# hence accessing the "Transformation" attribute would throw an error)
times$Phase = factor(times$Phase, levels = c("Read", "Check", "Transformation", "Recheck"))

if (nrow(times[times$Phase == "Transformation"]) == 0) {
  stop("There are no records on the 'Transformation' phase in the dataset. Cannot generate report.")
}

times.wide = dcast(data = times,
                   formula = Tool + Workload + Description + Model + Run ~ Phase,
                   value.var = "Time",
                   drop = T,
                   fun.aggregate = mean
)

# calculate aggregated values
times.derived = times.wide
times.derived$Read.and.Check = times.derived$Read + times.derived$Check
times.derived$Transformation.and.Recheck = times.derived$Transformation + times.derived$Recheck

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
times.plot$Phase = factor(times.plot$Phase, levels = c("Read", "Check", "Read and Check", "Transformation", "Recheck", "Transformation and Recheck"))

for (workload in workloads) {
  workloadSizes = sizes[["Repair"]]
  
  # filter the dataframe to the current workload
  df = times.plot[times.plot$Workload == workload, ]
  
  # do not visualize empty data sets
  if (nrow(df) == 0) {
    print(paste("No rows to visualize for workload", workload))
    next
  }
  
  # x axis labels
  xbreaks = as.factor(unique(df$Model))
  currentWorkloadSizes = head(workloadSizes, n=length(xbreaks))
  xlabels = paste(xbreaks, "\n", currentWorkloadSizes, sep = "")
  
  # y axis labels
  yaxis = nice_y_axis()
  ybreaks = yaxis$ybreaks
  ylabels = yaxis$ylabels
  
  # another ugly hack - for both facet sets:
  # - upper (Read, Check, Read and Check),
  # - lower (Transformation, Recheck, Transformation and Recheck),
  # we calculate minimum and maximum values
  validation.facets = c("Read", "Check", "Read and Check")
  read.and.check.extremes = get_extremes(df, validation.facets)
  read.and.check.extremes = create_extremes_for_facets(read.and.check.extremes, validation.facets)
  
  revalidation.facets = c("Transformation", "Recheck", "Transformation and Recheck")
  transformation.and.recheck.extremes = get_extremes(df, revalidation.facets)
  transformation.and.recheck.extremes = create_extremes_for_facets(transformation.and.recheck.extremes, revalidation.facets)
  
  extremes = NULL
  extremes = rbind(extremes, read.and.check.extremes)
  extremes = rbind(extremes, transformation.and.recheck.extremes)
  
  extremes
  
  p = ggplot(df) + 
    #ggplot(na.omit(df)) +
    aes(x = as.factor(Model), y = Time) +
    labs(title = workload, x = "Model size\n#Elements", y = "Execution times [ms]") +
    #geom_point(aes(col = Tool, shape = Tool), size = 2.0) +
    geom_point(aes(col = Description, shape = Description), size = 2.0) +
    scale_shape_manual(values = seq(0, 15)) +
    #geom_line(aes(col = Tool, group = Tool), size = 0.5) +
    geom_line(aes(col = Description, group = Description), size = 0.5) +
    scale_x_discrete(breaks = as.factor(xbreaks), labels = xlabels) +
    scale_y_log10(breaks = ybreaks, labels = ylabels) +
    facet_wrap(~ Phase, ncol = 3, scale = "free_y") +
    geom_point(data = extremes, color = "transparent") + # add extremes for minimum and maximum values
    guides(color = guide_legend()) + #nrow = 1)) +
    theme_bw() +
    theme(
      text = element_text(size = 10),
      legend.key = element_blank(), 
      legend.title = element_blank(), 
      legend.position = "bottom",
      axis.text = element_text(size = 5.5)
    )
  print(p)
  
  ggsave(
    plot = p,
    filename = paste("../diagrams/times-", workload, ".pdf", sep=""),
    width = 210, height = 150, units = "mm"
  )
}
