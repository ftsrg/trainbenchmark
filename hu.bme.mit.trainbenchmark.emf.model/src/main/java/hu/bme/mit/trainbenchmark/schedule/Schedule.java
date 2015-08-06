/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schedule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getDestinations <em>Destinations</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStatus <em>Status</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getTransaction <em>Transaction</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getDays <em>Days</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getPlanning <em>Planning</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getOrigin <em>Origin</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getTerminal <em>Terminal</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule()
 * @model
 * @generated
 */
public interface Schedule extends EObject {
	/**
	 * Returns the value of the '<em><b>Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link hu.bme.mit.trainbenchmark.schedule.Station}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destinations</em>' containment reference list.
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Destinations()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Station> getDestinations();

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.schedule.TrainStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.schedule.TrainStatus
	 * @see #setStatus(TrainStatus)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Status()
	 * @model
	 * @generated
	 */
	TrainStatus getStatus();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.schedule.TrainStatus
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(TrainStatus value);

	/**
	 * Returns the value of the '<em><b>Start Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Date</em>' attribute.
	 * @see #setStartDate(Date)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_StartDate()
	 * @model
	 * @generated
	 */
	Date getStartDate();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getStartDate <em>Start Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Date</em>' attribute.
	 * @see #getStartDate()
	 * @generated
	 */
	void setStartDate(Date value);

	/**
	 * Returns the value of the '<em><b>Transaction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction</em>' attribute.
	 * @see #setTransaction(String)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Transaction()
	 * @model
	 * @generated
	 */
	String getTransaction();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getTransaction <em>Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction</em>' attribute.
	 * @see #getTransaction()
	 * @generated
	 */
	void setTransaction(String value);

	/**
	 * Returns the value of the '<em><b>Days</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Days</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Days</em>' attribute.
	 * @see #setDays(String)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Days()
	 * @model
	 * @generated
	 */
	String getDays();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getDays <em>Days</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Days</em>' attribute.
	 * @see #getDays()
	 * @generated
	 */
	void setDays(String value);

	/**
	 * Returns the value of the '<em><b>Planning</b></em>' attribute.
	 * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.schedule.SchedulePlanning}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Planning</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Planning</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePlanning
	 * @see #setPlanning(SchedulePlanning)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Planning()
	 * @model
	 * @generated
	 */
	SchedulePlanning getPlanning();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getPlanning <em>Planning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Planning</em>' attribute.
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePlanning
	 * @see #getPlanning()
	 * @generated
	 */
	void setPlanning(SchedulePlanning value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' containment reference.
	 * @see #setOrigin(Station)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Origin()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Station getOrigin();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getOrigin <em>Origin</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' containment reference.
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(Station value);

	/**
	 * Returns the value of the '<em><b>Terminal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Terminal</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminal</em>' containment reference.
	 * @see #setTerminal(Station)
	 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedule_Terminal()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Station getTerminal();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Schedule#getTerminal <em>Terminal</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminal</em>' containment reference.
	 * @see #getTerminal()
	 * @generated
	 */
	void setTerminal(Station value);

} // Schedule
