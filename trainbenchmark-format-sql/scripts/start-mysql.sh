#!/bin/bash

sudo service mysql start
echo "SET GLOBAL max_heap_table_size=1073741824;" | mysql -u root
echo "MySQL started"


