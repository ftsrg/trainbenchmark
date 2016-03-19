INSERT INTO Sensor VALUES ();
SET @sensor :=
    (SELECT LAST_INSERT_ID());

UPDATE TrackElement
SET sensor = @sensor
WHERE id = ?;
