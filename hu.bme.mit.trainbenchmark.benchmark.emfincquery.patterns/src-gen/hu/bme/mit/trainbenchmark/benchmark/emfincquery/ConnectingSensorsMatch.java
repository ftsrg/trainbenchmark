package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.ConnectingSensorsQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors pattern,
 * to be used in conjunction with {@link ConnectingSensorsMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see ConnectingSensorsMatcher
 * @see ConnectingSensorsProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class ConnectingSensorsMatch extends BasePatternMatch {
  private Sensor fSensor1;
  
  private Sensor fSensor2;
  
  private static List<String> parameterNames = makeImmutableList("sensor1", "sensor2");
  
  private ConnectingSensorsMatch(final Sensor pSensor1, final Sensor pSensor2) {
    this.fSensor1 = pSensor1;
    this.fSensor2 = pSensor2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("sensor1".equals(parameterName)) return this.fSensor1;
    if ("sensor2".equals(parameterName)) return this.fSensor2;
    return null;
  }
  
  public Sensor getSensor1() {
    return this.fSensor1;
  }
  
  public Sensor getSensor2() {
    return this.fSensor2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("sensor1".equals(parameterName) ) {
    	this.fSensor1 = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    if ("sensor2".equals(parameterName) ) {
    	this.fSensor2 = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSensor1(final Sensor pSensor1) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor1 = pSensor1;
  }
  
  public void setSensor2(final Sensor pSensor2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor2 = pSensor2;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.connectingSensors";
  }
  
  @Override
  public List<String> parameterNames() {
    return ConnectingSensorsMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSensor1, fSensor2};
  }
  
  @Override
  public ConnectingSensorsMatch toImmutable() {
    return isMutable() ? newMatch(fSensor1, fSensor2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"sensor1\"=" + prettyPrintValue(fSensor1) + ", ");
    
    result.append("\"sensor2\"=" + prettyPrintValue(fSensor2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSensor1 == null) ? 0 : fSensor1.hashCode());
    result = prime * result + ((fSensor2 == null) ? 0 : fSensor2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof ConnectingSensorsMatch)) { // this should be infrequent
    	if (obj == null) {
    		return false;
    	}
    	if (!(obj instanceof IPatternMatch)) {
    		return false;
    	}
    	IPatternMatch otherSig  = (IPatternMatch) obj;
    	if (!specification().equals(otherSig.specification()))
    		return false;
    	return Arrays.deepEquals(toArray(), otherSig.toArray());
    }
    ConnectingSensorsMatch other = (ConnectingSensorsMatch) obj;
    if (fSensor1 == null) {if (other.fSensor1 != null) return false;}
    else if (!fSensor1.equals(other.fSensor1)) return false;
    if (fSensor2 == null) {if (other.fSensor2 != null) return false;}
    else if (!fSensor2.equals(other.fSensor2)) return false;
    return true;
  }
  
  @Override
  public ConnectingSensorsQuerySpecification specification() {
    try {
    	return ConnectingSensorsQuerySpecification.instance();
    } catch (IncQueryException ex) {
     	// This cannot happen, as the match object can only be instantiated if the query specification exists
     	throw new IllegalStateException (ex);
    }
  }
  
  /**
   * Returns an empty, mutable match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @return the empty match.
   * 
   */
  public static ConnectingSensorsMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static ConnectingSensorsMatch newMutableMatch(final Sensor pSensor1, final Sensor pSensor2) {
    return new Mutable(pSensor1, pSensor2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSensor1 the fixed value of pattern parameter sensor1, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static ConnectingSensorsMatch newMatch(final Sensor pSensor1, final Sensor pSensor2) {
    return new Immutable(pSensor1, pSensor2);
  }
  
  private static final class Mutable extends ConnectingSensorsMatch {
    Mutable(final Sensor pSensor1, final Sensor pSensor2) {
      super(pSensor1, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends ConnectingSensorsMatch {
    Immutable(final Sensor pSensor1, final Sensor pSensor2) {
      super(pSensor1, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
