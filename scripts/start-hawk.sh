#!/bin/bash

: ${HAWK_DIRECTORY=/home/szarnyasg/mondo/hawk-product/linux.gtk.x86_64}

rm -rf $HAWK_DIRECTORY/eclipse/configuration/org.eclipse.osgi/219
$HAWK_DIRECTORY/eclipse/mondo-server
