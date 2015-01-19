oldConfig <- c(
  "EclipseOCL"     = "Eclipse OCL",
  "EMF-IncQuery"   = "EMF-IncQuery",
  "Java"           = "Java",
  "Drools"         = "Drools",
  "Sesame"         = "Sesame",
  "Neo4j"          = "Neo4j",
  "Neo4j-ramdisk"  = "Neo4j RAM",
  "4store"         = "4store",
  "4store-ramdisk" = "4store RAM",
  "IncQuery-D"     = "IncQuery-D",
  "MySQL"          = "MySQL"
)


newTools <- levels(dataTB_orig$Tool)

for (tool in newTools) {
  if( ! tool %in% toolNames) {
    # toolNames
    toolNames <- c(toolNames, tool)
    
    # toolTitles
    if ( ! is.na(oldConfig[tool])) {
      toolTitles <- c(toolTitles, oldConfig[tool][[1]])
    } else {
      toolTitles <- c(toolTitles, tool)
    }
    
    # color and shape
    toolColor[[tool]] <- stringToColor(tool)
    toolShape[[tool]] <- hashString(tool) %% 15
    
    
    drawTools <- c(drawTools, tool)
  }
}

toolTitles <- gsub("_", " ", toolTitles)