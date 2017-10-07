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
package hu.bme.mit.trainbenchmark.benchmark.sql.transformations;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.benchmark.sql.matches.SqlMatch;
import hu.bme.mit.trainbenchmark.constants.RailwayOperation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;

public abstract class SqlTransformation<TSqlMatch extends SqlMatch, TSqlDriver extends SqlDriver> extends ModelTransformation<TSqlMatch, TSqlDriver> {

	protected PreparedStatement preparedUpdateStatement;
	protected String updateQuery;

	protected SqlTransformation(final TSqlDriver driver, final String workspaceDir, final RailwayOperation operation) throws IOException {
		super(driver);

		final String updatePath = workspaceDir + driver.getResourceDirectory() + "transformations/" + operation + "Rhs.sql";
		this.updateQuery = FileUtils.readFileToString(new File(updatePath), StandardCharsets.UTF_8);
	}

}
