## @knitr dependencies
library(ggplot2, quietly=T)
library(knitr, quietly=T)
library(scales, quietly=T)
library(grid, quietly=T)
library(reshape, quietly=T, verbose=F, warn.conflicts=FALSE)
library(xtable, quietly=T)
library(digest, quietly=T)
source("functions.R")
dir.create(file.path("namedFigs"), showWarnings = FALSE)
library(extrafont, quietly=T)
font_install('fontcm')
loadfonts(quiet=T)
loadfonts(device="pdf", quiet=T)

## @knitr readBasic
dataTB_orig <- read.csv(file="../results.txt", sep="\t")
data <- dataTB_orig[,c("RunIndex", "Tool", "Query","Scenario", "File", "Size","NOfModifications","Read","Check0", "SumModify", "SumReCheck", "SumEdit","ResultSize1","ResultSizeN","Memory")]
data <- aggregate(formula = cbind(Read,Check0,SumModify,SumReCheck,SumEdit,Memory,ResultSize1,ResultSizeN,Size) ~ Tool+Query+Scenario+File, data = data, FUN = min)
data <- preprocess(data)
source("generateConfig.R")


## @knitr tabularOut
cat("<h3>Raw tabular results</h3>")
print(xtable(data), type="html")


## @knitr dummyPlotForWorkaround
cat("<!--");
myplot <- plot(1, type="n", axes=F, xlab="", ylab="")
print(myplot);
cat("-->");

## @knitr detailedBatch
for (scenario in levels(data$Scenario)) {
  cat(paste("<h3>Scenario: ", scenario, "</h3>\n", sep=""))
  for (query in levels(data$Query)) {
    cat(paste("<h4>Query: ", query, "</h4>\n", sep=""))
    
    dataQuery <- data[data$Query==query,]
    params <- setParams(dataQuery, scenario, query)
    dataQuery <- dataQuery[dataQuery$Tool %in% drawTools,]
    
    plotALog(params, dataQuery, "Read", scenario, query); cat("\n")
    plotALog(params, dataQuery, "Check0", scenario, query); cat("\n")
    plotALog(params, dataQuery, "SumEdit", scenario, query); cat("\n")
    plotALog(params, dataQuery, "SumReCheck", scenario, query); cat("\n")
    plotALog(params, dataQuery, "Memory", scenario, query); cat("\n")
    
    plotALog(params, dataQuery, "ReadPCheck0", scenario, query); cat("\n")
    plotALog(params, dataQuery, "SumModifyPSumReCheck", scenario, query); cat("\n")
    plotALog(params, dataQuery, "SumTime", scenario, query); cat("\n")
  }
}


## @knitr readUserData
# get User data
dataTB_orig <- read.csv(file="../results-user.txt", sep="\t", header=TRUE)


# columns: 6..15: size..memory
# columns: 17..117: checks 0..100
check0Idx <- 17; checkMaxIdx <- check0Idx + getMaxColumn("Check", dataTB_orig)
# columns: 119..218: edits 1..100
edit1Idx <- checkMaxIdx + 2; editMaxIdx <- edit1Idx + getMaxColumn("Edit", dataTB_orig) - 1
# columns: 220..320: invalids 0..100
invalid0Idx <- editMaxIdx + 2; invalidMaxIdx <- invalid0Idx + getMaxColumn("Invalid", dataTB_orig)
numericDataToAggregate <- cbind(dataTB_orig[6:15],dataTB_orig[check0Idx:checkMaxIdx],
                                                  dataTB_orig[edit1Idx:editMaxIdx],
                                                  dataTB_orig[invalid0Idx:invalidMaxIdx])
data <- aggregate(numericDataToAggregate,
                  by=list(Tool=dataTB_orig$Tool, Query=dataTB_orig$Query, Scenario=dataTB_orig$Scenario, File=dataTB_orig$File), 
                  data=dataTB_orig, FUN = mean)  # calculate the MEAN of series
data <- preprocessUser(data)
data <- preprocess(data)

# just for the User scenario
## @knitr detailedUserEditRecheck
for (query in levels(data$Query)) {
  cat(paste("<h4>Query: ", query, "</h4>\n", sep=""))
  
  filteredData <- filterPlotData(data, query, scenario)
  
  plotALog(filteredData[["params"]], filteredData[["dataQuery"]], "MedianEdit", scenario, query)
  plotALog(filteredData[["params"]], filteredData[["dataQuery"]], "MedianReCheck", scenario, query)
  plotALog(filteredData[["params"]], filteredData[["dataQuery"]], "IncReValidation", scenario, query)
}

## @knitr userSequences
for (query in levels(data$Query)) {
  cat(paste("<h4>Query: ", query, "</h4>\n", sep=""))
  
  dataQuery <- data[data$Query==query & data$Scenario==scenario,]
  dataQuery <- dataQuery[dataQuery$Tool %in% drawTools,]

  dataQuery <- melt(dataQuery, id=c("Tool", "Query", "Size", "Scenario", "File"))  
  
  plotSeries(dataQuery, query, "DetCheck")
  plotSeries(dataQuery, query, "DetEdit")
}
