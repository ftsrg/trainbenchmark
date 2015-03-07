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
print(config$Summarize_Functions)


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
          settings <- setAxis(settings, "Log10", "Log10")
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration){
          uniqueSizes <-unique(subData2$Size)
          for(size in uniqueSizes){
            subData3 <- subset(subData2, Size==size)
            title <- paste(scenario, ", ", tool, ", Size: ", size, ", Function: ", func, " (Y: Log10) (X: Continuous)",
                           sep='')
            
            fileName <- paste(path,scenario, "-", tool, "-Size", size, "-GroupBy-Case-Function", index, ".",
                              config$Extension, sep='')
            settings <- setTitle(settings, title)
            settings <- setDimensions(settings, "Iteration", "MetricValue")
            settings <- setGroup(settings, "CaseName")
            settings <- setLabels(settings, "Iterations", "Time (ms)")
            settings <- setAxis(settings, "Continuous", "Log10")

            savePlot(subData3, settings, func, fileName)
          }
        } 
      }
    }
    
    if (config$Dimensions$Groups$Tool){
      uniqueCases <- unique(subData1$CaseName)
      for(case in uniqueCases){
        subData2 <- subset(subData1, CaseName==case)
        
        if (config$Dimensions$X_Dimensions$Size){
          title <- paste(scenario, ", ",case, ": ", func, " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", case, "-GroupBy-Tool-Function", index, ".", 
                            config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setGroup(settings, "Tool")
          settings <- setLabels(settings, "Size", "Time (ms)")
          settings <- setAxis(settings, "log10", "log10")
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration){
          uniqueSizes <-unique(subData2$Size)
          for(size in uniqueSizes){
            subData3 <- subset(subData2, Size==size)
            title <- paste(scenario, ", ", case, ", Size: ", size, ", Function: ", func, " (Y: Log10) (X: Continuous)",
                           sep='')
            
            fileName <- paste(path,scenario, "-", case, "-Size", size, "-GroupBy-Tool-Function", index, ".", 
                              config$Extension, sep='')
            settings <- setTitle(settings, title)
            settings <- setDimensions(settings, "Iteration", "MetricValue")
            settings <- setGroup(settings, "Tool")
            settings <- setLabels(settings, "Iterations", "Time (ms)")
            settings <- setAxis(settings, "Continuous", "Log10")
            savePlot(subData3, settings, func, fileName)
          }
        }     
      }
    }
  }
}