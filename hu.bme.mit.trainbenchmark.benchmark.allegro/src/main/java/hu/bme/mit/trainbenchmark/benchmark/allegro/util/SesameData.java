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

package hu.bme.mit.trainbenchmark.benchmark.allegro.util;

import java.util.List;

import org.openrdf.model.Literal;
import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;

public class SesameData {
	protected List<Statement> statements;
	protected Statement statement;
	protected Resource resource;
	protected URI uri;
	protected Literal literal;

	public List<Statement> getStatements() {
		return statements;
	}

	public Statement getStatement() {
		return statement;
	}

	public Resource getResource() {
		return resource;
	}

	public URI getUri() {
		return uri;
	}

	public Literal getLiteral() {
		return literal;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public void setLiteral(Literal literal) {
		this.literal = literal;
	}

}
