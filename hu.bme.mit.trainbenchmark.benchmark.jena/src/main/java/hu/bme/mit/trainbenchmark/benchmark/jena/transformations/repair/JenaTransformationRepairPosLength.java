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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.repair;

import static hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaPosLengthMatch;

import java.io.IOException;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaTransformationRepairPosLength extends JenaTransformationRepair<JenaPosLengthMatch> {

	public JenaTransformationRepairPosLength(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaPosLengthMatch> matches) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property lengthProperty = model.getProperty(BASE_PREFIX + LENGTH);

		for (final JenaPosLengthMatch match : matches) {
			final Resource segment = match.getSegment();
			final int length = match.getLength().getInt();
			final int newLength = -length + 1;

			final Selector selector = new SimpleSelector(segment, lengthProperty, (RDFNode) null);
			final StmtIterator statementsToRemove = model.listStatements(selector);

			final Statement oldStatement = statementsToRemove.next();
			model.remove(oldStatement);
			final Statement newStatement = model.createLiteralStatement(segment, lengthProperty, newLength);
			model.add(newStatement);
		}
	}

}
