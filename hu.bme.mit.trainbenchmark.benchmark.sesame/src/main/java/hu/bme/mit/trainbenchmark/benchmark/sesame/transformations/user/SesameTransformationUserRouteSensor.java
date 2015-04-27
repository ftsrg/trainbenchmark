/*******************************************************************************
 * Copyright (c) 2010-2015, Gabor Szarnyas, Benedek Izso, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.sesame.transformations.user;

import static hu.bme.mit.trainbenchmark.benchmark.Sesame.constants.SesameConstants.relationshipTypeDefinedBy;
import hu.bme.mit.trainbenchmark.benchmark.Sesame.driver.SesameDriver;

import java.util.Collection;

import org.Sesame.graphdb.Node;
import org.Sesame.graphdb.Relationship;

public class SesameTransformationUserRouteSensor extends SesameTransformationUser {

	public SesameTransformationUserRouteSensor(final SesameDriver neoDriver) {
		super(neoDriver);
	}

	@Override
	public void rhs(final Collection<Node> routes) {
		for (final Node route : routes) {
			final Iterable<Relationship> definedBys = route.getRelationships(relationshipTypeDefinedBy);
			for (final Relationship definedBy : definedBys) {
				definedBy.delete();
				break;
			}
		}
	}

}
