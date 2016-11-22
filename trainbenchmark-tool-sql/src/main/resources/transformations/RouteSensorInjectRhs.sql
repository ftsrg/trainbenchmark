SET @route  := ?;
SET @sensor := ?;

DELETE FROM requires
WHERE Route_id  = @route
  AND Sensor_id = @sensor;
