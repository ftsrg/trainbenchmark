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

import static hu.bme.mit.trainbenchmark.constants.ModelConstants.LENGTH;
import static hu.bme.mit.trainbenchmark.rdf.RdfConstants.BASE_PREFIX;

import java.io.IOException;
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
import hu.bme.mit.trainbenchmark.benchmark.jena.matches.JenaVertexMatch;

public class JenaTransformationInjectPosLength extends JenaTransformationInject<JenaVertexMatch> {

	public JenaTransformationInjectPosLength(final JenaDriver driver) {
		super(driver);
	}

	@Override
	public void activate(final Collection<JenaVertexMatch> segments) throws IOException {
		final Model model = driver.getModel();
		final Property lengthProperty = model.getProperty(BASE_PREFIX + LENGTH);

		for (final JenaVertexMatch segmentMatch : segments) {
			final Resource segment = segmentMatch.getVertex();
			final Selector selector = new SimpleSelector(segment, lengthProperty, (RDFNode) null);
			final StmtIterator oldStatements = model.listStatements(selector);
			if (!oldStatements.hasNext()) {
				continue;

			}
			final Statement oldStatement = oldStatements.next();
			model.remove(oldStatement);
			final Statement newStatement = model.createLiteralStatement(segment, lengthProperty, 0);
			model.add(newStatement);
		}
	}

}
