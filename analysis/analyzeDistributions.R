source("util.R")
usePackage("RJSONIO")
usePackage("plyr")
usePackage("igraph")

analyze <- function(metrics, maxElement, type) {
  if (is.character(maxElement)) {
    maxElement <- as.numeric(maxElement)
  }
  frame <- getFrame(metrics)
  allDegree <- sum(ddply(frame, c("MetricName", "MetricValue"), summarise,
                         All = MetricName * MetricValue)$All)
  frame$MetricValue <- frame$MetricValue / maxElement
  fit <- fit_power_law(sort(frame$MetricValue, decreasing = T), implementation = "plfit")
  cat(type, " alpha: ", fit$alpha, "\n")
  
  cat(type, " number: ", maxElement, "\n")
  cat(type, " sum degree: ", allDegree, "\n")
  avgDegree <- allDegree / maxElement
  
  cat(type, " average degree : ", avgDegree, "\n")
}


data <- loadData()
elements <- initElements(data)
types <- c("Train", "Station", "Schedule")

for (phase in data$PhaseResults) {
  for (metric in (phase[["Metrics"]])) {
    if ("CompositeName" %in% names(metric)) {
      if (metric[["CompositeName"]] %in% types) {
        name <- metric[["CompositeName"]]
        maxElement <- elements[which(elements[["Type"]] == name),][["Count"]]
        analyze(metric[["NestedMetrics"]], 
                maxElement, name)
      }
    }
  }
}