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

package hu.bme.mit.trainbenchmark.generator.rdf.config;

import hu.bme.mit.trainbenchmark.config.ExecutionConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;
import hu.bme.mit.trainbenchmark.rdf.RdfFormat;

public class RdfGeneratorConfig extends GeneratorConfig {

	protected boolean inferred;
	protected RdfFormat format;

	public RdfGeneratorConfig(final GeneratorConfigBase configBase, final ExecutionConfig executionConfig,
			final boolean inferred, final RdfFormat format) {
		super(configBase, executionConfig);
		this.inferred = inferred;
		this.format = format;
	}

	public boolean isInferred() {
		return inferred;
	}

	public RdfFormat getFormat() {
		return format;
	}

	public String getModelFlavor() {
		return isInferred() ? "-inferred" : "-metamodel";
	}

	public String getExtension() {
		return format.getExtension();
	}

	@Override
	public String getProjectName() {
		return "rdf";
	}

}
