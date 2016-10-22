INSERT OR REPLACE INTO Variables VALUES ('region',
    (SELECT region
    FROM TrackElement
    WHERE id = (SELECT Value FROM Variables WHERE Name = 'switch'))
  );

-- insert a (sensor) vertex and retrieve its id
INSERT INTO Sensor (region) VALUES (
  (SELECT Value FROM Variables WHERE Name = 'region')
);

INSERT INTO monitoredBy (TrackElement_id, Sensor_id)
VALUES (
  (SELECT Value FROM Variables WHERE Name = 'switch'),
  (SELECT MAX(id) FROM Sensor)
);
