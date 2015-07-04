SET @switch := ?;

DELETE Sensor 
FROM Sensor 
INNER JOIN TrackElement ON Sensor.id = TrackElement.sensor 
WHERE TrackElement.id = @switch;

DELETE
FROM TrackElement 
WHERE id = @switch;

DELETE definedBy 
FROM definedBy 
INNER JOIN TrackElement ON definedBy.sensor_id = TrackElement.sensor 
WHERE TrackElement.id = @switch;
