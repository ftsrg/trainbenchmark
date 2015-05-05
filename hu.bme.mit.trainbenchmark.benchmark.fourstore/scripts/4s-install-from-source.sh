#*******************************************************************************
# Copyright (c) 2010-2014, Gabor Szarnyas, Istvan Rath and Daniel Varro
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
# Gabor Szarnyas - initial API and implementation
#*******************************************************************************
#!/bin/bash
# installs 4store's dependencies, compiles and installs 4store

# prompting for sudo password if necessary
sudo echo > /dev/null

mkdir 4s-install
cd 4s-install

FOURSTORE_VERSION=1.1.5
RAPTOR_VERSION=2.0.6
RASQAL_VERSION=0.9.28

wget -nc http://download.librdf.org/source/raptor2-$RAPTOR_VERSION.tar.gz
wget -nc http://download.librdf.org/source/rasqal-$RASQAL_VERSION.tar.gz
wget -nc http://4store.org/download/4store-v$FOURSTORE_VERSION.tar.gz

tar -xf raptor2-$RAPTOR_VERSION.tar.gz 
tar -xf rasqal-$RASQAL_VERSION.tar.gz 
tar -xf 4store-v$FOURSTORE_VERSION.tar.gz 

# dependencies
sudo apt-get install -y gcc make libxml2-dev uuid-dev avahi-discover libnss-mdns build-essential libpcre3-dev libtool libglib2.0-dev ncurses-dev libreadline-dev libavahi-client-dev libavahi-glib-dev

cd raptor2-$RAPTOR_VERSION
./configure
make
sudo make install

cd ..
cd rasqal-$RASQAL_VERSION
./configure
make
sudo make install

sudo ldconfig
cd ..
cd 4store-v$FOURSTORE_VERSION
./configure 
make
sudo make install
make test

