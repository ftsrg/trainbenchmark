#!/bin/bash

(
for i in `ps auxw | grep '[4]s-backend' | awk '{print $2;}'`; do
cat /proc/${i}/status | grep VmRSS | awk '{print $2;}' ;
done
for i in `ps auxw | grep '[4]s-httpd' | awk '{print $2;}'`; do
cat /proc/${i}/status | grep VmRSS | awk '{print $2;}' ;
done
) | awk 'BEGIN{Memory=0;}{Memory += $1;}END{ print Memory; }'
