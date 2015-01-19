# load .json files and convert into nested lists
load_results <-function(){
  files <- dir("../../trainbenchmark-results/", pattern = '\\.json', full.names = TRUE)
  lists <- lapply(files,fromJSON)
}


change_time_metrics <- function(table, divisor){
  table$readTime <- (table$readTime / divisor)
  table$checkTimeSum <- (table$checkTimeSum / divisor)
  table$editTimeSum <- (table$editTimeSum / divisor)
  table$checkTimes <- (table$checkTimes / divisor)
  table$editTimes <- (table$editTimes / divisor)
  return(table)
}


get_artifacts <- function(table){
  artifacts <- unique(table$artifactSize)
  artifacts <- sort(artifacts)
  return(artifacts)
}