DELETE FROM requires
WHERE Route_id  = (SELECT Value FROM Variables WHERE Name = 'route')
  AND Sensor_id = (SELECT Value FROM Variables WHERE Name = 'sensor');
