package hu.bme.mit.trainbenchmark.benchmark.emfincquery.driver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.incquery.runtime.api.AdvancedIncQueryEngine;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.base.api.NavigationHelper;
import org.eclipse.incquery.runtime.emf.EMFScope;

import com.google.common.collect.Sets;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.checker.EMFIncQueryChecker;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayElement;
import hu.bme.mit.trainbenchmark.railway.RailwayPackage;

public abstract class EMFIncQueryBaseDriver<M extends BasePatternMatch> extends EMFDriver {

	protected EMFIncQueryChecker<M> checker;
	protected AdvancedIncQueryEngine engine;

	public void registerChecker(final EMFIncQueryChecker<M> checker) throws IOException {
		this.checker = checker;
	}

	@Override
	public List<RailwayElement> collectVertices(final String type) throws Exception {
		final EClass clazz = (EClass) RailwayPackage.eINSTANCE.getEClassifier(type);
		final NavigationHelper navigationHelper = EMFScope.extractUnderlyingEMFIndex(engine);

		// register the class (won't register it twice)
		navigationHelper.registerEClasses(Sets.newHashSet(clazz));

		final Set<EObject> instances = navigationHelper.getAllInstances(clazz);
		final List<RailwayElement> vertices = new ArrayList<>();
		for (final EObject instance : instances) {
			vertices.add((RailwayElement) instance);
		}

		return vertices;
	}

	public AdvancedIncQueryEngine getEngine() {
		return engine;
	}
}
