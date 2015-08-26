/**
 */
package hu.bme.mit.trainbenchmark.schedule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Planning</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see hu.bme.mit.trainbenchmark.schedule.SchedulePackage#getSchedulePlanning()
 * @model
 * @generated
 */
public enum SchedulePlanning implements Enumerator {
        /**
         * The '<em><b>PERMANENT</b></em>' literal object.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #PERMANENT_VALUE
         * @generated
         * @ordered
         */
        PERMANENT(0, "PERMANENT", "PERMANENT"),

        /**
         * The '<em><b>SHORT TERM</b></em>' literal object.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see #SHORT_TERM_VALUE
         * @generated
         * @ordered
         */
        SHORT_TERM(1, "SHORT_TERM", "SHORT_TERM");

        /**
         * The '<em><b>PERMANENT</b></em>' literal value.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of '<em><b>PERMANENT</b></em>' literal object isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @see #PERMANENT
         * @model
         * @generated
         * @ordered
         */
        public static final int PERMANENT_VALUE = 0;

        /**
         * The '<em><b>SHORT TERM</b></em>' literal value.
         * <!-- begin-user-doc -->
         * <p>
         * If the meaning of '<em><b>SHORT TERM</b></em>' literal object isn't clear,
         * there really should be more of a description here...
         * </p>
         * <!-- end-user-doc -->
         * @see #SHORT_TERM
         * @model
         * @generated
         * @ordered
         */
        public static final int SHORT_TERM_VALUE = 1;

        /**
         * An array of all the '<em><b>Planning</b></em>' enumerators.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        private static final SchedulePlanning[] VALUES_ARRAY =
                new SchedulePlanning[] {
                        PERMANENT,
                        SHORT_TERM,
                };

        /**
         * A public read-only list of all the '<em><b>Planning</b></em>' enumerators.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static final List<SchedulePlanning> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

        /**
         * Returns the '<em><b>Planning</b></em>' literal with the specified literal value.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static SchedulePlanning get(String literal) {
                for (int i = 0; i < VALUES_ARRAY.length; ++i) {
                        SchedulePlanning result = VALUES_ARRAY[i];
                        if (result.toString().equals(literal)) {
                                return result;
                        }
                }
                return null;
        }

        /**
         * Returns the '<em><b>Planning</b></em>' literal with the specified name.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static SchedulePlanning getByName(String name) {
                for (int i = 0; i < VALUES_ARRAY.length; ++i) {
                        SchedulePlanning result = VALUES_ARRAY[i];
                        if (result.getName().equals(name)) {
                                return result;
                        }
                }
                return null;
        }

        /**
         * Returns the '<em><b>Planning</b></em>' literal with the specified integer value.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public static SchedulePlanning get(int value) {
                switch (value) {
                        case PERMANENT_VALUE: return PERMANENT;
                        case SHORT_TERM_VALUE: return SHORT_TERM;
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
        private SchedulePlanning(int value, String name, String literal) {
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
        
} //SchedulePlanning
