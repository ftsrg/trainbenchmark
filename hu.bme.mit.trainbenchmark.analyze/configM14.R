toolLineType <- c("IncQueryD" = 1, "IncQueryD ReadPCheck0" = 1, "IncQueryD SumModifyPSumReCheck" = 1, "IncQueryD SumReCheck" = 1, 
                  "4store" = 4, "4store ReadPCheck0" = 6, "4store SumModifyPSumReCheck" = 6, "4store SumReCheck" = 6)

# Name in Data -> Name on Diagram mapping
toolNames <-  c("EclipseOCL",  "EMF-IncQuery", "Java", "Neo4j", "Drools", "4store", "IncQueryD",
                "IncQueryD ReadPCheck0", "IncQueryD SumModifyPSumReCheck", "IncQueryD SumReCheck", 
                "4store ReadPCheck0", "4store SumModifyPSumReCheck", "4store SumReCheck",
                "ReadPCheck0", "SumModifyPSumReCheck", "SumReCheck"
)
toolTitles <- c("Eclipse OCL", "EMF-IncQuery", "Java", "Neo4j", "Drools", "4store", "IncQuery-D",
                expression("IncQueryD read+check"[0]), expression("IncQueryD modify+recheck"), expression("IncQueryD recheck"),
                expression("4store read+check"[0]), expression("4store modify+recheck"), expression("4store recheck"),
                expression("read+check"[0]), expression("modify+recheck"), expression("recheck")
)
# select RGB (in HEX) for each tool
toolColor <- c(
  "EclipseOCL" = "#0084D1", "EMF-IncQuery" = "#FF420E", "Java" = "#FFD320", 
  "Neo4j" = "#314004", "Drools" = "#00FF00", "4store" = "#5882FA", "IncQueryD" = "#FE2E2E",
  "IncQueryD ReadPCheck0" = "#4B1F6F", "IncQueryD SumModifyPSumReCheck" = "#FF950E", "IncQueryD SumReCheck" = "#C5000B", 
  "4store ReadPCheck0" = "#AECF00", "4store SumModifyPSumReCheck" = "#7E0021", "4store SumReCheck" = "#0084D1",
  "ReadPCheck0" = "#C5000B", "SumModifyPSumReCheck" = "#0084D1", "SumReCheck" = "#AECF00"
)
# define shape for each tool, see: http://www.cookbook-r.com/Graphs/Shapes_and_line_types/
toolShape <- c(
  "EclipseOCL" = 0, "EMF-IncQuery" = 2, "Java" = 1, "Neo4j" = 3, "Drools" = 4, "4store" = 1, "IncQueryD" = 5,
  "IncQueryD ReadPCheck0" = 5, "IncQueryD SumModifyPSumReCheck" = 5, "IncQueryD SumReCheck" = 5, 
  "4store ReadPCheck0" = 1, "4store SumModifyPSumReCheck" = 1, "4store SumReCheck" = 1,
  "ReadPCheck0" = 5, "SumModifyPSumReCheck" = 1, "SumReCheck" = 6
)
# select tools on plot
#drawTools=c("EclipseOCL", "EMF-IncQuery", "Java", "Neo4j", "Drools")
drawTools=c("IncQueryD", "4store")

# Size (TODO: read data from Metrics table)
Size <- c(     1,        4,         16,         64,        256,         1024,       4096)
Nodes <- c( 6277,23037,  88643, 361469, 1423309,  5695827, 22772944)
Edges <- c(17424,63892,245761,1002389,3946935,  15794751,63179004)
Elements <- Nodes + Edges

