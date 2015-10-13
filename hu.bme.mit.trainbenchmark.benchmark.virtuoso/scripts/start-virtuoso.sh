#!/bin/bash

sudo service virtuoso-opensource-6.1 stop
sudo rm -rf /var/lib/virtuoso-opensource-6.1/db/*
sudo service virtuoso-opensource-6.1 start
