///*******************************************************************************
// * Copyright (c) 2010-2014, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
// * All rights reserved. This program and the accompanying materials
// * are made available under the terms of the Eclipse Public License v1.0
// * which accompanies this distribution, and is available at
// * http://www.eclipse.org/legal/epl-v10.html
// *
// * Contributors:
// *   Benedek Izso - initial API and implementation
// *   Gabor Szarnyas - initial API and implementation
// *******************************************************************************/
//
//package hu.bme.mit.trainbenchmark.benchmark.neo4j.benchmarkcases;
//
//import org.neo4j.graphdb.Relationship;
//import org.neo4j.graphdb.Transaction;
//import org.neo4j.tooling.GlobalGraphOperations;
//
//import com.google.common.collect.HashMultimap;
//import com.google.common.collect.Multimap;
//
//public class ConnectedNodes extends Neo4jJavaBenchmarkCase {
//
//	protected String name;
//	protected String relationshipName;
//	final Multimap<Long, Long> connectedNodes = HashMultimap.create();
//
//	public ConnectedNodes(final String name, final String relationshipName) {
//		super();
//		this.name = name;
//		this.relationshipName = relationshipName;
//	}
//
//	@Override
//	public String getName() {
//		return name;
//	}
//
//	@Override
//	public void check() {
//		final Transaction tx = graphDb.beginTx();
//
//		for (final Relationship r : GlobalGraphOperations.at(graphDb).getAllRelationships()) {
//
//			if (relationshipName.equals(r.getType().name())) {
//				connectedNodes.get(r.getStartNode().getId()).add(r.getEndNode().getId());
//			}
//
//		}
//
//		tx.success();
//	}
//
//}
