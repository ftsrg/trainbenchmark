#*******************************************************************************
# Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   Benedek Izso - initial API and implementation
#   Gabor Szarnyas - initial API and implementation
#*******************************************************************************
#!/bin/bash

cd "$1"/deps

sudo add-apt-repository ppa:cwchien/gradle -y
sudo apt-get update
sudo apt-get install -y gradle
gradle -v

git clone --depth 1 https://github.com/nigelsmall/geoff.git
cd geoff
gradle install
cd ..	

git clone --depth 1 https://github.com/jexp/neo4j-shell-tools
cd neo4j-shell-tools
mvn clean install -DskipTests
