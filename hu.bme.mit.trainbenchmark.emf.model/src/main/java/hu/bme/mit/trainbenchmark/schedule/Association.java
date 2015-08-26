/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import java.util.Date;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getLocation <em>Location</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getCategory <em>Category</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getDays <em>Days</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getAssociative <em>Associative</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getStpIndicator <em>Stp Indicator</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getStartDate <em>Start Date</em>}</li>
 *   <li>{@link hu.bme.mit.trainbenchmark.schedule.Association#getEndDate <em>End Date</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation()
 * @model
 * @generated
 */
public interface Association extends EObject {
        /**
         * Returns the value of the '<em><b>Location</b></em>' reference.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Location</em>' reference isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Location</em>' reference.
         * @see #setLocation(Station)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_Location()
         * @model required="true"
         * @generated
         */
        Station getLocation();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getLocation <em>Location</em>}' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Location</em>' reference.
         * @see #getLocation()
         * @generated
         */
        void setLocation(Station value);

        /**
         * Returns the value of the '<em><b>Category</b></em>' attribute.
         * The literals are from the enumeration {@link hu.bme.mit.trainbenchmark.schedule.AssociationCategory}.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Category</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Category</em>' attribute.
         * @see hu.bme.mit.trainbenchmark.schedule.AssociationCategory
         * @see #setCategory(AssociationCategory)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_Category()
         * @model
         * @generated
         */
        AssociationCategory getCategory();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getCategory <em>Category</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Category</em>' attribute.
         * @see hu.bme.mit.trainbenchmark.schedule.AssociationCategory
         * @see #getCategory()
         * @generated
         */
        void setCategory(AssociationCategory value);

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
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_Days()
         * @model
         * @generated
         */
        String getDays();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getDays <em>Days</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Days</em>' attribute.
         * @see #getDays()
         * @generated
         */
        void setDays(String value);

        /**
         * Returns the value of the '<em><b>Associative</b></em>' reference.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Associative</em>' reference isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Associative</em>' reference.
         * @see #setAssociative(Schedule)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_Associative()
         * @model required="true"
         * @generated
         */
        Schedule getAssociative();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getAssociative <em>Associative</em>}' reference.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Associative</em>' reference.
         * @see #getAssociative()
         * @generated
         */
        void setAssociative(Schedule value);

        /**
         * Returns the value of the '<em><b>Stp Indicator</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>Stp Indicator</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>Stp Indicator</em>' attribute.
         * @see #setStpIndicator(String)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_StpIndicator()
         * @model unique="false" ordered="false"
         * @generated
         */
        String getStpIndicator();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getStpIndicator <em>Stp Indicator</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Stp Indicator</em>' attribute.
         * @see #getStpIndicator()
         * @generated
         */
        void setStpIndicator(String value);

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
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_StartDate()
         * @model
         * @generated
         */
        Date getStartDate();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getStartDate <em>Start Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>Start Date</em>' attribute.
         * @see #getStartDate()
         * @generated
         */
        void setStartDate(Date value);

        /**
         * Returns the value of the '<em><b>End Date</b></em>' attribute.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of the '<em>End Date</em>' attribute isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @return the value of the '<em>End Date</em>' attribute.
         * @see #setEndDate(Date)
         * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getAssociation_EndDate()
         * @model unique="false" ordered="false"
         * @generated
         */
        Date getEndDate();

        /**
         * Sets the value of the '{@link hu.bme.mit.trainbenchmark.schedule.Association#getEndDate <em>End Date</em>}' attribute.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @param value the new value of the '<em>End Date</em>' attribute.
         * @see #getEndDate()
         * @generated
         */
        void setEndDate(Date value);

} // Association
