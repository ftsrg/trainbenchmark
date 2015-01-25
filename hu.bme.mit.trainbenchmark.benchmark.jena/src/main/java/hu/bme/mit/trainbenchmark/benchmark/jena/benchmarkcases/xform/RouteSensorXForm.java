/*******************************************************************************
 * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Benedek Izso - initial API and implementation
 *   Gabor Szarnyas - initial API and implementation
 *******************************************************************************/

package hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.xform;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.Transformation;
import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		final long nElemToModify = Util.calcModify(jbc, jbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		final long start = System.nanoTime();

		final List<Resource> sensors = Transformation.pickRandom(nElemToModify, invalids);
		final List<Statement> itemsToRemove = new ArrayList<>();
		for (final Resource sensor : sensors) {
			final Selector selector = new SimpleSelector(null, model.getProperty(RDFConstants.BASE_PREFIX
					+ ModelConstants.TRACKELEMENT_SENSOR), sensor);

			final StmtIterator statementsToRemove = model.listStatements(selector);

			while (statementsToRemove.hasNext()) {
				final Statement statementToRemove = statementsToRemove.next();
				itemsToRemove.add(statementToRemove);
			}

		}

		// edit
		final long startEdit = System.nanoTime();
		for (final Statement statementToRemove : itemsToRemove) {
			model.remove(statementToRemove);
		}
		final long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
