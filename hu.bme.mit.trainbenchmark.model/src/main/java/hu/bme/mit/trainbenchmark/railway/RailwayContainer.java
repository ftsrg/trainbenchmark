/**
 */
package hu.bme.mit.trainbenchmark.railway;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.RailwayContainer#getContains <em>Contains</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRailwayContainer()
 * @model
 * @generated
 */
public interface RailwayContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Contains</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.railway.RailwayElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contains</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contains</em>' containment reference list.
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getRailwayContainer_Contains()
	 * @model containment="true"
	 * @generated
	 */
	EList<RailwayElement> getContains();

} // RailwayContainer
