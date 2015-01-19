# Name in Data -> Name on Diagram mapping
toolNames <- c("Drools540", "Drools610", "DroolsSnap", "EMF-IncQuery", "Drools540from", "Drools610from", "DroolsSnapfrom")
toolTitles <- c("Drools 5.4", "Drools 6.1", "Drools Snapshot", "EMF-IncQuery", "Drools 5.4 (from)", "Drools 6.1 (from)", "Drools Snapshot (from)")
# select RGB (in HEX) for each tool
toolColor <- c(
  "Drools540" = "#FF00FF", "Drools610" = "#008000", "DroolsSnap" = "#0000FF", "EMF-IncQuery" = "#FF0000", 
  "Drools540from" = "#FF00FF", "Drools610from" = "#008000", "DroolsSnapfrom" = "#0000FF"
)

# define shape for each tool, see: http://www.cookbook-r.com/Graphs/Shapes_and_line_types/
toolShape <- c(
  "Drools540" = 0, "Drools610" = 0, "DroolsSnap" = 0, "EMF-IncQuery" = 1, 
  "Drools540from" = 2, "Drools610from" = 2, "DroolsSnapfrom" = 2
)

# select tools on plot
drawTools <- c("Drools540", "Drools610", "EMF-IncQuery", "Drools540from", "Drools610from")

# Size (TODO: read data from Metrics table)
Size <- c(     1,    2,    4,     8,    16,    32,     64,    128,    256,     512,    1024,    2048,    4096)
Nodes <- c( 6277,12553,23037, 43469, 88643,176521, 361469, 715871,1423309, 2837515, 5693236,11386472,22772944)
Edges <- c(24617,49221,90264,170264,347172,691320,1416018,2804219,5575683,11115522,22231044,44462088,88924176)
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
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick10
    tick[["title"]] <- "\nNodes\nEdges\nResults"
  } else if ((Ydata == "SumEdit") || (Ydata == "SumReCheck") || 
             (Ydata == "Memory") || 
             (Ydata == "MedianEdit") || (Ydata == "MedianReCheck") ||
             (Ydata == "SumModifyPSumReCheck") || (Ydata == "SumTime") || (Ydata == "IncReValidation") || 
             (Ydata == "Combo")) {
    #tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick7
    #tick[["title"]] <- "\n#nodes + #edges\n# result set size\n# modifications"
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick11
    tick[["title"]] <- "\nNodes\nEdges\nResults\nChange of result set size"
  }
  else { # some default
    tick[["labels"]] <- params[["sizeNodesEdges"]]$Tick11
    tick[["title"]] <- "\nNodes\nEdges\nResults\nChange of result set size"
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
    ylab <- "Time [s]"
  }
  else if ((Ydata == "Check0") || (Ydata == "SumEdit") || (Ydata == "SumReCheck") || 
    (Ydata == "MedianEdit") || (Ydata == "MedianReCheck") ||
    (Ydata == "SumModifyPSumReCheck") || (Ydata == "SumTime") || (Ydata == "IncReValidation")) {
    dataToPlot[[Ydata]] <- dataToPlot[[Ydata]]/1000000
    ylab <- "Time [ms]"
  } else if (Ydata == "Combo") {
    dataToPlot[["ReadPCheck0"]] <- dataToPlot[["ReadPCheck0"]]/1000000
    dataToPlot[["SumModifyPSumReCheck"]] <- dataToPlot[["SumModifyPSumReCheck"]]/1000000
    dataToPlot[["SumReCheck"]] <- dataToPlot[["SumReCheck"]]/1000000
    tick[["ReadPCheck0"]] <- dataToPlot[["ReadPCheck0"]]
    tick[["SumModifyPSumReCheck"]] <- dataToPlot[["SumModifyPSumReCheck"]]
    tick[["SumReCheck"]] <- dataToPlot[["SumReCheck"]]
    ylab <- "Time [ms]"
  } else {
    ylab <- "Maybe numbers? Please adjust function plotYTicks!"
  }
  
  tick[["title"]] <- ylab
  if (Ydata != "Combo")
    tick[["Yvalues"]] <- dataToPlot[[Ydata]]
  return(tick)
}

