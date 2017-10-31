#!/bin/bash

PG_CSV_DIR=${1:-$(pwd)/../models/railway-inject-1-csv}
DB_NAME=${2:-trainbenchmark}
PG_USER=${3:-$USER}

/usr/bin/dropdb $DB_NAME -U $PG_USER
/usr/bin/createdb $DB_NAME -U $PG_USER --template template0 -l "C"
/usr/bin/psql -d $DB_NAME -U $PG_USER -a -f schema.sql
(cat tb-load.sql | sed "s|PATHVAR|$PG_CSV_DIR|g"; echo "\q\n") | /usr/bin/psql -d $DB_NAME -U $PG_USER
