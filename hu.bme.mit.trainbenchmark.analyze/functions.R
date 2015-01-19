preprocessUser <- function(data) {
  # calc median of 100 checks and edits
  data$MedianReCheck <- apply(data[check0Idx+1:check100Idx], 1, median)  # calculate the MEDIAN of the 100 checks
  data$MedianEdit <- apply(data[edit1Idx:edit100Idx], 1, median) # calculate the MEDIAN of the 100 edits
  
  data$IncReValidation <- data$MedianReCheck + data$MedianEdit
  
  return(data)
}

preprocess <- function(data) {
  data$ReadPCheck0 <- data$Read + data$Check0  
  data$SumModifyPSumReCheck <- data$SumModify + data$SumReCheck
  data$SumTime <- data$Read + data$Check0 + data$SumModify + data$SumReCheck
  
  return(data)
}

filterPlotData <- function(data, query, scenario) {
  dataQuery <- data[data$Query==query,]
  params <- setParams(dataQuery, scenario, query)
  dataQuery <- dataQuery[dataQuery$Tool %in% drawTools,]
  result <- list()
  result[["params"]] <- params
  result[["dataQuery"]] <- dataQuery
  return(result)
}

plotSeries <- function(dataQuery, query, phase) {
  dataQuery <- dataQuery[grep(paste("^",phase,sep=""), as.character(dataQuery$variable)),]
  dataQuery$SeqNum <- substring(dataQuery$variable,nchar(phase)+1)
  dataQuery$SeqNum <- as.numeric(dataQuery$SeqNum)
  
  maxSize <- max(dataQuery$Size)
  while (maxSize>=1) {
    if (length(unique(dataQuery[dataQuery$Size == maxSize,]$Tool)) == length(unique(dataQuery$Tool))) {
      break
    }
    maxSize <- maxSize / 2;
  }
  
  dataQuery <- dataQuery[dataQuery$Size==maxSize & dataQuery$Query==query,]
  dataQuery$value <- dataQuery$value/1000000
  
  minValue = max(min(dataQuery$value), 1)
  maxValue = max(max(dataQuery$value), 1)
  plot <- ggplot(data=dataQuery, aes(x=SeqNum, y=value, colour=Tool)) + 
    geom_line(size=0.8) + geom_point() + 
    scale_y_log10( limits=c(minValue,maxValue),
                   breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),3),
                   labels = formatC(round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),3),digits=2,format="f")
    ) + 
    xlab("Index") + ylab("Time (ms)") + 
    ggtitle(paste(phase," Times - ", query, " size=", maxSize, " (y:logscale, x:continuous)",sep="")) +
    theme(text=element_text(family="Helvetica", size=16),
          panel.background = element_rect(fill="#FFFFFF"),
          panel.grid.major = element_line(size=0.3, colour="#333333"),
          panel.grid.minor = element_line(size=0.15, colour="#CCCCCC"),
          axis.text.x = element_text(colour="black"),
          axis.text.y = element_text(colour="black"),
          legend.position="right"
    )
  
  fnameBase <- paste("series_", phase, "_", maxSize, "_",query, sep="")
  fnamePDF <- paste("namedFigs/",fnameBase,".pdf", sep="")
  ggsave(plot, filename=fnamePDF, width=14, height=7, dpi=92, family="CM Sans")
  embedFonts(file=fnamePDF)
  fnamePNG <- paste("namedFigs/",fnameBase,".png", sep="")
  ggsave(plot, filename=fnamePNG, width=14, height=7, dpi=92)
  print(plot)
  cat(paste("<a name=", toAnchor(fnameBase),"></a>", sep=""))
  cat(paste("<a href=\"",fnamePDF,"\">[PDF]</a>", sep=""))
  cat(paste(" <a href=\"",fnamePNG,"\">[PNG]</a><br>\n", sep=""))
  
}

