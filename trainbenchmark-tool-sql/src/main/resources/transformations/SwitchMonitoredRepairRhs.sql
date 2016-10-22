SET @switch := ?;
SET @region :=
    (SELECT region FROM TrackElement WHERE id = @switch);

-- insert a (sensor) vertex and retrieve its id
INSERT INTO Sensor (region) VALUES (@region);
SET @sensor :=
    (SELECT LAST_INSERT_ID());

INSERT INTO monitoredBy (TrackElement_id, Sensor_id)
VALUES (@switch, @sensor);
