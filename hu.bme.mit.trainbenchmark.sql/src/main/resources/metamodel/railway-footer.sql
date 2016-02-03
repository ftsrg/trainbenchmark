
COMMIT;
CREATE INDEX segment_length_idx ON "Segment" ("length");
CREATE INDEX monitoredBy_idx ON "monitoredBy" ("Sensor_id", "TrackElement_id");
CREATE INDEX connectsTo_idx1 ON "connectsTo" ("TrackElement1_id");
CREATE INDEX connectsTo_idx2 ON "connectsTo" ("TrackElement2_id");
