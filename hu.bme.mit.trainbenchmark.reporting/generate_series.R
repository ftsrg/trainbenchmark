library("jsonlite", quietly=T, verbose=F, warn.conflicts=FALSE)
library("ggplot2",  quietly=T, verbose=F, warn.conflicts=FALSE)
library("plyr",  quietly=T, verbose=F, warn.conflicts=FALSE)
source("functions.R")

extract_series <-function(results, times){
  table <- data.frame(times=numeric(), Tool=character(), Size=numeric(), 
                      Scenario=character(), Query=character(), SeriesIndex=numeric(), stringsAsFactors=FALSE)
  tool <- 0
  size <- 0
  scenario <-0
  series <-0
  query <-0
  for (list in 1:length(results)){
    for(element in 1:length(results[[list]])){
      if (names(results[[list]])[element] == "tool"){tool<-results[[list]][element]}
      if (names(results[[list]])[element] == "query"){query<-results[[list]][element]}
      if (names(results[[list]])[element] == "artifactSize"){size<-results[[list]][element]}
      if (names(results[[list]])[element] == "scenario"){scenario<-results[[list]][element]}
      if (names(results[[list]])[element] == "seriesIndex"){series<-results[[list]][element]}
      if (names(results[[list]])[element] == times){
        temp_list <-c(results[[list]][[element]])
      }
    }
    table[sapply(table, is.factor)] <- lapply(table[sapply(table, is.factor)], as.character)
    for(value in temp_list){
      table <- rbind(table,c(Time=value, Tool=tool, Size=size, Scenario=scenario, Query=query, 
                             SeriesIndex=series))
    }
  }
  colnames(table)[which(colnames(table) == "Tool.tool")] <- "Tool"
  colnames(table)[which(colnames(table) == "Size.artifactSize")] <- "Size"
  colnames(table)[which(colnames(table) == "Scenario.scenario")] <- "Scenario"
  colnames(table)[which(colnames(table) == "Query.query")] <- "Query"
  colnames(table)[which(colnames(table) == "SeriesIndex.seriesIndex")] <- "SeriesIndex"
  return(table)
}


create_series_plots <-function(table, query, size, filename, title){
  subtable <- subset(table,SeriesIndex==1 & Scenario == "User" & Query==query & Size==size)
  tools <- unique(subtable$Tool)
  for(t in tools){
    subtable$Id <- seq_len(nrow(subset(subtable, Tool == t)))
  }
  subtable$Time <- subtable$Time / 10**6
  minValue <- min(subtable$Time)
  maxValue <- max(subtable$Time)
  minId <- min(subtable$Id)
  maxId <- max(subtable$Id)
  plot <- ggplot(subtable,aes_string(x = "Id", y = "Time")) +
    geom_line(aes(group=Tool, colour=Tool)) +
    geom_point(aes(shape=Tool, colour=Tool)) +
    scale_y_log10(breaks = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),7), 
                  labels = round(10^seq(log10(minValue), log10(maxValue), by=((log10(maxValue)-log10(minValue))/7)),2)
    ) + 
    scale_x_continuous(breaks = seq(minId, maxId, by=round(maxId/5,0))) +
    ylab("Time (ms)") +
    xlab("Index") +
    ggtitle(label = title)
  ggsave(plot,file = file_name, width=14, height=7, dpi=192)
  print(file_name)
}

results <-load_results()
table <- extract_series(results, "checkTimes")
queries <- unique(table$Query)
path <- c("../diagrams/")

for(query in queries){
  sizes <- unique(subset(table, Query==query)$Size)
  for(size in sizes){
    file_name <- paste(path,"SeriesofCheckTimes-",query,"-",size,".png", sep = "")
    title <- paste("Series of check times - ",query,", size=",size," (y:logscale, x:continuous)")
    create_series_plots(table, query, size, file_name, title)
  }
}


table <- extract_series(results, "editTimes")
queries <- unique(table$Query)
for(query in queries){
  sizes <- unique(subset(table, Query==query)$Size)
  for(size in sizes){
    file_name <- paste(path,"SeriesofEditTimes-",query,"-",size,".png", sep = "")
    title <- paste("Series of edit times - ",query,", size=",size," (y:logscale, x:continuous)")
    create_series_plots(table, query, size, file_name, title)
  }
}

