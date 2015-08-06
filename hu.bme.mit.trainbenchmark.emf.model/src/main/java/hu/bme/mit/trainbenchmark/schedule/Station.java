/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Station</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Station#getNeighbors <em>Neighbors</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Station#getCode <em>Code</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Station#getStanox <em>Stanox</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Station#getNalco <em>Nalco</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getStation()
 * @model
 * @generated
 */
public interface Station extends EObject {
	/**
	 * Returns the value of the '<em><b>Neighbors</b></em>' reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.schedule.Station}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Neighbors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Neighbors</em>' reference list.
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getStation_Neighbors()
	 * @model required="true"
	 * @generated
	 */
	EList<Station> getNeighbors();

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getStation_Code()
	 * @model required="true"
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Station#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Stanox</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stanox</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stanox</em>' attribute.
	 * @see #setStanox(String)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getStation_Stanox()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getStanox();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Station#getStanox <em>Stanox</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stanox</em>' attribute.
	 * @see #getStanox()
	 * @generated
	 */
	void setStanox(String value);

	/**
	 * Returns the value of the '<em><b>Nalco</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nalco</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nalco</em>' attribute.
	 * @see #setNalco(String)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getStation_Nalco()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getNalco();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Station#getNalco <em>Nalco</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nalco</em>' attribute.
	 * @see #getNalco()
	 * @generated
	 */
	void setNalco(String value);

} // Station
