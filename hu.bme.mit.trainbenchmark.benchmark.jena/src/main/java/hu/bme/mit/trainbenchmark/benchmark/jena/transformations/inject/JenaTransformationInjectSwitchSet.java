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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.inject;

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.CURRENTPOSITION;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.constants.Position;
import hu.bme.mit.trainbenchmark.rdf.RDFHelper;

import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaTransformationInjectSwitchSet extends JenaTransformationInject {

	public JenaTransformationInjectSwitchSet(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> switches) {
		final Model model = jenaDriver.getModel();
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
			final Resource object = oldStatement.getObject().asResource();
			final String currentPositionRDFString = object.getLocalName();
			final String currentPositionString = RDFHelper.removePrefix(Position.class, currentPositionRDFString);
			final Position currentPosition = Position.valueOf(currentPositionString);
			final Position newCurrentPosition = Position.values()[(currentPosition.ordinal() + 1) % Position.values().length];
			final String newCurrentPositionString  = RDFHelper.addEnumPrefix(newCurrentPosition);

			// set new value			
			final Statement newStatement = model.createLiteralStatement(sw, currentPositionProperty, newCurrentPositionString);
			model.add(newStatement);

		}
	}

}
