nice_y_axis = function() {
  options(scipen=999)

  # y axis labels
  longticks = c(T, F, F, T, F, F, F, F, T)
  shortticks = 2:10
  range = -6:6
  
  ooms = 10^range
  
  ybreaks = as.vector(shortticks %o% ooms)
  ylabels = as.character(ybreaks * longticks)
  ylabels = gsub("^0$", "", ylabels)
  
  list(ybreaks = ybreaks, ylabels = ylabels)
}

get_extremes = function(df, phases) {
  phase.rows.extremes = NULL
  for (phase in phases) {
    phase.rows = df[df$Phase == phase, ]
    phase.rows.current.extremes = phase.rows[phase.rows$Time == min(phase.rows$Time) | phase.rows$Time == max(phase.rows$Time), ]
    phase.rows.extremes = rbind(phase.rows.extremes, phase.rows.current.extremes)
  }
  phase.rows.extremes
}

create_extremes_for_facets = function(extremes, phases) {
  # include the original phase
  facet.extremes = extremes
  
  # include the phases specified in phase
  for (phase in phases) {
    phase.extremes = extremes
    phase.extremes$Phase = phase
    facet.extremes = rbind(facet.extremes, phase.extremes)
  }
  facet.extremes
}

# this is required for some descriptions, because F will become False :-)
keep_descriptions_first_char = function(data) {
  data$Description = sapply(
    data$Description,
    function(i) substr(as.character(i),1,1)
  )
  data
}

model_filenames_to_sizes = function(data) {
  data$Model = gsub("\\D+", "", data$Model)
  data$Model = as.numeric(data$Model)
  data
}
