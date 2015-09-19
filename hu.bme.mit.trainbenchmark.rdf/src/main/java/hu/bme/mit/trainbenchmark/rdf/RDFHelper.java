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

package hu.bme.mit.trainbenchmark.rdf;

public class RDFHelper {

	public static String brackets(final String uri) {
		return "<" + uri + ">";
	}

	public static long extractId(final String uri) {
		final int hashPosition = uri.lastIndexOf("#");
		try {
			final String localName = uri.substring(hashPosition + RDFConstants.ID_PREFIX.length() + 1);
			return Long.parseLong(localName);
		} catch (final NumberFormatException e) {
			throw new RuntimeException("Could not extract id from URI: " + uri, e);
		}
	}

	public static String addEnumPrefix(final Enum<?> e) {
		return e.getClass().getSimpleName().toUpperCase() + "_" + e.toString();
	}

	public static String removePrefix(final Class<?> enumClass, final String name) {
		return name.replace(enumClass.getSimpleName().toUpperCase() + "_", "");
	}

	public static String getPostfix(final boolean metamodel) {
		if (metamodel) {
			return "-metamodel.ttl";
		} else {
			return "-inferred.ttl";
		}
	}

}
