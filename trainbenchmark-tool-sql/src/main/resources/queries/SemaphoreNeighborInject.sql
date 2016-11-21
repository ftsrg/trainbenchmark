SELECT DISTINCT id AS route, entry AS semaphore
FROM Route
WHERE entry != 0;
