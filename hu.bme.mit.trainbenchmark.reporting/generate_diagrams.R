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
yLabel <- "Time (ms)"
results$MetricValue <- results$MetricValue / 10**6

if (validConfig(results, config$Summarize_Functions) == FALSE){
  print("Non existing phasename was given!")
  quit()
}

for(func in config$Summarize_Functions){
  index <- index + 1
  for(scenario in uniqueScenarios){
    subData1 <- subset(results, Scenario==scenario & MetricName=="Time")
    
    if (config$Dimensions$Groups$Case){
      uniqueTools <- unique(subData1$Tool)
      settings <- setGroup(settings, "CaseName")
      for(tool in uniqueTools){
        subData2 <- subset(subData1, Tool==tool)
        
        if (config$Dimensions$X_Dimensions$Size){
          title <- paste(tool, ",", scenario, ": ", func, " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", tool, "-GroupBy-Case-Function", index, ".", config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setLabels(settings, "Size", yLabel)
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
            settings <- setLabels(settings, "Iterations", yLabel)
            settings <- setAxis(settings, "Continuous", "Log10")

            savePlot(subData3, settings, func, fileName)
          }
        } 
      }
    }
    
    if (config$Dimensions$Groups$Tool){
      uniqueCases <- unique(subData1$CaseName)
      settings <- setGroup(settings, "Tool")
      for(case in uniqueCases){
        subData2 <- subset(subData1, CaseName==case)
        
        if (config$Dimensions$X_Dimensions$Size){
          title <- paste(scenario, ", ",case, ": ", func, " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", case, "-GroupBy-Tool-Function", index, ".", 
                            config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setLabels(settings, "Size", yLabel)
          settings <- setAxis(settings, "Log10", "Log10")
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration){
          uniqueSizes <-unique(subData2$Size)
          settings <- setDimensions(settings, "Iteration", "MetricValue")
          settings <- setLabels(settings, "Iterations", yLabel)
          settings <- setAxis(settings, "Continuous", yLabel)
          for(size in uniqueSizes){
            subData3 <- subset(subData2, Size==size)
            title <- paste(scenario, ", ", case, ", Size: ", size, ", Function: ", func, " (Y: Log10) (X: Continuous)",
                           sep='')
            
            fileName <- paste(path,scenario, "-", case, "-Size", size, "-GroupBy-Tool-Function", index, ".", 
                              config$Extension, sep='')
            settings <- setTitle(settings, title)
            savePlot(subData3, settings, func, fileName)
          }
        }     
      }
    }
  }
}