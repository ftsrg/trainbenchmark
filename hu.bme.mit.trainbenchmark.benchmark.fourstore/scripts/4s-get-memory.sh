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

(
for i in `ps auxw | grep '[4]s-backend' | awk '{print $2;}'`; do
cat /proc/${i}/status | grep VmRSS | awk '{print $2;}' ;
done
) | awk 'BEGIN{Memory=0;}{Memory += $1;}END{ print Memory; }'
