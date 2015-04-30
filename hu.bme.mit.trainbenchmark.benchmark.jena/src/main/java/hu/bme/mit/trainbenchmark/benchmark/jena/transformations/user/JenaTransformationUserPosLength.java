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
package hu.bme.mit.trainbenchmark.benchmark.jena.transformations.user;

import static hu.bme.mit.trainbenchmark.rdf.RDFConstants.BASE_PREFIX;
import hu.bme.mit.trainbenchmark.benchmark.jena.driver.JenaDriver;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;

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

public class JenaTransformationUserPosLength extends JenaTransformationUser {

	public JenaTransformationUserPosLength(final JenaDriver jenaDriver) {
		super(jenaDriver);
	}

	@Override
	public void rhs(final Collection<Resource> segments) throws IOException {
		final Model model = jenaDriver.getModel();
		final Property property = model.getProperty(BASE_PREFIX + ModelConstants.LENGTH);

		for (final Resource segment : segments) {
			final Selector selector = new SimpleSelector(segment, property, (RDFNode) null);
			final StmtIterator statementsToRemove = model.listStatements(selector);
			if (statementsToRemove.hasNext()) {
				final Statement oldStatement = statementsToRemove.next();
				model.remove(oldStatement);
				final Statement newStatement = model.createLiteralStatement(segment, property, 0);
				model.add(newStatement);
			}
		}
	}

}
