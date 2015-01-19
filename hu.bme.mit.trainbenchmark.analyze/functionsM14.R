
plotALogM14 <- function(params, dataToPlot, Ydata, scenario, query) {
  
  dataToPlot <- dataToPlot[dataToPlot$Scenario == scenario & dataToPlot$Query == query, ]
  
  fnamePDF <- paste("namedFigsM14/", scenario, "_",Ydata,"_",query,".pdf", sep="")
  fnamePNG <- paste("namedFigsM14/", scenario, "_",Ydata,"_",query,".png", sep="")
  
  title <- plotTitle(Ydata, scenario, query)
  xticks <- plotXTicks(Ydata, params)
  yticks <- plotYTicks(Ydata, dataToPlot, params)
  dataToPlot[[Ydata]] <- yticks[["Yvalues"]]
  
  #minValue <- min(1,dataToPlot[[Ydata]])
  minValue <- min(dataToPlot[[Ydata]])
  maxValue <- round(max(dataToPlot[[Ydata]]))+1
  .e <- environment()
  plot <- ggplot(data=dataToPlot, 
                 aes_string(x = "Size", y = Ydata), environment = .e) + 
    scale_color_manual(values=toolColor, name = "Combined execution phases  ", breaks = toolNames, labels=toolTitles) + 
    scale_shape_manual(values=toolShape, name = "Combined execution phases  ", breaks = toolNames, labels=toolTitles) +
    scale_linetype_manual(values=toolLineType, name = "Configuration", breaks = toolNames, labels=toolTitles) +
    geom_line(size=1.5, environment = .e, aes(linetype=paste(Tool), colour=paste(Ydata))) + 
    geom_point(size=5, environment = .e, aes(shape=paste(Ydata))) +
    #scale_x_continuous() +
    #scale_y_continuous() +
    #      geom_errorbar(aes(ymin=MinRead, ymax=MaxRead), width=.07) +
    scale_x_log10(limits=c(1,4096),
                  breaks = params[["sizeNodesEdges"]]$Size,
                  labels = xticks[["labels"]]) +
    scale_y_log10( limits=c(minValue,maxValue),
                   breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/4)),3),
                   labels = formatC(round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/4)),3),digits=2,format="f")
    ) + 
    #xlab(xticks[["title"]]) + 
    xlab(paste("\n", xticks[["title"]], sep="")) + 
    ylab(paste(yticks[["title"]], "\n", sep="")) + 
    #ggtitle(title) +
    theme(text=element_text(family="NimbusSan", size=32),
          panel.background = element_rect(fill="#FFFFFF"),
          panel.grid.major = element_line(size=0.5, colour="#7B7B7B"),
          #panel.grid.minor = element_line(size=0.25, colour="#C4C4C4"),
          axis.text.x = element_text(colour="black"),
          axis.text.y = element_text(colour="black"),
          axis.title.x = element_text(vjust=2),
          axis.title.y = element_text(vjust=0.6),
          legend.position="none",  # right
          legend.title=element_blank()
    )
  #out <- NULL
  #out <- c(out, knit_child('diagPaste.Rhtml'))
  #paste(out, collapse = '\n')
  
  anchor <- paste(Ydata, "_", scenario, "_",query, sep="")
  #cat("<div>")   # Workaround to start <div> with generated </div>
  print(plot)     # print and cat overlap...
  #cat("</div>")  # Workaround to end </div> with generated <div>
  cat(paste("\n<a name=", toAnchor(anchor),"></a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePDF,"\">[PDF]</a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePNG,"\">[PNG]</a><br>\n", sep=""))
  
  
  ggsave(plot, filename=fnamePDF, width=11, height=5, dpi=92)
  embedFonts(file=fnamePDF)
  ggsave(plot, filename=fnamePNG, width=14, height=7, dpi=92)
  
}

