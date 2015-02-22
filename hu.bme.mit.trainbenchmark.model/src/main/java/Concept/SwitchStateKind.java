/**
 */
package Concept;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Switch State Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see Concept.ConceptPackage#getSwitchStateKind()
 * @model
 * @generated
 */
public enum SwitchStateKind implements Enumerator {
	/**
	 * The '<em><b>Point State Kind FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	POINT_STATE_KIND_FAILURE(0, "PointStateKind_FAILURE", "PointStateKind_FAILURE"),

	/**
	 * The '<em><b>Point State Kind LEFT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_LEFT_VALUE
	 * @generated
	 * @ordered
	 */
	POINT_STATE_KIND_LEFT(1, "PointStateKind_LEFT", "PointStateKind_LEFT"),

	/**
	 * The '<em><b>Point State Kind RIGHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_RIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	POINT_STATE_KIND_RIGHT(2, "PointStateKind_RIGHT", "PointStateKind_RIGHT"),

	/**
	 * The '<em><b>Point State Kind STRAIGHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_STRAIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	POINT_STATE_KIND_STRAIGHT(3, "PointStateKind_STRAIGHT", "PointStateKind_STRAIGHT");

	/**
	 * The '<em><b>Point State Kind FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Point State Kind FAILURE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_FAILURE
	 * @model name="PointStateKind_FAILURE"
	 * @generated
	 * @ordered
	 */
	public static final int POINT_STATE_KIND_FAILURE_VALUE = 0;

	/**
	 * The '<em><b>Point State Kind LEFT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Point State Kind LEFT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_LEFT
	 * @model name="PointStateKind_LEFT"
	 * @generated
	 * @ordered
	 */
	public static final int POINT_STATE_KIND_LEFT_VALUE = 1;

	/**
	 * The '<em><b>Point State Kind RIGHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Point State Kind RIGHT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_RIGHT
	 * @model name="PointStateKind_RIGHT"
	 * @generated
	 * @ordered
	 */
	public static final int POINT_STATE_KIND_RIGHT_VALUE = 2;

	/**
	 * The '<em><b>Point State Kind STRAIGHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Point State Kind STRAIGHT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #POINT_STATE_KIND_STRAIGHT
	 * @model name="PointStateKind_STRAIGHT"
	 * @generated
	 * @ordered
	 */
	public static final int POINT_STATE_KIND_STRAIGHT_VALUE = 3;

	/**
	 * An array of all the '<em><b>Switch State Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SwitchStateKind[] VALUES_ARRAY =
		new SwitchStateKind[] {
			POINT_STATE_KIND_FAILURE,
			POINT_STATE_KIND_LEFT,
			POINT_STATE_KIND_RIGHT,
			POINT_STATE_KIND_STRAIGHT,
		};

	/**
	 * A public read-only list of all the '<em><b>Switch State Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SwitchStateKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Switch State Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SwitchStateKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SwitchStateKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Switch State Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SwitchStateKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SwitchStateKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Switch State Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SwitchStateKind get(int value) {
		switch (value) {
			case POINT_STATE_KIND_FAILURE_VALUE: return POINT_STATE_KIND_FAILURE;
			case POINT_STATE_KIND_LEFT_VALUE: return POINT_STATE_KIND_LEFT;
			case POINT_STATE_KIND_RIGHT_VALUE: return POINT_STATE_KIND_RIGHT;
			case POINT_STATE_KIND_STRAIGHT_VALUE: return POINT_STATE_KIND_STRAIGHT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SwitchStateKind(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //SwitchStateKind
