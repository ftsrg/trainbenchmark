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
import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;

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

public class JenaTransformationInjectPosLength extends JenaTransformationInject {

	public JenaTransformationInjectPosLength(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> segments) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property lengthProperty = model.getProperty(BASE_PREFIX + LENGTH);

		for (final Resource segment : segments) {
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
