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

package hu.bme.mit.trainbenchmark.benchmark.drools6.benchmarkcases;

import hu.bme.mit.trainbenchmark.benchmark.drools6.ResultListener;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.LiveQuery;

public class Drools6LiveQueryMWE {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();

		// create sesion with query
		System.out.println("Phase1: initialisation");
		KieFileSystem kfs = kieServices.newKieFileSystem();
		String queryStr = "query \"isNewString\"" + "   str : String()" + "end";
		kfs.write("src/main/resources/KBase1/oneQuery.drl", queryStr);
		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs);
		kieBuilder.buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			throw new RuntimeException("Build Errors:\n" + kieBuilder.getResults().toString());
		}
		KieContainer kContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieSession ksession = kContainer.newKieSession();

		// insert two facts
		System.out.println("Phase2: insert two facts");
		ksession.insert(new String("apple"));
		ksession.insert(new String("pear"));

		// initialize query in a batch way
		System.out.println("Phase3: batch query");
		ResultListener<String> listener = new ResultListener<String>("str");
		LiveQuery query = ksession.openLiveQuery("isNewString", new Object[] {}, listener);
		System.out.println("Number of matches (should be 2): " + listener.getMatching().size());

		// insert another fact and get updated (?) query results
		System.out.println("Phase4: incremental modification and query");
		ksession.insert(new String("banana"));
		ksession.fireAllRules();
		System.out.println("Number of matches (should be 3): " + listener.getMatching().size());

		// close the query
		System.out.println("Phase5: close");
		query.close();
		System.out.println("Number of matches (should be 0): " + listener.getMatching().size());
	}

}
