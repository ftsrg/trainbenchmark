PlotSettings <- setClass(
  "PlotSettings",
  
  slots = c(
             xDimension = "character",
             yDimension = "character",
             group = "character",
             xLabel = "character",
             yLabel = "character",
             title = "character",
             xAxis = "character",
             yAxis = "character"
           ),
  
  prototype=list(
                 xDimension = "",
                 yDimension = "",
                 group = "",
                 xLabel = "",
                 yLabel = "",
                 title = "",
                 xAxis = "",
                 yAxis = ""
                )
  )

setGeneric(name="setDimensions",
           def=function(theObject, xDimension, yDimension){
             standardGeneric("setDimensions")
           }
)

setMethod(f="setDimensions",
          signature="PlotSettings",
          definition=function(theObject, xDimension, yDimension){
            theObject@xDimension <- xDimension
            theObject@yDimension <- yDimension
            return(theObject)
          })

setGeneric(name="setGroup",
           def=function(theObject, group){
             standardGeneric("setGroup")
           }
)

setMethod(f="setGroup",
          signature="PlotSettings",
          definition=function(theObject, group){
            theObject@group <- group
            return(theObject)
          })

setGeneric(name="setTitle",
           def=function(theObject, title){
             standardGeneric("setTitle")
           }
)

setMethod(f="setTitle",
          signature="PlotSettings",
          definition=function(theObject, title){
            theObject@title <- title
            return(theObject)
          })

setGeneric(name="setLabels",
           def=function(theObject, xLabel, yLabel){
                standardGeneric("setLabels")
           }
           )

setMethod(f="setLabels",
          signature="PlotSettings",
          definition=function(theObject, xLabel, yLabel){
            theObject@xLabel <- xLabel
            theObject@yLabel <- yLabel
            return(theObject)
          })

setGeneric(name="setAxis",
           def=function(theObject, xAxis, yAxis){
             standardGeneric("setAxis")
           }
)

setMethod(f="setAxis",
          signature="PlotSettings",
          definition=function(theObject, xAxis, yAxis){
            theObject@xAxis <- xAxis
            theObject@yAxis <- yAxis
            return(theObject)
          })
