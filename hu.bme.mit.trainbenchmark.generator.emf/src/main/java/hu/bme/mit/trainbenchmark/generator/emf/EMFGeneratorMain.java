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

package hu.bme.mit.trainbenchmark.generator.emf;

import hu.bme.mit.trainbenchmark.generator.Generator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

public class EMFGeneratorMain {

	public static void main(final String[] args) throws Exception {
		final GeneratorConfig generatorConfig = new GeneratorConfig(args);
		final EMFSerializer emfSerializer = new EMFSerializer(generatorConfig);
		final Generator generator = new Generator(emfSerializer, generatorConfig);
		generator.generateModels();
	}

}
