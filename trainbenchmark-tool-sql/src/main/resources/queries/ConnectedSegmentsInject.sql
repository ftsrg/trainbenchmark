SELECT
    mb1.Sensor_id AS sensor,
    ct.TrackElement1_id AS segment1,
    ct.TrackElement2_id AS segment3
FROM Segment
INNER JOIN connectsTo as ct ON Segment.id = ct.TrackElement1_id
INNER JOIN monitoredBy as mb1 ON mb1.TrackElement_id = ct.TrackElement1_id -- segment1
INNER JOIN monitoredBy as mb2 ON mb2.TrackElement_id = ct.TrackElement2_id -- segment3
WHERE mb1.Sensor_id = mb2.Sensor_id;
