/*******************************************************************************
 * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;

import java.util.Collection;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Selector;
import org.apache.jena.rdf.model.SimpleSelector;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

public class JenaTransformationInjectSwitchSet extends JenaTransformationInject {

	public JenaTransformationInjectSwitchSet(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> switches) {
		final Model model = driver.getModel();
		final Property currentPositionProperty = model.getProperty(BASE_PREFIX + CURRENTPOSITION);

		for (final Resource sw : switches) {
			final Selector selector = new SimpleSelector(sw, currentPositionProperty, (RDFNode) null);
			final StmtIterator statementsToRemove = model.listStatements(selector);
			if (!statementsToRemove.hasNext()) {
				continue;

			}

			// delete old statement
			final Statement oldStatement = statementsToRemove.next();
			model.remove(oldStatement);

			// get next enum value
			final Resource currentPositionResource = oldStatement.getObject().asResource();
			final String currentPositionRDFString = currentPositionResource.getLocalName();
			final String currentPositionString = RDFHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString = RDFHelper.addEnumPrefix(newCurrentPosition);
			final Resource newCurrentPositionResource = model.createResource(BASE_PREFIX + newCurrentPositionString);

			// set new value
			final Statement newStatement = model.createLiteralStatement(sw, currentPositionProperty, newCurrentPositionResource);
			model.add(newStatement);

		}
	}

}
