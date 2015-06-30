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

package hu.bme.mit.trainbenchmark.rdf;

public interface RDFConstants {

	static final String ONTOLOGYIRI = "http://www.semanticweb.org/ontologies/2015/trainbenchmark";
	static final String BASE_PREFIX = ONTOLOGYIRI + "#";
	static final String ID_PREFIX = "_";

	static final String XSD_PREFIX = "http://www.w3.org/2001/XMLSchema#";
	static final String RDF_PREFIX = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	static final String RDF_TYPE = RDF_PREFIX + "type";

	static final String SPARQL_BASE_PREFIX = "PREFIX base: <" + BASE_PREFIX + "> ";
	static final String SPARQL_RDF_PREFIX = "PREFIX rdf: <" + RDF_PREFIX + "> ";

}