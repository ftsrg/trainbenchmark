SET @route := ?;
SET @sensor := ?;

-- the "Sensor.route" attribute is the inverse of the "Route.requires" edge
UPDATE Sensor
SET route = NULL
WHERE route = @route
	AND id = @sensor;
