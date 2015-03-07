library("jsonlite", quietly=T, verbose=F, warn.conflicts=FALSE)
library("ggplot2",quietly=T, verbose=F, warn.conflicts=FALSE)
library("plyr", quietly=T, verbose=F, warn.conflicts=FALSE)
source("functions.R")
source("plot.R")
source("constants.R")




results <-read.csv(resultsPath, header=TRUE, sep=',')

config <- fromJSON(configPath)

if (validConfig(results, config$Summarize_Functions$Phases) == FALSE){
  print("Non existing phasename was given!")
  quit()
}

uniqueScenarios <- unique(results$Scenario)
index <- 0
settings <- PlotSettings()
results$MetricValue <- results$MetricValue / scaleDivisor
createFolders(rootPath, uniqueScenarios)

for(func in config$Summarize_Functions$Phases){
  index <- index + 1
  for(scenario in uniqueScenarios){
    subData1 <- subset(results, Scenario==scenario & MetricName=="Time")
    path <- paste(rootPath, scenario, "/", sep='')
    
    if (config$Dimensions$Groups$Case){
      uniqueTools <- unique(subData1$Tool)
      settings <- setGroup(settings, "CaseName")
      for(tool in uniqueTools){
        subData2 <- subset(subData1, Tool==tool)
        
        if (config$Dimensions$X_Dimensions$Size){
          title <- paste(tool, ",", scenario, ": ", concatPhases(func), " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", tool, "-GroupBy-Case-Function", index, ".", config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setLabels(settings, "Size", yLabel)
          settings <- setAxis(settings, "Log10", yAxis)
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration){
          uniqueSizes <-unique(subData2$Size)
          for(size in uniqueSizes){
            subData3 <- subset(subData2, Size==size)
            title <- paste(scenario, ", ", tool, ", Size: ", size, ", Function: ", concatPhases(func), 
                           " (Y: Log10) (X: Continuous)", sep='')
            
            fileName <- paste(path,scenario, "-", tool, "-Size", size, "-GroupBy-Case-Function", index, ".",
                              config$Extension, sep='')
            settings <- setTitle(settings, title)
            settings <- setDimensions(settings, "Iteration", "MetricValue")
            settings <- setLabels(settings, "Iterations", yLabel)
            settings <- setAxis(settings, "Continuous", yAxis)

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
          title <- paste(scenario, ", ",case, ": ", concatPhases(func), " (Y: Log10) (X: Log10)", sep='')
          fileName <- paste(path,scenario, "-", case, "-GroupBy-Tool-Function", index, ".", 
                            config$Extension, sep='')
          settings <- setTitle(settings, title)
          settings <- setDimensions(settings, "Size", "MetricValue")
          settings <- setLabels(settings, "Size", yLabel)
          settings <- setAxis(settings, "Log10", yAxis)
          savePlot(subData2, settings, func, fileName)
        }
        
        if (config$Dimensions$X_Dimensions$Iteration){
          uniqueSizes <-unique(subData2$Size)
          settings <- setDimensions(settings, "Iteration", "MetricValue")
          settings <- setLabels(settings, "Iterations", yLabel)
          settings <- setAxis(settings, "Continuous", yAxis)
          for(size in uniqueSizes){
            subData3 <- subset(subData2, Size==size)
            title <- paste(scenario, ", ", case, ", Size: ", size, ", Function: ", concatPhases(func), 
                           " (Y: Log10) (X: Continuous)", sep='')
            
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