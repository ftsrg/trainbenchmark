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
package hu.bme.mit.trainbenchmark.benchmark.sql.queries;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelQuery;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class SqlQuery<TSqlMatch extends SqlMatch, TSqlDriver extends SqlDriver> extends ModelQuery<TSqlMatch, TSqlDriver> {

	protected final String queryDefinition;
	protected PreparedStatement statement;

	public SqlQuery(final TSqlDriver driver, final String workspaceDir, final RailwayQuery query) throws IOException, SQLException {
		super(query, driver);

		final String queryPath = workspaceDir + driver.getResourceDirectory() + "queries/" + query + ".sql";
		queryDefinition = FileUtils.readFileToString(new File(queryPath), StandardCharsets.UTF_8);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TSqlMatch> evaluate() throws SQLException {
		if (statement == null) {
			statement = driver.getConnection().prepareStatement(queryDefinition);
		}
		return (Collection<TSqlMatch>) driver.runStatement(query, statement);
	}

}
