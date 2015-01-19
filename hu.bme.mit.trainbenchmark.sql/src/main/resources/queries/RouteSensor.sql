SELECT DISTINCT Sensor.id
FROM Sensor INNER JOIN TrackElement_sensor   ON TrackElement_sensor.Sensor_id = Sensor.id
            INNER JOIN Switch                ON Switch.TrackElement_id = TrackElement_sensor.TrackElement_id
            INNER JOIN SwitchPosition        ON SwitchPosition.SwitchPosition_switch = Switch.TrackElement_id
            INNER JOIN Route                 ON Route.id = SwitchPosition.Route_switchPosition
       LEFT OUTER JOIN Route_routeDefinition ON 1 = 1
                                            AND Route_routeDefinition.Route_id = Route.id
                                            AND Route_routeDefinition.Sensor_id = Sensor.id
WHERE Route_routeDefinition.Sensor_id IS NULL;
