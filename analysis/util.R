# source: http://www.salemmarafi.com/code/install-r-package-automatically/
usePackage <- function(p) {
  if (!is.element(p, installed.packages()[,1]))
    install.packages(p, dep = TRUE)
  require(p, character.only = TRUE, quietly=T, warn.conflicts=FALSE)
}

getFrame <- function (metrics) {
  frame <- do.call(rbind, lapply(metrics, function(x) data.frame("MetricName" = x[["MetricName"]], 
                                                                 "MetricValue" = x[["MetricValue"]]
                                                                 , stringsAsFactors = F)))
  frame[["MetricValue"]] <- as.numeric(frame[["MetricValue"]])
  frame[["MetricName"]] <- as.numeric(frame[["MetricName"]])
  return(frame)
}

loadData <- function () {
  args <- commandArgs(trailingOnly = TRUE)
  if(!is.na(args[1])){
    path <- args[1]
  }
  data <- fromJSON(path)
  return(data)
}

initElements <- function(data) {
  elements <- data.frame(Type = character(), Count = character(), stringsAsFactors = F)
  
  for (phase in data$PhaseResults) {
    for (metric in (phase[["Metrics"]])) {
      if ("MetricName" %in% names(metric)) {
        if (grepl("elements", metric[["MetricName"]])) { 
          elements <- rbind(elements, data.frame(Type = strsplit(metric[["MetricName"]], "-elements")[[1]], 
                                                 Count = metric[["MetricValue"]], 
                                                 stringsAsFactors = F))
        }
      }
    }
  }
  return(elements)
}