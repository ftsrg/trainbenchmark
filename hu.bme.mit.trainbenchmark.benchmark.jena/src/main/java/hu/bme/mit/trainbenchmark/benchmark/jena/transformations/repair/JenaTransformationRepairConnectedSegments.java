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

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.railway.RailwayModelConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class JenaTransformationRepairConnectedSegments extends JenaTransformationRepair<JenaConnectedSegmentsMatch> {

	public JenaTransformationRepairConnectedSegments(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<JenaConnectedSegmentsMatch> matches) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property connectsToProperty = model.getProperty(BASE_PREFIX + RailwayModelConstants.LENGTH);

		for (final JenaConnectedSegmentsMatch match : matches) {
			final Resource segment2 = match.getSegment2();

			// delete segment2 by removing all (segment2, _, _) and (_, _, segment2) triples
			final Collection<Statement> statementsToRemove = new ArrayList<>();

			final Selector selectorOutgoingEdges = new SimpleSelector(segment2, null, (RDFNode) null);
			final StmtIterator statementsOutgoingEdges = model.listStatements(selectorOutgoingEdges);
			while (statementsOutgoingEdges.hasNext()) {
				statementsToRemove.add(statementsOutgoingEdges.next());
			}
			final Selector selectorIncomingEdges = new SimpleSelector(null, null, segment2);
			final StmtIterator statementsIncomingEdges = model.listStatements(selectorIncomingEdges);
			while (statementsIncomingEdges.hasNext()) {
				statementsToRemove.add(statementsIncomingEdges.next());
			}
			for (final Statement statement : statementsToRemove) {
				model.remove(statement);
			}

			// insert (segment1)-[:connectsTo]->(segment3) edge
			model.add(model.createStatement(match.getSegment1(), connectsToProperty, match.getSegment3()));
		}
	}

}
