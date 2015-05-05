source("theme.R")

savePlot <-function(results, settings, phases, fileName){
  phases <- unlist(phases)
  first <- TRUE
  for(phase in phases){
    
    if (first == TRUE){
      merged <- subset(results, PhaseName == phase) # selection on phase
      first <- FALSE
    }
    else{
      merged <- rbind(merged, subset(results, PhaseName == phase)) # merge back
    }
  }
  
  if (nrow(merged) == 0) {
    return()
  }
  if (settings@xDimension == "Size"){
    # summarise the iterations
    data <- ddply(merged, c("Size", "CaseName", "Tool", "Scenario", "RunIndex", "MetricName"),
                  summarize, MetricValue=sum(MetricValue))
    data <- ddply(data, c("Size", "CaseName", "Tool", "Scenario", "MetricName"),
                  summarize, MetricValue=median(MetricValue))
  }
  else if(settings@xDimension == "Iteration"){
    data <- ddply(merged, c("CaseName", "Tool", "Scenario", "RunIndex", "MetricName", "Iteration"),
                  summarize, MetricValue=sum(MetricValue))
    data <- ddply(data, c("CaseName", "Tool", "Scenario", "MetricName", "Iteration"),
                  summarize, MetricValue=median(MetricValue))
  }
  artifacts <- unique(data[[settings@xDimension]])
  minValue <- min(data$MetricValue)
  maxValue <- max(data$MetricValue)
  if (minValue == 0){
    print("The minimum metricvalue equals with 0. The plot cannot be generated.")
    return()
  }
  if (settings@xDimension == "Size"){
    xLabels <- getXLabels(artifacts)
  }
  else{
    xLabels <- c(artifacts)
  }
  plot <- ggplot(data,aes_string(x = settings@xDimension, y = settings@yDimension)) +
    geom_line(aes_string(group = settings@group, colour=settings@group), size=lineSize) + 
    geom_point(aes_string(shape = settings@group, colour=settings@group), size=pointSize) +
    scale_shape_manual(values=1:nlevels(data[[settings@group]])) +
    ylab(settings@yLabel) +
    xlab(settings@xLabel) +
    ggtitle(label = settings@title) +
    bwTheme

  if (settings@xAxis == "Continuous"){
    plot <- plot + scale_x_continuous(breaks = c(artifacts))
  }
  else if (settings@xAxis == "Log10"){
    plot <- plot + scale_x_log10(breaks = c(artifacts), labels = xLabels)
  }

  if (settings@yAxis == "Continuous"){
    plot <- plot + scale_y_continuous(breaks = seq(minValue, maxValue, by=round(maxValue/5,0)),
                                      labels = seq(minValue, maxValue, by=round(maxValue/5,0)))
  }
  else if (settings@yAxis == "Log10"){
    plot <- plot + scale_y_log10(breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),7), 
                                labels = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),2))
  }
  ggsave(plot,filename = fileName, width=14, height=7, dpi=192)
  print(fileName)


}

validPhase <- function(results, functions){
  uniquePhases <- unique(results$PhaseName)
  for(func in functions){
    for(phase in func){
      if(phase %in% uniquePhases == FALSE){
        return(FALSE)
      }
    }
  }
  return(TRUE)
}

validMetric <- function(results, metric){
  if (metric %in% levels(results$MetricName)){
    return(TRUE)
  }
  return(FALSE)
}

createFolders <- function(rootPath, subFolders){
  if (file.exists(rootPath) == FALSE){
    dir.create(rootPath)
  }
  for (folder in subFolders){
    path <- paste(rootPath, folder, sep='')
    if (file.exists(path) == FALSE){
      dir.create(path)
    }
  }
}

concatPhases <- function(phases){
  phases <- unlist(phases)
  first <- TRUE
  for(p in phases){
    if(first){
      merged <- p
      first <- FALSE
    }
    else{
      merged <- paste(merged, p, sep='+')
    }
  }
  return(merged)
}

getXLabels <- function(artifacts){
  ticks <- c()
  for(size in artifacts){
    ticks <- c(ticks, labels[[as.character(size)]])
  }
  return(ticks)
}