# plot data
plotALog <- function(params, dataToPlot, Ydata, scenario, query) {
  
  dataToPlot <- dataToPlot[dataToPlot$Scenario == scenario & dataToPlot$Query == query, ]
  
  fnamePDF <- paste("namedFigs/", scenario, "_",Ydata,"_",query,".pdf", sep="")
  fnamePNG <- paste("namedFigs/", scenario, "_",Ydata,"_",query,".png", sep="")

  title <- plotTitle(Ydata, scenario, query)
  xticks <- plotXTicks(Ydata, params)
  yticks <- plotYTicks(Ydata, dataToPlot, params)
  dataToPlot[[Ydata]] <- yticks[["Yvalues"]]
  
  
  minValue <- max(min(dataToPlot[[Ydata]]), 0.00001)
  maxValue <- max(round(max(dataToPlot[[Ydata]]))+1, 0.00001)
  
  plot <- ggplot(data=dataToPlot, 
                 aes_string(x = "Size", y = Ydata, colour = "Tool")) + 
    scale_color_manual(values=toolColor, name = "Tools  ", breaks = toolNames, labels=toolTitles) + 
    scale_shape_manual(values=toolShape, name = "Tools  ", breaks = toolNames, labels=toolTitles) +
    geom_line(size=0.8) + 
    geom_point(aes(shape=Tool), size=5) +
    #scale_x_continuous() +
    #scale_y_continuous() +
    #      geom_errorbar(aes(ymin=MinRead, ymax=MaxRead), width=.07) +
    scale_x_log10(breaks = params[["sizeNodesEdges"]]$Size,
                  labels = xticks[["labels"]]) +
    scale_y_log10( limits=c(minValue,maxValue),
                   breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),5),
                   labels = formatC(round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),3),digits=2,format="f")
    ) + 
    xlab(xticks[["title"]]) + ylab(yticks[["title"]]) + 
    ggtitle(title) +
    theme(text=element_text(family="Helvetica", size=16),
          panel.background = element_rect(fill="#FFFFFF"),
          panel.grid.major = element_line(size=0.3, colour="#333333"),
          panel.grid.minor = element_line(size=0.15, colour="#CCCCCC"),
          axis.text.x = element_text(colour="black"),
          axis.text.y = element_text(colour="black"),
          legend.position="right"
    )
  
  anchor <- paste(Ydata, "_", scenario, "_",query, sep="")
  #cat("<div>")   # Workaround to start <div> with generated </div>
  print(plot)     # print and cat overlap...
  #cat("</div>")  # Workaround to end </div> with generated <div>
  cat(paste("\n<a name=", toAnchor(anchor),"></a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePDF,"\">[PDF]</a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePNG,"\">[PNG]</a><br>\n", sep=""))
  

  ggsave(plot, filename=fnamePDF, width=14, height=7, dpi=92)
  embedFonts(file=fnamePDF)
  ggsave(plot, filename=fnamePNG, width=14, height=7, dpi=92)
  
}

flushCat <- function(str) {
  cat(str)
  flush.console()
  cat("\n")
  flush.console()
}

