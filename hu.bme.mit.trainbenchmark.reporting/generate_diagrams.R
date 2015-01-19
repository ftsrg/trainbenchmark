library("jsonlite", quietly=T, verbose=F, warn.conflicts=FALSE)
library("ggplot2",  quietly=T, verbose=F, warn.conflicts=FALSE)
library("plyr",  quietly=T, verbose=F, warn.conflicts=FALSE)
source("functions.R")


# convert the 3-dimensional nested list (results) to 2-dimensional as deleting the unnecessary values
pre_process <-function(results){ 
  for (list in 1:length(results)){
    for(element in 1:length(results[[list]])){
      if (length(results[[list]][[element]]) > 1){
          for(value in length(results[[list]][[element]]):2){
            results[[list]][[element]] <-  results[[list]][[element]][- value] #delete elements except the first
          }
        }
      }
    }
  return(results)
}


save_plot <-function(table, column, title, file_name){
  artifacts <- get_artifacts(table)
  minValue <- 0
  maxValue <- 0
  minValue <- min(table[[column]])
  maxValue <- max(table[[column]])
  plot <- ggplot(table,aes_string(x = "artifactSize", y = column)) +
    geom_line(aes(group = Tools, colour=Tools)) + 
    geom_point(aes(shape = Tools, colour=Tools)) + 
    scale_y_log10(breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),7), 
                  labels = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),2)
                  ) + 
    ylab("Time (ms)") +
    xlab("Size") +
    ggtitle(label = title) +
    scale_x_log10(breaks = c(artifacts), labels = c(artifacts)) #+
    #theme(text=element_text(family="Helvetica", size=16),
    #   panel.background = element_rect(fill="#FFFFFF"),
    #   panel.grid.major = element_line(size=0.3, colour="#333333"),
    #   panel.grid.minor = element_line(size=0.15, colour="#CCCCCC"),
    #   axis.text.x = element_text(colour="black"),
    #   axis.text.y = element_text(colour="black"),
    #  legend.position="right"
    #) #+
    #geom_text(aes_string(label=column))
  ggsave(plot,file = file_name, width=14, height=7, dpi=192)
  print(file_name)
}


results <-load_results()
results <- pre_process(results)
table <- do.call(rbind.data.frame,results)
table <- change_time_metrics(table, 10**6)
colnames(table)[which(colnames(table) == "tool")] <- "Tools"

#queries <- unique(table$query)
scenarios <- unique(table$scenario)
path <- c("../diagrams/")
if (file.exists(path) == FALSE){
  dir.create(path)
}
for(s in scenarios){
  queries <- unique(subset(table, scenario==s)$query)
  for(q in queries){
    
    subtable <- ddply(table, c("Tools","scenario", "query", "artifactSize"), summarise, 
                      batchTime = readTime+checkTimes)
    subtable <- ddply(subtable, c("Tools","scenario", "query", "artifactSize"), summarise, 
                      batchTime = median(batchTime))
    subtable <- subset(subtable,query==q & scenario==s)
    file_name <- paste(path,"BatchValidation-",s,"-",q,".png", sep = "")
    title <- paste("Batch validation time ",q," (x,y:logscale), ",s, sep = "")
    save_plot(subtable, c("batchTime"), title, file_name)
    
    subtable <-ddply(table, c("Tools","scenario", "query", "artifactSize"), summarise, 
                     revalidation = checkTimeSum-checkTimes + editTimeSum-editTimes)
    subtable <-ddply(subtable, c("Tools","scenario", "query", "artifactSize"), summarise, 
                     revalidation = median(revalidation))
    subtable <- subset(subtable, query==q & scenario==s)
    file_name <- paste(path,"Revalidation-",s,"-",q,".png", sep="")
    title <- paste("Total revalidation time ",q," (x,y:logscale) ",s, sep ="")
    save_plot(subtable, c("revalidation"), title, file_name)
    
    subtable <-ddply(table, c("Tools","scenario", "query", "artifactSize"), summarise, 
                     totalTime = checkTimeSum + editTimeSum + readTime)
    subtable <-ddply(subtable, c("Tools","scenario", "query", "artifactSize"), summarise, 
                     totalTime = median(totalTime))
    subtable <- subset(subtable, query==q & scenario==s)
    file_name <- paste(path,"TotalTime-",s,"-",q,".png", sep = "")
    title <- paste("Total time of the phases ",q," (x,y:logscale) ",s, sep = "")
    save_plot(subtable, c("totalTime"), title, file_name)
  }
}
