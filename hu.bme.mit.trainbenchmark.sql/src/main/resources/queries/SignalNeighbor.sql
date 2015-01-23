SELECT DISTINCT Route1.id

FROM Route AS Route1

INNER JOIN Route_routeDefinition AS Route_routeDefinition1
ON Route1.id = Route_routeDefinition1.Route_id

INNER JOIN TrackElement_sensor AS TrackElement_sensor1
ON Route_routeDefinition1.Sensor_id = TrackElement_sensor1.Sensor_id

INNER JOIN TrackElement_connectsTo
ON TrackElement_sensor1.TrackElement_id = TrackElement_connectsTo.TrackElement_id

INNER JOIN TrackElement_sensor AS TrackElement_sensor2
ON TrackElement_connectsTo.TrackElement_id_connectsTo = TrackElement_sensor2.TrackElement_id

INNER JOIN Route_routeDefinition AS Route_routeDefinition2
ON Route_routeDefinition2.Sensor_id = TrackElement_sensor2.Sensor_id

WHERE NOT EXISTS
	(SELECT DISTINCT

	Route.id AS xRoute2,
	Route.Route_entry AS xSig,
	Route_routeDefinition.Sensor_id AS xSen2

	FROM
	Route

	INNER JOIN Route_routeDefinition
	ON Route_routeDefinition.Route_id = Route.id

	WHERE 1=1
	  AND Route.Route_entry != 0
	  AND Route_routeDefinition.Sensor_id = TrackElement_sensor2.Sensor_id
	  AND Route.Route_entry = Route1.Route_exit
	)
AND Route1.id != Route_routeDefinition2.Route_id;
