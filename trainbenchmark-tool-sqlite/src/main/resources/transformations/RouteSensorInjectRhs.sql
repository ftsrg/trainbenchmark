-- the "Sensor.route" attribute is the inverse of the "Route.requires" edge
UPDATE Sensor
SET route = 0
WHERE id    = (SELECT Value FROM Variables WHERE Name = 'sensor')
  AND route = (SELECT Value FROM Variables WHERE Name = 'route');
