MATCH (sw:Switch)
WHERE NOT (sw-[:TrackElement_sensor]->(:Sensor))
RETURN DISTINCT sw