setParams <- function(dataQuery, scenario, query) {
  
  # Get longest running tool to determine RSS1 (todo: code cleanup : )
  longestModelSize <- -1
  maxSize <- max(dataQuery$Size)
  longestScenario <- scenario
#  for (scenario in levels(dataQuery$Scenario)) {
    for(modelSize in maxSize:0) {
      longestToolC <- dataQuery[dataQuery$Scenario==scenario & dataQuery$Size==(2^modelSize),]$Tool[1]
      if ( (! is.na(longestToolC)) & (longestModelSize < modelSize) ) {
        longestModelSize <- modelSize
        longestTool <- longestToolC
#        longestScenario <- scenario
      }
    }
#  }
  
  # sizeNodesEdges: labels on X axis depends on query, todo: should depend on instance model : )
  sizeNodesEdges <- as.data.frame(cbind(Size, Nodes, Edges, Elements))
  RSS1 <- dataQuery[dataQuery$Query==query & dataQuery$Tool==longestTool & dataQuery$Scenario==longestScenario,c("Size","ResultSize1"),]
  sizeNodesEdges <- merge(sizeNodesEdges, RSS1)
  RSS2 <- dataQuery[dataQuery$Query==query & dataQuery$Tool==longestTool & dataQuery$Scenario==longestScenario,c("Size","ResultSizeN"),]
  sizeNodesEdges <- merge(sizeNodesEdges, RSS2)
  # nodes, edges, elements with postfix
  sizeNodesEdges$ElementsPF <- ifelse(sizeNodesEdges$Elements>1000000, paste(as.integer(sizeNodesEdges$Elements/1000000),"M", sep=""),
                                      ifelse(sizeNodesEdges$Elements>1000, paste(as.integer(sizeNodesEdges$Elements/1000),"k", sep=""),
                                             paste(as.integer(sizeNodesEdges$Elements),sep="")))
  sizeNodesEdges$NodesPF <- ifelse(sizeNodesEdges$Nodes>1000000, paste(as.integer(sizeNodesEdges$Nodes/1000000),"M", sep=""),
                                   ifelse(sizeNodesEdges$Nodes>1000, paste(as.integer(sizeNodesEdges$Nodes/1000),"k", sep=""),
                                          paste(as.integer(sizeNodesEdges$Nodes),sep="")))
  sizeNodesEdges$EdgesPF <- ifelse(sizeNodesEdges$Edges>1000000, paste(as.integer(sizeNodesEdges$Edges/1000000),"M", sep=""),
                                   ifelse(sizeNodesEdges$Edges>1000, paste(as.integer(sizeNodesEdges$Edges/1000),"k", sep=""),
                                          paste(as.integer(sizeNodesEdges$Edges),sep="")))
  sizeNodesEdges$ResultSize1PF <- ifelse(sizeNodesEdges$ResultSize1>1000000, paste(as.integer(sizeNodesEdges$ResultSize1/1000000),"M", sep=""),
                                         ifelse(sizeNodesEdges$ResultSize1>2000, paste(as.integer(sizeNodesEdges$ResultSize1/1000),"k", sep=""),
                                                paste(as.integer(sizeNodesEdges$ResultSize1),sep="")))
  # x tick label (elements): 30k, 61k
  sizeNodesEdges$Tick <- paste(as.integer(sizeNodesEdges$Elements/1000),"k", sep="")
  # x tick label (nodes+edges): 6277+24617, 12553+49221
  sizeNodesEdges$Tick2 <- paste(as.integer(sizeNodesEdges$Nodes),"+", 
                                as.integer(sizeNodesEdges$Edges), sep="")
  # x tick label (nodes+edges RSS1->RSS2): 6k+24k 19->29, 12k+49k 41->51
  sizeNodesEdges$Tick3 <- paste(as.integer(sizeNodesEdges$Nodes/1000),"k+", 
                                as.integer(sizeNodesEdges$Edges/1000),"k\n",
                                sizeNodesEdges$ResultSize1, "->", sizeNodesEdges$ResultSizeN, sep="")
  # x tick label (#elements): 0.0M, 0.1M
  sizeNodesEdges$Tick5 <- paste(formatC(round(sizeNodesEdges$Elements/1000000,1),digits=1,format="f"),"M", sep="")
  # x tick label (nodes+edges RSS1): 6k+24k 19, 12k+49k 41, ..., XM+YM Zk
  sizeNodesEdges$Tick6 <- paste(sizeNodesEdges$NodesPF,"+",sizeNodesEdges$EdgesPF,"\n", sizeNodesEdges$ResultSize1PF, sep="")
  # x tick label (nodes+edges RSS1 delta): 6k+24k 19 d=10, 12k+49k 41 d=10
  sizeNodesEdges$Tick7 <- paste(sizeNodesEdges$NodesPF,"+",sizeNodesEdges$EdgesPF,"\n",
                                sizeNodesEdges$ResultSize1PF,"\n",
                                "d=",as.integer(sizeNodesEdges$ResultSizeN-sizeNodesEdges$ResultSize1), sep="")
  # x tick label (#elements RSS1 d=RSS2-RSS1): 30k 19 d=10, 61k 41 d=10
  sizeNodesEdges$Tick8 <- paste(sizeNodesEdges$ElementsPF," ",sizeNodesEdges$ResultSize1," ",
                                "d=",as.integer(sizeNodesEdges$ResultSizeN-sizeNodesEdges$ResultSize1), sep="")
  # x tick label (#elements RSS1): 30k 19, 61k 41
  sizeNodesEdges$Tick9 <- paste(sizeNodesEdges$ElementsPF, sizeNodesEdges$ResultSize1PF)
  # x tick label (nodes, edges, RSS1): 6k,24k,19; 12k,49k,41; ...; XM,YM,Zk
  sizeNodesEdges$Tick10 <- paste(sizeNodesEdges$NodesPF,"\n", sizeNodesEdges$EdgesPF,"\n", sizeNodesEdges$ResultSize1PF, sep="")
  # x tick label (nodes, edges, RSS1, delta): 6k, 24k, 19, 10; 12k, 49k, 41, 10
  sizeNodesEdges$Tick11 <- paste(sizeNodesEdges$NodesPF,"\n",
                                 sizeNodesEdges$EdgesPF,"\n",
                                 sizeNodesEdges$ResultSize1PF,"\n",
                                 as.integer(sizeNodesEdges$ResultSizeN-sizeNodesEdges$ResultSize1), sep="")
  
  result <- list()
  result[["Size"]] <- Size
  result[["Nodes"]] <- Nodes
  result[["Edges"]] <- Edges
  result[["Elements"]] <- Elements
  result[["longestTool"]] <- longestTool
  result[["sizeNodesEdges"]] <- sizeNodesEdges
  return(result)
}

