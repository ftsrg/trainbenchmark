MATCH (sw {id: $sw}), (swP {id: $swP})
SET sw.currentPosition = swP.position
