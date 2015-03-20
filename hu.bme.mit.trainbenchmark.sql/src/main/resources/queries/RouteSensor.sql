SELECT DISTINCT Sensor.id
FROM Sensor INNER JOIN sensor         ON sensor.Sensor_id = Sensor.id
            INNER JOIN Switch         ON Switch.id = sensor.TrackElement_id
            INNER JOIN SwitchPosition ON SwitchPosition.switch = Switch.id
            INNER JOIN Route          ON Route.id = SwitchPosition.follows
       LEFT OUTER JOIN definedBy      ON 1 = 1
                                     AND definedBy.Route_id = Route.id
                                     AND definedBy.Sensor_id = Sensor.id
WHERE definedBy.Sensor_id IS NULL;