plotALogM14Multi <- function(params, dataToPlot, scenario, query) {
  
  dataToPlot <- dataToPlot[dataToPlot$Scenario == scenario & dataToPlot$Query == query, ]
  
  fnamePDF <- paste("namedFigsM14/", scenario, "_Combo_",query,".pdf", sep="")
  fnamePNG <- paste("namedFigsM14/", scenario, "_Combo_",query,".png", sep="")
  
  title <- plotTitle("Combo", scenario, query)
  xticks <- plotXTicks("Combo", params)
  yticks <- plotYTicks("Combo", dataToPlot, params)
  dataToPlot[["ReadPCheck0"]] <- yticks[["ReadPCheck0"]]
  dataToPlot[["SumModifyPSumReCheck"]] <- yticks[["SumModifyPSumReCheck"]]
  dataToPlot[["SumReCheck"]] <- yticks[["SumReCheck"]]
  
  #minValue <- min(1,dataToPlot[[Ydata]])
  minValue <- min(dataToPlot[["SumReCheck"]])
  maxValue <- round(max(dataToPlot[["ReadPCheck0"]]))+1
  plot <- ggplot(data=dataToPlot) + 
    scale_color_manual(values=toolColor, name = "Combined execution phases  ", breaks = toolNames, labels=toolTitles) + 
    scale_shape_manual(values=toolShape, name = "Combined execution phases  ", breaks = toolNames, labels=toolTitles) +
    scale_linetype_manual(values=toolLineType, name = "Configuration", breaks = toolNames, labels=toolTitles) +
    geom_line(size=1.5,aes(x=Size, y=ReadPCheck0, colour=paste("ReadPCheck0"), linetype=paste(Tool))) + 
            geom_point(aes(x=Size, y=ReadPCheck0, shape =paste("ReadPCheck0")), size=5) +
    geom_line(size=1.5,aes(x=Size, y=SumModifyPSumReCheck, colour=paste("SumModifyPSumReCheck"), linetype=paste(Tool))) +
            geom_point(aes(x=Size, y=SumModifyPSumReCheck, shape =paste("SumModifyPSumReCheck")), size=5) +
    geom_line(size=1.5,aes(x=Size, y=SumReCheck, colour=paste("SumReCheck"), linetype=paste(Tool))) +
            geom_point(aes(x=Size, y=SumReCheck, shape =paste("SumReCheck")), size=5) +
    #      geom_errorbar(aes(ymin=MinRead, ymax=MaxRead), width=.07) +
    scale_x_log10(limits=c(1,4096),
                  breaks = params[["sizeNodesEdges"]]$Size,
                  labels = xticks[["labels"]]) +
    scale_y_log10( limits=c(minValue,maxValue),
                   breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/4)),3),
                   labels = formatC(round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/4)),3),digits=2,format="f")
    ) + 
    #xlab(xticks[["title"]]) + 
    xlab(paste("\n", xticks[["title"]], sep="")) + 
    ylab(paste(yticks[["title"]], "\n", sep="")) + 
    #ggtitle(title) +
    theme(text=element_text(family="NimbusSan", size=32),
          panel.background = element_rect(fill="#FFFFFF"),
          panel.grid.major = element_line(size=0.5, colour="#7B7B7B"),
          #panel.grid.minor = element_line(size=0.25, colour="#C4C4C4"),
          axis.text.x = element_text(colour="black"),
          axis.text.y = element_text(colour="black"),
          axis.title.x = element_text(vjust=2),
          axis.title.y = element_text(vjust=0.6),
          legend.position="none",  # right, none
          legend.title=element_blank(),
          legend.key.width = unit(3, "char"),  # right, none
          legend.text.align = 0
          #legend.key.height = unit(3, "char"),  # right, none
          #legend.text.align = 1,
          #legend.direction = "vertical",
          #legend.margin = unit(5, "char"),  # right, none
          #legend.text = element_text(hjust=0.8)  # right, none
    )
  #out <- NULL
  #out <- c(out, knit_child('diagPaste.Rhtml'))
  #paste(out, collapse = '\n')
  
  anchor <- paste("Combo", "_", scenario, "_",query, sep="")
  #cat("<div>")   # Workaround to start <div> with generated </div>
  print(plot)     # print and cat overlap...
  #cat("</div>")  # Workaround to end </div> with generated <div>
  cat(paste("\n<a name=", toAnchor(anchor),"></a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePDF,"\">[PDF]</a>\n", sep=""))
  cat(paste("\n<a href=\"",fnamePNG,"\">[PNG]</a><br>\n", sep=""))
  
  
  ggsave(plot, filename=fnamePDF, width=11, height=5, dpi=92)
  embedFonts(file=fnamePDF)
  ggsave(plot, filename=fnamePNG, width=18, height=7, dpi=92)
  
}

