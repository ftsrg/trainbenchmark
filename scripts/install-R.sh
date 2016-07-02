#!/bin/bash

# based on
# trusty for 14.04, xenial for 16.04
DISTRIBUTION=trusty

sudo sh -c 'echo "deb http://cran.rstudio.com/bin/linux/ubuntu trusty/" >> /etc/apt/sources.list'
gpg --keyserver keyserver.ubuntu.com --recv-key E084DAB9
gpg -a --export E084DAB9 | sudo apt-key add -

sudo apt-get update
sudo apt-get install -y r-base r-base-dev