toAnchor <- function(name) {
  return(gsub("\\+", "-", name))
}

getMaxColumn <- function(name, data) {
  maxIndex <- -1
  repeat{
    if(is.null(data[[paste(name, maxIndex + 1, sep="_")]])){
      break
    }
    
    maxIndex <- maxIndex + 1
  }
  
  maxIndex
}

# http://datadebrief.blogspot.hu/2011/03/ascii-code-table-in-r.html
asc <- function(x) { strtoi(charToRaw(x),16L) }


# http://stackoverflow.com/questions/11120840/hash-string-into-rgb-color
# http://en.wikipedia.org/wiki/Jenkins_hash_function
hashString <- function(string){
  hashString <- digest(string, algo="sha256")
  hexaString <- paste("0x", substr(hashString, nchar(hashString)-5, nchar(hashString)), sep="")
  as.integer(hexaString)
}

stringToColor <- function(string) {
  hash <- hashString(string)[[1]]
  
  colors <- c()
  colors["r"] <- bitwShiftR(bitwAnd(hash, 0xFF0000), 16) + 1
  colors["g"] <- bitwShiftR(bitwAnd(hash, 0x00FF00), 8) + 1
  colors["b"] <- bitwAnd(hash, 0x0000FF) + 1
  
  
  if(sum(colors) > (0xFF * 3 * 0.7)) {
#     colors <- colors * 0.6
    colors <- colors * (0xFF * 0.7 / (min(colors) + max(colors)) * 2) 
  }
  else if (sum(colors) < (0xFF * 3 * 0.5)) {
    colors <- colors * 1.3
  }
  
#   colors <- colors * (0xFF * 0.6 / (min(colors) + max(colors)) * 2) 
  
  r <- ceiling(max(min(colors[["r"]], 0xFF), 0))
  g <- ceiling(max(min(colors[["g"]], 0xFF), 0))
  b <- ceiling(max(min(colors[["b"]], 0xFF), 0))
  
  paste("#",
        format(as.hexmode(r), width = 2, upper.case = T),
        format(as.hexmode(g), width = 2, upper.case = T),
        format(as.hexmode(b), width = 2, upper.case = T),
        sep = ""
  )
}
