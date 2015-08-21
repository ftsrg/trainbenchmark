source("util.R")
usePackage("RJSONIO")
usePackage("ggplot2")
usePackage("plyr")
usePackage("R.oo")
usePackage("igraph")
options(warn = -1)
source("../../mondo-sam/reporting/shiny/plots/Theme.R")
options(warn = 0)

visualizeDistributions <- function(metrics, maxElement, type, theme) {
  if (is.character(maxElement)) {
    maxElement <- as.numeric(maxElement)
  }
  frame <- getFrame(metrics)
  frame$MetricValue <- frame$MetricValue / maxElement
 
  plot <- ggplot(frame, aes(x = MetricName, y = MetricValue)) +
    ylab("") +
    xlab("") +
    ggtitle(type) +
    geom_point() +
    scale_x_continuous() +
    scale_y_log10() +
    theme$getTheme()
  filename <- paste(type, ".png", sep = "")
  ggsave(filename = filename, plot = plot, path = "./", width = 14, 
         height = 7, dpi = 300)
}


data <- loadData()
elements <- initElements(data)
types <- c("Train", "Station", "Schedule")
theme <- Theme()
theme$init()

for (phase in data$PhaseResults) {
  for (metric in (phase[["Metrics"]])) {
    if ("CompositeName" %in% names(metric)) {
      if (metric[["CompositeName"]] %in% types) {
        name <- metric[["CompositeName"]]
        maxElement <- elements[which(elements[["Type"]] == name),][["Count"]]
        visualizeDistributions(metric[["NestedMetrics"]], 
                               maxElement,
                               name,
                               theme)
      }
    }
  }
}
