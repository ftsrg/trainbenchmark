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

sudo mkdir -p /var/lib/4store
sudo chown $USER:$USER /var/lib/4store/

sudo touch /etc/4store.conf
sudo chown $USER:$USER /etc/4store.conf

# for Ubuntu systems
#sudo apt-get install -y software-properties-common
# for Mint 17.1+
#sudo apt-get install -y mintsources
sudo apt-add-repository "deb http://ppa.launchpad.net/yves-raimond/ppa/ubuntu precise main"
sudo apt-add-repository "deb-src http://ppa.launchpad.net/yves-raimond/ppa/ubuntu precise main"
sudo apt-get update
sudo apt-get install -y --force-yes 4store

