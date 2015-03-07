savePlot <-function(results, settings, func, fileName){
  results$MetricValue <- results$MetricValue / 10**6
  
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
  
  if (nrow(merged) == 0) 
    return()
  if (settings@xDimension == "Size"){
    # summarise the iterations
    data <- ddply(merged, c("Size", "CaseName", "Tool", "Scenario", "RunIndex", "MetricName"),
                  summarise, MetricValue=sum(MetricValue))
    data <- ddply(data, c("Size", "CaseName", "Tool", "Scenario", "MetricName"),
                  summarise, MetricValue=median(MetricValue))
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
    ggtitle(label = settings@title) +
    scale_x_log10(breaks = c(artifacts), labels = c(artifacts))
  
  ggsave(plot,filename = fileName, width=14, height=7, dpi=192)
  print(fileName)
}