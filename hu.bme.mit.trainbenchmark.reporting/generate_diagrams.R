library("jsonlite", quietly=T, verbose=F, warn.conflicts=FALSE)
library("ggplot2",quietly=T, verbose=F, warn.conflicts=FALSE)
library("plyr", quietly=T, verbose=F, warn.conflicts=FALSE)
source("functions.R")
source("plot.R")




resultsPath <- "../results/csv/results.csv"
results <-read.csv(resultsPath, header=TRUE, sep=',')
configPath <- "./config.json"
config <- fromJSON(configPath)
path <- c("../diagrams/")
if (file.exists(path) == FALSE){
  dir.create(path)
}

uniqueScenarios <- unique(results$Scenario)
index <- 0
settings <- PlotSettings()
for(func in config$Summarize_Functions){
  index <- index + 1
  for(scenario in uniqueScenarios){
    subData1 <- subset(results, Scenario==scenario & MetricName=="Time")
    if (config$Dimensions$Groups$Case){
      uniqueTools <- unique(subData1$Tool)
      for(tool in uniqueTools){
        subData2 <- subset(subData1, Tool==tool)
        
        if (config$Dimensions$X_Dimensions$Size){
          title <- paste(tool, ",", scenario, ": ", func, " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", tool, "-GroupBy-Case-Function", index, ".", config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setGroup(settings, "CaseName")
          settings <- setLabels(settings, "Size", "Time (ms)")
          settings <- setAxis(settings, "log10", "log10")
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration == TRUE){
          
        } 
      }
    }
    if (config$Dimensions$Groups$Tool == TRUE){
      if (config$Dimensions$X_Dimensions$Size == TRUE){
        
      }
      if (config$Dimensions$X_Dimensions$Iteration == TRUE){
        
      }     
    }
  }
}