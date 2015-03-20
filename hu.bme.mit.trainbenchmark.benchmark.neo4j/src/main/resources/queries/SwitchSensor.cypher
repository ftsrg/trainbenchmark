MATCH (sw:Switch)
WHERE NOT (sw-[:sensor]->(:Sensor))
RETURN DISTINCT sw
