package hu.bme.mit.trainbenchmark.benchmark.emfincquery;

import hu.bme.mit.trainbenchmark.benchmark.emfincquery.util.EntrySemaphoreSensorQuerySpecification;
import hu.bme.mit.trainbenchmark.railway.Semaphore;
import hu.bme.mit.trainbenchmark.railway.Sensor;
import java.util.Arrays;
import java.util.List;
import org.eclipse.incquery.runtime.api.IPatternMatch;
import org.eclipse.incquery.runtime.api.impl.BasePatternMatch;
import org.eclipse.incquery.runtime.exception.IncQueryException;

/**
 * Pattern-specific match representation of the hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphoreSensor pattern,
 * to be used in conjunction with {@link EntrySemaphoreSensorMatcher}.
 * 
 * <p>Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters,
 * usable to represent a match of the pattern in the result of a query,
 * or to specify the bound (fixed) input parameters when issuing a query.
 * 
 * @see EntrySemaphoreSensorMatcher
 * @see EntrySemaphoreSensorProcessor
 * 
 */
@SuppressWarnings("all")
public abstract class EntrySemaphoreSensorMatch extends BasePatternMatch {
  private Semaphore fSemaphore;
  
  private Sensor fSensor2;
  
  private static List<String> parameterNames = makeImmutableList("semaphore", "sensor2");
  
  private EntrySemaphoreSensorMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    this.fSemaphore = pSemaphore;
    this.fSensor2 = pSensor2;
  }
  
  @Override
  public Object get(final String parameterName) {
    if ("semaphore".equals(parameterName)) return this.fSemaphore;
    if ("sensor2".equals(parameterName)) return this.fSensor2;
    return null;
  }
  
  public Semaphore getSemaphore() {
    return this.fSemaphore;
  }
  
  public Sensor getSensor2() {
    return this.fSensor2;
  }
  
  @Override
  public boolean set(final String parameterName, final Object newValue) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    if ("semaphore".equals(parameterName) ) {
    	this.fSemaphore = (hu.bme.mit.trainbenchmark.railway.Semaphore) newValue;
    	return true;
    }
    if ("sensor2".equals(parameterName) ) {
    	this.fSensor2 = (hu.bme.mit.trainbenchmark.railway.Sensor) newValue;
    	return true;
    }
    return false;
  }
  
  public void setSemaphore(final Semaphore pSemaphore) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSemaphore = pSemaphore;
  }
  
  public void setSensor2(final Sensor pSensor2) {
    if (!isMutable()) throw new java.lang.UnsupportedOperationException();
    this.fSensor2 = pSensor2;
  }
  
  @Override
  public String patternName() {
    return "hu.bme.mit.trainbenchmark.benchmark.emfincquery.entrySemaphoreSensor";
  }
  
  @Override
  public List<String> parameterNames() {
    return EntrySemaphoreSensorMatch.parameterNames;
  }
  
  @Override
  public Object[] toArray() {
    return new Object[]{fSemaphore, fSensor2};
  }
  
  @Override
  public EntrySemaphoreSensorMatch toImmutable() {
    return isMutable() ? newMatch(fSemaphore, fSensor2) : this;
  }
  
  @Override
  public String prettyPrint() {
    StringBuilder result = new StringBuilder();
    result.append("\"semaphore\"=" + prettyPrintValue(fSemaphore) + ", ");
    
    result.append("\"sensor2\"=" + prettyPrintValue(fSensor2)
    );
    return result.toString();
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((fSemaphore == null) ? 0 : fSemaphore.hashCode());
    result = prime * result + ((fSensor2 == null) ? 0 : fSensor2.hashCode());
    return result;
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
    	return true;
    if (!(obj instanceof EntrySemaphoreSensorMatch)) { // this should be infrequent
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
    EntrySemaphoreSensorMatch other = (EntrySemaphoreSensorMatch) obj;
    if (fSemaphore == null) {if (other.fSemaphore != null) return false;}
    else if (!fSemaphore.equals(other.fSemaphore)) return false;
    if (fSensor2 == null) {if (other.fSensor2 != null) return false;}
    else if (!fSensor2.equals(other.fSensor2)) return false;
    return true;
  }
  
  @Override
  public EntrySemaphoreSensorQuerySpecification specification() {
    try {
    	return EntrySemaphoreSensorQuerySpecification.instance();
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
  public static EntrySemaphoreSensorMatch newEmptyMatch() {
    return new Mutable(null, null);
  }
  
  /**
   * Returns a mutable (partial) match.
   * Fields of the mutable match can be filled to create a partial match, usable as matcher input.
   * 
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the new, mutable (partial) match object.
   * 
   */
  public static EntrySemaphoreSensorMatch newMutableMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    return new Mutable(pSemaphore, pSensor2);
  }
  
  /**
   * Returns a new (partial) match.
   * This can be used e.g. to call the matcher with a partial match.
   * <p>The returned match will be immutable. Use {@link #newEmptyMatch()} to obtain a mutable match object.
   * @param pSemaphore the fixed value of pattern parameter semaphore, or null if not bound.
   * @param pSensor2 the fixed value of pattern parameter sensor2, or null if not bound.
   * @return the (partial) match object.
   * 
   */
  public static EntrySemaphoreSensorMatch newMatch(final Semaphore pSemaphore, final Sensor pSensor2) {
    return new Immutable(pSemaphore, pSensor2);
  }
  
  private static final class Mutable extends EntrySemaphoreSensorMatch {
    Mutable(final Semaphore pSemaphore, final Sensor pSensor2) {
      super(pSemaphore, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return true;
    }
  }
  
  private static final class Immutable extends EntrySemaphoreSensorMatch {
    Immutable(final Semaphore pSemaphore, final Sensor pSensor2) {
      super(pSemaphore, pSensor2);
    }
    
    @Override
    public boolean isMutable() {
      return false;
    }
  }
}
