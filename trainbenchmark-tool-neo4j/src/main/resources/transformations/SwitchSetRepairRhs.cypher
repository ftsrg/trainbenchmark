MATCH (sw), (swP)
WHERE sw.id = { sw }
  AND swP.id = { swP }
SET sw.currentPosition = swP.position
