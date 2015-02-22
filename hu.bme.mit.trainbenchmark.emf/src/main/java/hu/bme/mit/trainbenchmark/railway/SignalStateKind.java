/**
 */
package hu.bme.mit.trainbenchmark.railway;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Signal State Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSignalStateKind()
 * @model
 * @generated
 */
public enum SignalStateKind implements Enumerator {
	/**
	 * The '<em><b>Signal State Kind STOP</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_STOP_VALUE
	 * @generated
	 * @ordered
	 */
	SIGNAL_STATE_KIND_STOP(0, "SignalStateKind_STOP", "SignalStateKind_STOP"),

	/**
	 * The '<em><b>Signal State Kind FAILURE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_FAILURE_VALUE
	 * @generated
	 * @ordered
	 */
	SIGNAL_STATE_KIND_FAILURE(1, "SignalStateKind_FAILURE", "SignalStateKind_FAILURE"),

	/**
	 * The '<em><b>Signal State Kind GO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_GO_VALUE
	 * @generated
	 * @ordered
	 */
	SIGNAL_STATE_KIND_GO(2, "SignalStateKind_GO", "SignalStateKind_GO");

	/**
	 * The '<em><b>Signal State Kind STOP</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Signal State Kind STOP</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_STOP
	 * @model name="SignalStateKind_STOP"
	 * @generated
	 * @ordered
	 */
	public static final int SIGNAL_STATE_KIND_STOP_VALUE = 0;

	/**
	 * The '<em><b>Signal State Kind FAILURE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Signal State Kind FAILURE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_FAILURE
	 * @model name="SignalStateKind_FAILURE"
	 * @generated
	 * @ordered
	 */
	public static final int SIGNAL_STATE_KIND_FAILURE_VALUE = 1;

	/**
	 * The '<em><b>Signal State Kind GO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Signal State Kind GO</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SIGNAL_STATE_KIND_GO
	 * @model name="SignalStateKind_GO"
	 * @generated
	 * @ordered
	 */
	public static final int SIGNAL_STATE_KIND_GO_VALUE = 2;

	/**
	 * An array of all the '<em><b>Signal State Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SignalStateKind[] VALUES_ARRAY =
		new SignalStateKind[] {
			SIGNAL_STATE_KIND_STOP,
			SIGNAL_STATE_KIND_FAILURE,
			SIGNAL_STATE_KIND_GO,
		};

	/**
	 * A public read-only list of all the '<em><b>Signal State Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SignalStateKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Signal State Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalStateKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SignalStateKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Signal State Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalStateKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SignalStateKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Signal State Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SignalStateKind get(int value) {
		switch (value) {
			case SIGNAL_STATE_KIND_STOP_VALUE: return SIGNAL_STATE_KIND_STOP;
			case SIGNAL_STATE_KIND_FAILURE_VALUE: return SIGNAL_STATE_KIND_FAILURE;
			case SIGNAL_STATE_KIND_GO_VALUE: return SIGNAL_STATE_KIND_GO;
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
	private SignalStateKind(int value, String name, String literal) {
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
	
} //SignalStateKind
