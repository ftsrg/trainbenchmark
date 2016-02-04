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

import java.io.IOException;
import java.util.ArrayList;
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
import hu.bme.mit.trainbenchmark.benchmark.jena.match.JenaConnectedSegmentsMatch;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

public class JenaTransformationRepairConnectedSegments extends JenaTransformationRepair<JenaConnectedSegmentsMatch> {

	public JenaTransformationRepairConnectedSegments(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void performRHS(final Collection<JenaConnectedSegmentsMatch> matches) throws IOException {
		final Model model = driver.getModel();
		final Property connectsToProperty = model.getProperty(BASE_PREFIX + ModelConstants.LENGTH);

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
