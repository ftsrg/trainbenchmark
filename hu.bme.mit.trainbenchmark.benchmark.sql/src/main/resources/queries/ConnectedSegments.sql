SELECT DISTINCT
    mb1.Sensor_id AS sensor,
    ct1.TrackElement1_id AS segment1,
    ct2.TrackElement1_id AS segment2,
    ct3.TrackElement1_id AS segment3,
    ct4.TrackElement1_id AS segment4,
    ct5.TrackElement1_id AS segment5,
    ct5.TrackElement2_id AS segment6
FROM Segment
INNER JOIN connectsTo as ct1 ON Segment.id = ct1.TrackElement1_id
INNER JOIN connectsTo as ct2 ON ct1.TrackElement2_id = ct2.TrackElement1_id
INNER JOIN connectsTo as ct3 ON ct2.TrackElement2_id = ct3.TrackElement1_id
INNER JOIN connectsTo as ct4 ON ct3.TrackElement2_id = ct4.TrackElement1_id
INNER JOIN connectsTo as ct5 ON ct4.TrackElement2_id = ct5.TrackElement1_id
INNER JOIN monitoredBy as mb1 ON mb1.TrackElement_id = ct1.TrackElement1_id -- segment1
INNER JOIN monitoredBy as mb2 ON mb2.TrackElement_id = ct5.TrackElement2_id -- segment6
WHERE mb1.Sensor_id = mb2.Sensor_id;
