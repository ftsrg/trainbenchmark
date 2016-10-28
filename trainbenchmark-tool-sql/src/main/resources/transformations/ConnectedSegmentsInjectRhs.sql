SET @sensor := ?;
SET @segment1 := ?;
SET @segment3 := ?;

-- get (region) node
SET @region :=
    (SELECT region
    FROM TrackElement
    WHERE id = @segment1);

-- delete (segment1)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo
WHERE TrackElement1_id = @segment1 AND TrackElement2_id = @segment3;

-- insert new node (segment2) as a TrackElement and retrieve its id
INSERT INTO TrackElement (region) VALUES (@region);

SET @segment2 := (SELECT LAST_INSERT_ID());

-- insert (segment2) node as a Segment
INSERT INTO Segment (id)
VALUES (@segment2);

-- insert (segment1)-[:connectsTo]->(segment2) edge
INSERT INTO connectsTo
VALUES (@segment1, @segment2);

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo
VALUES (@segment2, @segment3);

-- insert (segment2)-[:monitoredBy]->(sensor) edge
INSERT INTO monitoredBy
VALUES (@segment2, @sensor);
