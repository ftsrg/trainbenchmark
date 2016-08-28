MATCH (sw), (swP)
WHERE id(sw) = { sw }
  AND id(swP) = { swP }
SET sw.currentPosition = swP.position
