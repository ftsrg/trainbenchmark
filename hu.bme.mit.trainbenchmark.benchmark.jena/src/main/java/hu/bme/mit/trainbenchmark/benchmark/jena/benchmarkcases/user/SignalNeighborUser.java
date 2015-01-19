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

package hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.RDF;

public class SignalNeighborUser extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(jbc, jbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();

		ResIterator switchStatements = model.listSubjectsWithProperty(RDF.type,
				model.getResource(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE));
		List<Resource> routes = new ArrayList<Resource>();
		routes = switchStatements.toList();

		Random random = bmr.getRandom();
		Set<Statement> itemsToRemove = new HashSet<Statement>();
		int size = routes.size();
		if (size < nElemToModify)
			nElemToModify = size;
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Resource aroute = routes.get(rndTarget);

			Selector selector = new SimpleSelector(aroute, model.getProperty(RDFConstants.BASE_PREFIX), (RDFNode) null);
			StmtIterator statementsToRemove = model.listStatements(selector);

			while (statementsToRemove.hasNext()) {
				itemsToRemove.add(statementsToRemove.next());
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
