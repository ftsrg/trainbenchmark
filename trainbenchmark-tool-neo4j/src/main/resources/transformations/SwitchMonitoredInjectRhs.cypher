WITH $sw AS sw
MATCH (sw)-[m:monitoredBy]->(:Sensor)
DELETE m
