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

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import hu.bme.mit.trainbenchmark.benchmark.operations.ModelTransformation;
import hu.bme.mit.trainbenchmark.benchmark.sql.driver.SqlDriver;
import hu.bme.mit.trainbenchmark.constants.RailwayQuery;

public abstract class SqlTransformation<TObject, TSqlDriver extends SqlDriver> extends ModelTransformation<TObject, TSqlDriver> {

	protected PreparedStatement preparedUpdateStatement;
	protected final String updateQuery;
		
	protected SqlTransformation(final TSqlDriver driver, final Optional<String> workspaceDir, final RailwayQuery query, final String scenario) throws IOException {
		super(driver);
		
		final String updatePath = workspaceDir.get() + driver.getResourceDirectory() + "transformations/" + scenario + query + ".sql";
		this.updateQuery = FileUtils.readFileToString(new File(updatePath));
	}
	
}
