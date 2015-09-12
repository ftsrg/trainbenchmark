SELECT DISTINCT
    te1.sensor AS sensor,
    ct1.TrackElement1 AS segment1,
    ct2.TrackElement1 AS segment2,
    ct3.TrackElement1 AS segment3,
    ct4.TrackElement1 AS segment4,
    ct5.TrackElement1 AS segment5,
    ct5.TrackElement2 AS segment6
FROM Segment
INNER JOIN connectsTo as ct1 ON Segment.id = ct1.TrackElement1
INNER JOIN connectsTo as ct2 ON ct1.TrackElement2 = ct2.TrackElement1
INNER JOIN connectsTo as ct3 ON ct2.TrackElement2 = ct3.TrackElement1
INNER JOIN connectsTo as ct4 ON ct3.TrackElement2 = ct4.TrackElement1
INNER JOIN connectsTo as ct5 ON ct4.TrackElement2 = ct5.TrackElement1
INNER JOIN TrackElement as te1 ON Segment.id = te1.id
INNER JOIN TrackElement as te2 ON ct5.TrackElement2 = te2.id
WHERE te1.sensor = te2.sensor;