# title text of different plots
plotTitle <- function(Ydata, scenario, query) {
  if (Ydata == "Read")               title <- paste("Read time for query ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "Check0")        title <- paste("First check time for query ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "SumEdit")       title <- paste("Sum of Edit times for query ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "SumReCheck")    title <- paste("Sum of recheck times for query ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "Memory")        title <- paste("Memory usage of query ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "MedianEdit")    title <- paste("Time of one edit operation ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "MedianReCheck") title <- paste("Time of one recheck operation ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "ReadPCheck0")   title <- paste("Batch validation time ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "SumModifyPSumReCheck") title <- paste("Total revalidation time ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "SumTime")       title <- paste("Total time of the phases ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "IncReValidation")      title <- paste("Time of one revalidation ",query," (x,y:logscale), ", scenario, sep="")
  else if (Ydata == "Combo")      title <- paste("Combo processing time ",query," (x,y:logscale), ", scenario, sep="")
  else title <- paste("Wow, seems to be a new type of diagram! Please adjust plotTitles()! Query: ",
                      query," (x,y:logscale), Scenario: ", scenario, sep="")
  return(title)
}

# select X label (called title) and labels on the X axis form the pregenerated values
plotXTicks <- function(Ydata, params) {
  tick <- list()
  if ((Ydata == "Read") || (Ydata == "Check0") || (Ydata == "ReadPCheck0")) {
    #tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick6
    #tick[["title"]] <- "\n#nodes + #edges\n# result set size"
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Size
    tick[["title"]] <- "Problem size"
  } else if ((Ydata == "SumEdit") || (Ydata == "SumReCheck") || 
               (Ydata == "Memory") || 
               (Ydata == "MedianEdit") || (Ydata == "MedianReCheck") ||
               (Ydata == "SumModifyPSumReCheck") || (Ydata == "SumTime") || (Ydata == "IncReValidation") || 
               (Ydata == "Combo")) {
    #tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick7
    #tick[["title"]] <- "\n#nodes + #edges\n# result set size\n# modifications"
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Size
    tick[["title"]] <- "Problem size"
  }
  else { # some default
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick11
    tick[["title"]] <- "(Nodes, Edges, Results, Modifications)"
  }
  return(tick)
}

# select Y label and scale Y values accordingly
plotYTicks <- function(Ydata, dataToPlot, params) {
  tick <- list()
  
  if (Ydata == "Memory") {
    dataToPlot[[Ydata]] <- dataToPlot[[Ydata]]/1000
    ylab <- "Memory [MB]"
  }
  else if ((Ydata == "Read") || (Ydata == "ReadPCheck0")) {
    dataToPlot[[Ydata]] <- dataToPlot[[Ydata]]/1000000000
    ylab <- "Execution time [s]"
  }
  else if ((Ydata == "Check0") || (Ydata == "SumEdit") || (Ydata == "SumReCheck") || 
             (Ydata == "MedianEdit") || (Ydata == "MedianReCheck") ||
             (Ydata == "SumModifyPSumReCheck") || (Ydata == "SumTime") || (Ydata == "IncReValidation")) {
    #dataToPlot[[Ydata]] <- dataToPlot[[Ydata]]/1000000
    #ylab <- "Time [ms]"
    dataToPlot[[Ydata]] <- dataToPlot[[Ydata]]/1000000000
    ylab <- "Execution time [s]"
  } else if (Ydata == "Combo") {
    dataToPlot[["ReadPCheck0"]] <- dataToPlot[["ReadPCheck0"]]/1000000000
    dataToPlot[["SumModifyPSumReCheck"]] <- dataToPlot[["SumModifyPSumReCheck"]]/1000000000
    dataToPlot[["SumReCheck"]] <- dataToPlot[["SumReCheck"]]/1000000000
    tick[["ReadPCheck0"]] <- dataToPlot[["ReadPCheck0"]]
    tick[["SumModifyPSumReCheck"]] <- dataToPlot[["SumModifyPSumReCheck"]]
    tick[["SumReCheck"]] <- dataToPlot[["SumReCheck"]]
    #ylab <- "Time [ms]"
    ylab <- "Execution time [s]"
  } else {
    ylab <- "Maybe numbers? Please adjust function plotYTicks!"
  }
  
  tick[["title"]] <- ylab
  if (Ydata != "Combo")
    tick[["Yvalues"]] <- dataToPlot[[Ydata]]
  return(tick)
}
