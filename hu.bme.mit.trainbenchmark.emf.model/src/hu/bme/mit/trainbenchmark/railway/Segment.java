/**
 */
package hu.bme.mit.trainbenchmark.railway;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link hu.bme.mit.trainbenchmark.railway.Segment#getSegment_length <em>Segment length</em>}</li>
 * </ul>
 * </p>
 *
 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends TrackElement {
	/**
	 * Returns the value of the '<em><b>Segment length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segment length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segment length</em>' attribute.
	 * @see #setSegment_length(int)
	 * @see hu.bme.mit.trainbenchmark.railway.RailwayPackage#getSegment_Segment_length()
	 * @model required="true"
	 * @generated
	 */
	int getSegment_length();

	/**
	 * Sets the value of the '{@link hu.bme.mit.trainbenchmark.railway.Segment#getSegment_length <em>Segment length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Segment length</em>' attribute.
	 * @see #getSegment_length()
	 * @generated
	 */
	void setSegment_length(int value);

} // Segment
