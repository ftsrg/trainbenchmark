-- get (region) node
INSERT OR REPLACE INTO Variables VALUES ('region',
    (SELECT region
    FROM TrackElement
    WHERE id = (SELECT Value FROM Variables WHERE Name = 'segment1'))
  );

-- delete (segment1)-[:connectsTo]->(segment3) edge
DELETE FROM connectsTo
WHERE
  TrackElement1_id = (SELECT Value FROM Variables WHERE Name = 'segment1') AND
  TrackElement2_id = (SELECT Value FROM Variables WHERE Name = 'segment3');

-- insert new node (segment2) as a TrackElement and retrieve its id
INSERT INTO TrackElement (region) VALUES (
    (SELECT Value FROM Variables WHERE Name = 'region')
  );

INSERT OR REPLACE INTO Variables VALUES ('segment2',
    (SELECT MAX(id) FROM TrackElement)
  );

-- insert (segment2) node as a Segment
INSERT INTO Segment (id)
VALUES (
  (SELECT Value FROM Variables WHERE Name = 'segment2')
);

-- insert (segment1)-[:connectsTo]->(segment2) edge
INSERT INTO connectsTo
VALUES (
  (SELECT Value FROM Variables WHERE Name = 'segment1'),
  (SELECT Value FROM Variables WHERE Name = 'segment2')
);

-- insert (segment1)-[:connectsTo]->(segment3) edge
INSERT INTO connectsTo
VALUES (
  (SELECT Value FROM Variables WHERE Name = 'segment2'),
  (SELECT Value FROM Variables WHERE Name = 'segment3')
);

-- insert (segment2)-[:monitoredBy]->(sensor) edge
INSERT INTO monitoredBy
VALUES (
  (SELECT Value FROM Variables WHERE Name = 'segment2'),
  (SELECT Value FROM Variables WHERE Name = 'sensor')
);
