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
import hu.bme.mit.trainbenchmark.benchmark.jena.benchmarkcases.PosLength;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class PosLengthXForm extends PosLength implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(jbc, jbc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);

		// modify
		long start = System.nanoTime();

		Random random = bmr.getRandom();
		int size = invalids.size();
		Set<Statement> itemsToRemove = new HashSet<>();
		Set<Statement> itemsToAdd = new HashSet<>();
		for (int i = 0; i < nElemToModify; i++) {
			int rndTarget = random.nextInt(size);
			Resource segment = invalids.get(rndTarget);

			Selector selector = new SimpleSelector(segment, model.getProperty(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH),
					(RDFNode) null);

			StmtIterator statementsToRemove = model.listStatements(selector);
			Statement newValueStmt = null;
			while (statementsToRemove.hasNext()) {
				Statement stmt = statementsToRemove.next();
				Integer length = stmt.getInt();
				itemsToRemove.add(stmt);
				newValueStmt = model.createLiteralStatement(segment,
						model.getProperty(RDFConstants.BASE_PREFIX + ModelConstants.SEGMENT_LENGTH), -1 * (length - 1));
			}

			if (newValueStmt != null)
				itemsToAdd.add(newValueStmt);
		}

		// -- do edit
		long startEdit = System.nanoTime();
		for (Statement statementToRemove : itemsToRemove) {
			model.remove(statementToRemove);
		}
		for (Statement newValueStmt : itemsToAdd) {
			model.add(newValueStmt);
		}

		long end = System.nanoTime();
		bmr.addEditTime(end - startEdit);
		bmr.addModificationTime(end - start);
	}

}
