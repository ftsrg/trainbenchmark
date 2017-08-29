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

package hu.bme.mit.trainbenchmark.generator.cypher.config;

import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBase;

public class CypherGeneratorConfig extends GeneratorConfig {

	protected CypherFormat cypherFormat;

	protected CypherGeneratorConfig(final GeneratorConfigBase configBase, final CypherFormat cypherFormat) {
		super(configBase);
		this.cypherFormat = cypherFormat;
	}

	public CypherFormat getCypherFormat() {
		return cypherFormat;
	}

	@Override
	public String getProjectName() {
		return "graph-cypher";
	}

}
