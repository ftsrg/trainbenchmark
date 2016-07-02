#!/bin/bash

# trusty for 14.04, utopic for 14.10, vivid for 15.04
DISTRIBUTION=trusty
MIRROR=http://cran.rapporter.net

sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E084DAB9
sudo echo "deb $MIRROR/bin/linux/ubuntu $DISTRIBUTION/" >> /etc/apt/sources.list
sudo apt-get update
sudo apt-get install -y r-base r-base-dev
