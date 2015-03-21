SELECT DISTINCT Sensor.id
FROM Sensor INNER JOIN TrackElement   ON TrackElement.sensor = Sensor.id
            INNER JOIN Switch         ON TrackElement.id = Switch.id
            INNER JOIN SwitchPosition ON SwitchPosition.switch = Switch.id
            INNER JOIN Route          ON Route.id = SwitchPosition.follows
       LEFT OUTER JOIN definedBy      ON 1 = 1
                                     AND definedBy.Route_id = Route.id
                                     AND definedBy.Sensor_id = Sensor.id
WHERE definedBy.Sensor_id IS NULL;

