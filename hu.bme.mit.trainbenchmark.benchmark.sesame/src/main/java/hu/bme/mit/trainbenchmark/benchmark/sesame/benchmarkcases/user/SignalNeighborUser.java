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

package hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.user;

import hu.bme.mit.trainbenchmark.benchmark.benchmarkcases.TransformationBenchmarkCase;
import hu.bme.mit.trainbenchmark.benchmark.sesame.SesameData;
import hu.bme.mit.trainbenchmark.benchmark.sesame.benchmarkcases.SignalNeighbor;
import hu.bme.mit.trainbenchmark.benchmark.util.Util;
import hu.bme.mit.trainbenchmark.constants.ModelConstants;
import hu.bme.mit.trainbenchmark.rdf.RDFConstants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

public class SignalNeighborUser extends SignalNeighbor implements TransformationBenchmarkCase {

	@Override
	public void modify() throws IOException {
		int nElemToModify = Util.calcModify(bc, bc.getModificationConstant(), bmr);
		bmr.addModifyParams(nElemToModify);
		long start = System.nanoTime();

		ValueFactory f = myRepository.getValueFactory();
		URI routeOC = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE);
		RepositoryResult<Statement> routesIter;

		try {
			routesIter = con.getStatements(null, RDF.TYPE, routeOC, true);

			List<Resource> routes = new ArrayList<Resource>();
			for (Statement s : routesIter.asList()) {
				routes.add(s.getSubject());
			}

			Random random = bmr.getRandom();
			List<SesameData> itemsToRemove = new ArrayList<SesameData>();
			int size = routes.size();
			if (size < nElemToModify)
				nElemToModify = size;
			for (int i = 0; i < nElemToModify; i++) {
				int rndTarget = random.nextInt(size);
				Resource aroute = routes.get(rndTarget);

				URI routeExitURI = f.createURI(RDFConstants.BASE_PREFIX + ModelConstants.ROUTE_ENTRY);
				RepositoryResult<Statement> statementsToRemove = con.getStatements(aroute, routeExitURI, null, true);

				SesameData jd = new SesameData();
				jd.setStatements(statementsToRemove.asList());
				itemsToRemove.add(jd);
			}

			// edit
			long startEdit = System.nanoTime();
			for (SesameData jd : itemsToRemove) {
				con.remove(jd.getStatements());
			}
			con.commit();
			long end = System.nanoTime();
			bmr.addEditTime(end - startEdit);
			bmr.addModificationTime(end - start);
		} catch (RepositoryException e) {
			throw new IOException(e);
		}
	}

}
