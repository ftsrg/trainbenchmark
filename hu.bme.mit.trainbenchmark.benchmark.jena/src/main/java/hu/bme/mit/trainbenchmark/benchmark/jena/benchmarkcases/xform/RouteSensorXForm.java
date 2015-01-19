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

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.RouteSensor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class RouteSensorXForm extends RouteSensor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(jbc, jbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();

		Random random = bmr.getRandom();
		int size = invalids.size();
		Set<Statement> itemsToRemove = new HashSet<>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Resource sensor = invalids.get(rndTarget);

			Selector selector = new SimpleSelector(null, model.getProperty(RDFConstants.BASE_PREFIX + ModelConstants.TRACKELEMENT_SENSOR),
					sensor);

			StmtIterator statementsToRemove = model.listStatements(selector);

			while (statementsToRemove.hasNext()) {
				Statement statementToRemove = statementsToRemove.next();
				itemsToRemove.add(statementToRemove);
			}

		}

		// -- do edit
		long startEdit = System.nanoTime();
		for (Statement statementToRemove : itemsToRemove) {
			model.remove(statementToRemove);
		}
		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);

	}

}
