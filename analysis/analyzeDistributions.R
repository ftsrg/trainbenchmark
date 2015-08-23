source("util.R")
usePackage("RJSONIO")
usePackage("plyr")
usePackage("igraph")

analyze <- function(metrics, maxElement, type) {
  if (is.character(maxElement)) {
    maxElement <- as.numeric(maxElement)
  }
  frame <- getFrame(metrics)
  frame <- ddply(frame, c("MetricName", "MetricValue"), summarise,
                 All = MetricName * MetricValue)
  allDegree <- sum(frame$All)
  if (type == "Schedule") {
    calculateDegrees(frame, type, maxElement)
  }
  frame$MetricValue <- frame$MetricValue / maxElement
  fit <- fit_power_law(sort(frame$MetricValue, decreasing = T), implementation = "plfit")
  cat(type, " alpha: ", fit$alpha, "\n")
  
  cat(type, " number: ", maxElement, "\n")
  cat(type, " sum degree: ", allDegree, "\n")
  avgDegree <- allDegree / maxElement
  
  cat(type, " average degree : ", avgDegree, "\n\n")
}

calcSumOfDegrees <- function(frame, min, max, type, maxElement) {
  value <- (sum(frame[which(frame[["MetricName"]] < max & frame[["MetricName"]] >= min),]$MetricValue))
  percent <- value / maxElement
  cat(type, " number of degrees between ", min, " and ", max, ": ", value, ", percentage: ", 
      percent, "\n")
  return(value)
}

calculateDegrees <- function(frame, type, maxElement) {
  sum = calcSumOfDegrees(frame, 1, 50, type, maxElement)
  calcSumOfDegrees(frame, 2, 11, type, sum)
  sum <- calcSumOfDegrees(frame, 11, 50, type, sum)
  calcSumOfDegrees(frame, 11, 21, type, sum)
  calcSumOfDegrees(frame, 21, 31, type, sum)
  calcSumOfDegrees(frame, 31, 41, type, sum)
  calcSumOfDegrees(frame, 41, 50, type, sum)
  calcSumOfDegrees(frame, 50, 100, type, maxElement)
  calcSumOfDegrees(frame, 100, 160, type, maxElement)
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