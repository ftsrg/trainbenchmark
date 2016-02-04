SET @segment1 := ?;

-- get (segment3) node
SET @segment3 :=
	(SELECT TrackElement2
	FROM connectsTo
	WHERE TrackElement1 = @segment1);

-- get (sensor) node
SET @sensor :=
	(SELECT sensor
	FROM TrackElement
	WHERE id = @segment3);

-- delete (segment1)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo
WHERE TrackElement1 = @segment1 AND TrackElement2 = @segment3;

-- insert new node (segment2) as a TrackElement and retrieve its id
-- also insert the (segment2)-[:sensor]->(sensor) edge
INSERT INTO TrackElement (sensor)
VALUES (@sensor);
SET @segment2 :=
	(SELECT LAST_INSERT_ID());

-- insert (segment2) node as a Segment
INSERT INTO Segment (id)
VALUES (@segent2);

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo
VALUES (@segment1, @segment2);

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo
VALUES (@segment2, @segment3);

-- insert (segment2)-[:monitoredBy]->(sensor) edge
INSERT INTO monitoredBy
VALUES (@segment2, @sensor);
