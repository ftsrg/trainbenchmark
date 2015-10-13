#!/bin/bash

sudo service mysql restart
echo "SET GLOBAL max_heap_table_size=1073741824;" | mysql -u root
