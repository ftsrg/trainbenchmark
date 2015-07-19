package hu.bme.mit.trainbenchmark.emf.analyzer;

import hu.bme.mit.trainbenchmark.benchmark.analyzer.ModelAnalyzer;
import hu.bme.mit.trainbenchmark.constants.EdgeDirection;
import hu.bme.mit.trainbenchmark.emf.EMFDriver;
import hu.bme.mit.trainbenchmark.railway.RailwayContainer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class EMFModelAnalyzer extends ModelAnalyzer<EMFDriver> {

	public EMFModelAnalyzer(EMFDriver driver) {
		super(driver);
	}

	@Override
	public void calculateMetrics() {
		RailwayContainer container = driver.getContainer();
		EObject object;
		EList<EReference> references;
		double degree = 0;

		TreeIterator<EObject> contents = container.eAllContents();
		while (contents.hasNext()) {
			object = contents.next();

			numberOfNodes++;
			references = object.eClass().getEAllReferences();
			degree = getDegree(object, references);
			if (degree > 0) {
				numberOfNodesWithOutgoingDegrees++;
			}
			numberOfEdges += degree;
			changeMaximumDegree(EdgeDirection.OUTGOING, degree);
		}
		calculateAverageDegree(EdgeDirection.BOTH);
		calculateAverageDegree(EdgeDirection.OUTGOING);

		contents = container.eAllContents();
		int roundedOutgoingDegree = roundAverageDegree(EdgeDirection.OUTGOING);
		while (contents.hasNext()) {
			object = contents.next();
			references = object.eClass().getEAllReferences();
			degree = getDegree(object, references);
			changeNumberOfDegrees(EdgeDirection.OUTGOING, degree, roundedOutgoingDegree);

		}
	}

	protected double getDegree(EObject object, EList<EReference> references) {
		double degree = 0;
		for (EReference ref : references) {
			if (ref.isMany()) {
				degree += ((EList<EObject>) object.eGet(ref, true)).size();
			} else {
				if (object.eGet(ref, true) != null) {
					degree++;
				}
			}
		}
		return degree;
	}

}
