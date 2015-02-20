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
package hu.bme.mit.trainbenchmark.emf;

import java.io.IOException;
import java.util.List;

public class EMFMain {

	public static void main(final String[] args) throws IOException {
		final String modelPath = "/home/szarnyasg/git/trainbenchmark/models/railway-test-1.concept";
		final EMFDriver driver = new EMFDriver(modelPath);
		
		final List<? extends Object> collectVertices = driver.collectVertices("Segment");
		System.out.println(collectVertices.size());
	}
	
}
