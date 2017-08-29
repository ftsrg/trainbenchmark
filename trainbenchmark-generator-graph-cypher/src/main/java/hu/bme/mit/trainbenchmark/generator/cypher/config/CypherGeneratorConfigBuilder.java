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

import com.google.common.base.Preconditions;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfigBuilder;

public class CypherGeneratorConfigBuilder extends GeneratorConfigBuilder<CypherGeneratorConfig, CypherGeneratorConfigBuilder> {

	protected CypherFormat cypherFormat;

	public CypherGeneratorConfigBuilder setGraphFormat(CypherFormat cypherFormat) {
		this.cypherFormat = cypherFormat;
		return this;
	}

	@Override
	public CypherGeneratorConfig createConfig() {
		return new CypherGeneratorConfig(configBase, cypherFormat);
	}

	@Override
	public void checkNotNulls() {
		super.checkNotNulls();
		Preconditions.checkNotNull(cypherFormat);
	}

}
