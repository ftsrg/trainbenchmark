#!/bin/bash

# based on https://www.digitalocean.com/community/tutorials/how-to-set-up-r-on-ubuntu-14-04

# change trusty (14.04) to xenial for 16.04
sudo sh -c 'echo "deb http://cran.rstudio.com/bin/linux/ubuntu trusty/" >> /etc/apt/sources.list'
gpg --keyserver keyserver.ubuntu.com --recv-key E084DAB9
gpg -a --export E084DAB9 | sudo apt-key add -

sudo apt-get update
sudo apt-get install -y r-base r-base-dev
