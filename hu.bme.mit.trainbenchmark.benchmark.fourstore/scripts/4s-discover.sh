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
HOSTS=`avahi-browse -at | grep IPv4 | grep _4store | cut -d " " -f 6 | cut -d "-" -f 1`
while read -r HOSTS; do
  hostToIP="$HOSTS"; avahi-browse -atr 2> /dev/null | sed -n '/Workstation/,+2p' | sed -n '/IPv4/,+2p' | sed -n '/'$hostToIP'/,+2p' | grep 'address' | cut -d "[" -f 2 | cut -d "]" -f 1
done <<< "$HOSTS"

