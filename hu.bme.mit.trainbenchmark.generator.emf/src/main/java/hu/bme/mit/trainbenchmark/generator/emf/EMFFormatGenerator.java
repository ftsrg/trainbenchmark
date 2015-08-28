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

import hu.bme.mit.trainbenchmark.emf.FileBroker;
import hu.bme.mit.trainbenchmark.generator.FormatGenerator;
import hu.bme.mit.trainbenchmark.generator.config.GeneratorConfig;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public abstract class EMFFormatGenerator extends FormatGenerator {

	protected Resource resource;

	public EMFFormatGenerator(GeneratorConfig generatorConfig) {
		this.generatorConfig = generatorConfig;
	}

	@Override
	public String syntax() {
		return "EMF";
	}

	@Override
	public void initModel() {
		final String fileName = generatorConfig.getModelPathNameWithoutExtension() + ".emf";
		final URI resourceURI = FileBroker.getEMFUri(fileName);

		final ResourceSet resourceSet = new ResourceSetImpl();

		resource = resourceSet.createResource(resourceURI);
		resource.getContents().clear();

		initializeFactory();
		resource.getContents().add(getContainer());
	}

	@Override
	public void persistModel() throws IOException {
		resource.save(null);
	}

	@Override
	public void startTransaction() throws IOException {
		// empty
	}

	@Override
	public void endTransaction() {
		// empty
	}

	protected abstract void initializeFactory();

	protected abstract EObject getContainer();

}
