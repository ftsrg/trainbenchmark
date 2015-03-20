SELECT DISTINCT Route1.id

FROM Route AS Route1

INNER JOIN definedBy AS definedBy1
ON Route1.id = definedBy1.Route_id

INNER JOIN sensor AS sensor1
ON definedBy1.Sensor_id = sensor1.Sensor_id

INNER JOIN connectsTo
ON sensor1.TrackElement_id = connectsTo.TrackElement_id

INNER JOIN sensor AS sensor2
ON connectsTo.TrackElement_id_connectsTo = sensor2.TrackElement_id

INNER JOIN definedBy AS definedBy2
ON definedBy2.Sensor_id = sensor2.Sensor_id

WHERE 1=1
	AND Route1.exit IS NOT NULL
	AND NOT EXISTS
	(SELECT DISTINCT

	Route.id AS xRoute2,
	Route.entry AS xSem,
	definedBy.Sensor_id AS xSen2

	FROM
	Route

	INNER JOIN definedBy
	ON definedBy.Route_id = Route.id

	WHERE 1=1
	  AND Route.entry IS NOT NULL
	  AND definedBy.Sensor_id = sensor2.Sensor_id
	  AND Route.entry = Route1.exit
	)
AND Route1.id != definedBy2.Route_id;
