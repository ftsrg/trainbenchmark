/**
 */
package Concept.impl;

import Concept.ConceptPackage;
import Concept.Segment;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link Concept.impl.SegmentImpl#getSegment_length <em>Segment length</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SegmentImpl extends TrackElementImpl implements Segment {
	/**
	 * The default value of the '{@link #getSegment_length() <em>Segment length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegment_length()
	 * @generated
	 * @ordered
	 */
	protected static final int SEGMENT_LENGTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSegment_length() <em>Segment length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegment_length()
	 * @generated
	 * @ordered
	 */
	protected int segment_length = SEGMENT_LENGTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConceptPackage.Literals.SEGMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSegment_length() {
		return segment_length;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSegment_length(int newSegment_length) {
		int oldSegment_length = segment_length;
		segment_length = newSegment_length;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConceptPackage.SEGMENT__SEGMENT_LENGTH, oldSegment_length, segment_length));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConceptPackage.SEGMENT__SEGMENT_LENGTH:
				return getSegment_length();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConceptPackage.SEGMENT__SEGMENT_LENGTH:
				setSegment_length((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ConceptPackage.SEGMENT__SEGMENT_LENGTH:
				setSegment_length(SEGMENT_LENGTH_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ConceptPackage.SEGMENT__SEGMENT_LENGTH:
				return segment_length != SEGMENT_LENGTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (Segment_length: ");
		result.append(segment_length);
		result.append(')');
		return result.toString();
	}

} //SegmentImpl
