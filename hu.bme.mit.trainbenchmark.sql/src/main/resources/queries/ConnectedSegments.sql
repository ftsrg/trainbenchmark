SELECT DISTINCT
    te1.sensor AS sensor,
    ct1.TrackElement_id AS segment1,
    ct2.TrackElement_id AS segment2,
    ct3.TrackElement_id AS segment3,
    ct4.TrackElement_id AS segment4,
    ct5.TrackElement_id AS segment5,
    ct6.TrackElement_id AS segment6
FROM Segment
INNER JOIN connectsTo as ct1 ON Segment.id = ct1.TrackElement_id
INNER JOIN connectsTo as ct2 ON ct1.TrackElement_id_connectsTo = ct2.TrackElement_id
INNER JOIN connectsTo as ct3 ON ct2.TrackElement_id_connectsTo = ct3.TrackElement_id
INNER JOIN connectsTo as ct4 ON ct3.TrackElement_id_connectsTo = ct4.TrackElement_id
INNER JOIN connectsTo as ct5 ON ct4.TrackElement_id_connectsTo = ct5.TrackElement_id
INNER JOIN connectsTo as ct6 ON ct5.TrackElement_id_connectsTo = ct6.TrackElement_id
INNER JOIN TrackElement as te1 ON Segment.id = te1.id
INNER JOIN TrackElement as te2 ON ct6.TrackElement_id = te2.id
WHERE te1.sensor = te2.sensor;
