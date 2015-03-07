savePlot <-function(results, settings, func, fileName){
  results$MetricValue <- results$MetricValue / 10**6
  
  if(length(func) > 1){
    first <- TRUE
    for(phase in func){
      if (first == TRUE){
        merged <- subset(results, PhaseName == phase) # selection on phase
        first <- FALSE
      }
      else{
        merged <- rbind(merged, subset(results, PhaseName == phase)) # merge back
      }
    }
  }
  else{
    merged <- subset(results, PhaseName == func)
  }
  
  if (nrow(merged) == 0) {
    print("Empty merged frame")
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
    data <- ddply(merged, c("CaseName", "Tool", "Scenario", "MetricName", "Iteration"),
                  summarize, MetricValue=median(MetricValue))
  }
  artifacts <- unique(data[[settings@xDimension]])
  #   artifacts <- sort(artifacts)
  minValue <- min(data$MetricValue)
  maxValue <- max(data$MetricValue)
  plot <- ggplot(data,aes_string(x = settings@xDimension, y = settings@yDimension)) +
    geom_line(aes_string(group = settings@group, colour=settings@group)) + 
    geom_point(aes_string(shape = settings@group, colour=settings@group)) + 
    scale_y_log10(breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),7), 
                  labels = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),2)
    ) + 
    ylab(settings@yLabel) +
    xlab(settings@xLabel) +
    ggtitle(label = settings@title)
#     scale_x_log10(breaks = c(artifacts), labels = c(artifacts))
  if (settings@xAxis == "Continuous"){
    plot <- plot + scale_x_continuous(breaks = c(artifacts), labels = c(artifacts))
  }
  else if (settings@xAxis == "Log10"){
    plot <- plot + scale_x_log10(breaks = c(artifacts), labels = c(artifacts))
  }
  ggsave(plot,filename = fileName, width=14, height=7, dpi=192)
  print(fileName)
}