SELECT DISTINCT
  Route1.exit AS semaphore,
  Route1.id AS route1,
  Route2.id AS route2,
  TE1.sensor AS sensor1,
  TE2.sensor AS sensor2,
  TE1.id AS te1,
  TE2.id AS te2

-- route1
FROM Route AS Route1

INNER JOIN definedBy AS definedBy1
ON Route1.id = definedBy1.Route_id

-- sensor1
INNER JOIN TrackElement AS TE1
ON definedBy1.Sensor_id = TE1.sensor

-- te1
INNER JOIN connectsTo
ON TE1.id = connectsTo.TrackElement_id

-- te2
INNER JOIN TrackElement AS TE2
ON connectsTo.TrackElement_id_connectsTo = TE2.id

-- sensor2
INNER JOIN definedBy AS definedBy2
ON definedBy2.Sensor_id = TE2.sensor

INNER JOIN Route AS Route2
ON Route2.id = definedBy2.Route_id

WHERE 1=1
  AND Route1.id != definedBy2.Route_id
  AND Route1.exit IS NOT NULL -- semaphore
  AND (Route2.entry IS NULL OR Route2.entry != Route1.exit);
