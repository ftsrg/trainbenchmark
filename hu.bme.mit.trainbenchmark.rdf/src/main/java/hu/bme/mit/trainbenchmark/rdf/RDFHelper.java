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

package hu.bme.mit.trainbenchmark.rdf;


public class RDFHelper {

	public static String brackets(final String uri) {
		return "<" + uri + ">";
	}
	
	public static long extractId(final String uri) {
		final int hashPosition = uri.lastIndexOf("#");
		if (hashPosition == -1 || hashPosition == uri.length()) {
			throw new RuntimeException("Could not extract id from URI: " + uri);
		}
		final String localName = uri.substring(hashPosition + 1);
		return Long.parseLong(localName);
	}

}
